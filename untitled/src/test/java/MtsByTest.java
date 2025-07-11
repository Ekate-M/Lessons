import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MtsByTest {
    public static void main(String[] args) {
        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        options.addArguments("--start-maximized");

        // Установка пути к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");

        // Инициализация драйвера
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Открытие страницы
            driver.get("https://www.mts.by/");

            // 1. Удаляем куки-баннер через JavaScript (надежный способ)
            removeCookieBanner(driver);

            // 2. Раскрываем выпадающий список
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("select__header")
            ));

            // Кликаем через JavaScript для избежания перехвата клика
            clickWithJS(driver, selectHeader);

            // 3. Выбираем первый вариант из списка
            WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__option:first-child")
            ));
            clickWithJS(driver, firstOption);

            // 4. Заполняем поля формы
            fillField(driver, By.id("connection-phone"), "297777777");
            fillField(driver, By.id("connection-sum"), "500");
            fillField(driver, By.id("connection-email"), "ekate-meshkova@example.com");

            // 5. Кликаем кнопку "Продолжить" через JavaScript
            WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button.button:not([disabled])")
            ));
            clickWithJS(driver, continueButton);

            System.out.println("Форма успешно отправлена");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void removeCookieBanner(WebDriver driver) {
        try {
            // Пробуем закрыть обычным способом
            WebElement cookieClose = driver.findElement(By.cssSelector(".cookie.show .cookie__close"));
            cookieClose.click();
            System.out.println("Куки-баннер закрыт через UI");
        } catch (Exception e) {
            // Если не получилось, удаляем через JavaScript
            ((JavascriptExecutor)driver).executeScript(
                    "var banner = document.querySelector('.cookie.show');" +
                            "if (banner) banner.remove();"
            );
            System.out.println("Куки-баннер удален через JavaScript");
        }
    }

    private static void clickWithJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    private static void fillField(WebDriver driver, By locator, String value) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);
    }
}


