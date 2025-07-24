import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // ← Добавьте эту строку
    public class MtsPaymentTest extends BaseTest {
        private static WebDriver driver;  // Теперь не static
        private static WebDriverWait wait;
    // Селекторы
    private static final By COOKIE_BANNER_BUTTON = By.cssSelector(".cookie__button");
    private static final By SERVICE_DROPDOWN = By.cssSelector(".select__wrapper");
    private static final By SERVICE_OPTION = By.cssSelector(".select__list");
    private static final By CONTINUE_BUTTON = By.cssSelector("button.button.button__default[type='submit']");
    private static final By PAYMENT_MODAL = By.cssSelector(".pay__form");
    private static final By CONNECTION_PHONE = By.id("connection-phone");
    private static final By CONNECTION_SUM = By.id("connection-sum");
    private static final By CONNECTION_EMAIL = By.id("connection-email");
    private static final By INTERNET_PHONE = By.id("internet-phone");
    private static final By INTERNET_SUM = By.id("internet-sum");
    private static final By INTERNET_EMAIL = By.id("internet-email");
    private static final By INSTALMENT_SCORE = By.id("score-instalment");
    private static final By INSTALMENT_SUM = By.id("instalment-sum");
    private static final By INSTALMENT_EMAIL = By.id("instalment-email");
    private static final By ARREARS_SCORE = By.id("score-arrears");
    private static final By ARREARS_SUM = By.id("arrears-sum");
    private static final By ARREARS_EMAIL = By.id("arrears-email");

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--remote-allow-origins=*", "--disable-notifications");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

        @AfterAll
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void openMtsWebsite() {
        driver.get("https://www.mts.by/");
        handleCookieBanner();
    }

    private void handleCookieBanner() {
        List<WebElement> cookieBanners = driver.findElements(COOKIE_BANNER_BUTTON);
        if (!cookieBanners.isEmpty() && cookieBanners.get(0).isDisplayed()) {
            cookieBanners.get(0).click();
        }
    }

    @Test
    @DisplayName("Проверка всех вариантов оплаты")
    public void testAllPaymentOptions() {
        List<String> services = Arrays.asList(
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        );

        for (String service : services) {
            System.out.println("\n=== Тестирование услуги: " + service + " ===");

            selectService(service);
            checkInputPlaceholders(service);

            if (!service.equals("Задолженность")) {
                navigateBackSafely();
            }
        }
    }

    @Test
    @DisplayName("Детальное тестирование услуги связи")
    public void testCommunicationServiceDetailed() {
        System.out.println("\n=== Детальное тестирование услуги связи ===");

        selectService("Услуги связи");

        fillField(CONNECTION_PHONE, "297777777");
        fillField(CONNECTION_SUM, "10");
        fillField(CONNECTION_EMAIL, "test@example.com");

        clickContinueButton();
        verifyPaymentModal();
    }

    private void selectService(String serviceName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(SERVICE_DROPDOWN));
        highlightElement(dropdown);
        dropdown.click();

        WebElement optionsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(SERVICE_OPTION));
        WebElement option = optionsContainer.findElement(By.xpath(".//*[contains(text(), '" + serviceName + "')]"));

        highlightElement(option);
        option.click();
        waitForPageLoad();
    }

    private void checkInputPlaceholders(String service) {
        System.out.println("Проверка плейсхолдеров для услуги: " + service);

        switch (service) {
            case "Услуги связи":
                verifyPlaceholders(
                        new String[]{"Номер телефона", "Сумма", "E-mail"},
                        CONNECTION_PHONE, CONNECTION_SUM, CONNECTION_EMAIL
                );
                break;
            case "Домашний интернет":
                verifyPlaceholders(
                        new String[]{"Номер телефона", "Сумма", "E-mail"},
                        INTERNET_PHONE, INTERNET_SUM, INTERNET_EMAIL
                );
                break;
            case "Рассрочка":
                verifyPlaceholders(
                        new String[]{"Номер счета", "Сумма", "E-mail"},
                        INSTALMENT_SCORE, INSTALMENT_SUM, INSTALMENT_EMAIL
                );
                break;
            case "Задолженность":
                verifyPlaceholders(
                        new String[]{"Номер счета", "Сумма", "E-mail"},
                        ARREARS_SCORE, ARREARS_SUM, ARREARS_EMAIL
                );
                break;
        }
    }

    private void verifyPlaceholders(String[] expectedPlaceholders, By... locators) {
        for (int i = 0; i < locators.length; i++) {
            WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locators[i]));
            String actualPlaceholder = field.getAttribute("placeholder");

            assertEquals(expectedPlaceholders[i], actualPlaceholder,
                    String.format("Неверный плейсхолдер для %s. Ожидалось: '%s', фактический: '%s'",
                            locators[i], expectedPlaceholders[i], actualPlaceholder));

            System.out.printf("Проверка плейсхолдера для %s успешна: %s%n", locators[i], actualPlaceholder);
        }
    }

    private void navigateBackSafely() {
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().back();
        wait.until(d -> !d.getCurrentUrl().equals(currentUrl));
        waitForPageLoad();
        handleCookieBanner();
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
        System.out.println("Заполнено поле " + locator + " значением: " + value);
    }

    private void clickContinueButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
        highlightElement(button);
        button.click();
        waitForPageLoad();
    }

    private void verifyPaymentModal() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_MODAL));
        assertTrue(modal.isDisplayed(), "Модальное окно оплаты не отображается");

        assertAll(
                () -> assertTrue(modal.getText().contains("297777777"), "Не отображается номер телефона"),
                () -> assertTrue(modal.getText().contains("10.00"), "Не отображается корректная сумма")
        );
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(1000); // Краткая пауза для стабилизации
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }
}