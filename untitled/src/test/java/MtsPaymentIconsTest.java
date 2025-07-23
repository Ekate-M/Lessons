import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentIconsTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final List<String> EXPECTED_ICONS = Arrays.asList(
            "Visa", "Mastercard", "Белкарт", "Verified By Visa"
    );

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyPaymentIcons() {
        driver.get("https://www.mts.by/");

        List<WebElement> icons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".payment-icons img, .payment-icons svg")
                ));

        // Проверка количества иконок
        assertEquals(EXPECTED_ICONS.size(), icons.size(),
                "Количество иконок не соответствует ожидаемому");

        // Проверка наличия всех иконок
        List<String> foundIconNames = new ArrayList<>();
        for (WebElement icon : icons) {
            String iconName = getIconName(icon);
            foundIconNames.add(iconName);
            assertTrue(icon.isDisplayed(), "Иконка не отображается: " + iconName);
        }

        for (String expectedIcon : EXPECTED_ICONS) {
            assertTrue(foundIconNames.stream().anyMatch(name ->
                            name.toLowerCase().contains(expectedIcon.toLowerCase())),
                    "Не найдена иконка: " + expectedIcon);
        }
    }

    private String getIconName(WebElement icon) {
        return Optional.ofNullable(icon.getAttribute("alt"))
                .or(() -> Optional.ofNullable(icon.getAttribute("title")))
                .or(() -> Optional.ofNullable(icon.getAttribute("class")))
                .orElse("");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}