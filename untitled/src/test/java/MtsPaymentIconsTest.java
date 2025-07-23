import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MtsPaymentIconsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        options.addArguments("--start-maximized");

        // Установка пути к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");

        // Инициализация драйвера
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testPaymentIconsPresence() {
        // Открытие страницы
        driver.get("https://www.mts.by/");

        // Список всех иконок для проверки
        String[] icons = {
                "visa.svg",
                "visa-verified.svg",
                "mastercard.svg",
                "mastercard-secure.svg",
                "belkart.svg"
        };

        // Проверяем каждую иконку
        for (String icon : icons) {
            assertTrue("Иконка " + icon + " не найдена", isPaymentIconPresent(icon));
        }
    }

    private boolean isPaymentIconPresent(String iconFileName) {
        try {
            // 1. Пробуем найти как обычное изображение
            List<WebElement> imgElements = driver.findElements(
                    By.xpath("//img[contains(@src, '" + iconFileName + "')]")
            );

            if (!imgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(imgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как изображение");
                return true;
            }

            // 2. Пробуем найти как SVG через use
            List<WebElement> svgElements = driver.findElements(
                    By.xpath("//*[local-name()='use'][contains(@*[local-name()='href'], '" + iconFileName + "')]")
            );

            if (!svgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(svgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как SVG");
                return true;
            }

            // 3. Пробуем найти как SVG через image
            svgElements = driver.findElements(
                    By.xpath("//*[local-name()='image'][contains(@*[local-name()='href'], '" + iconFileName + "')]")
            );

            if (!svgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(svgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как SVG image");
                return true;
            }

            return false;

        } catch (Exception e) {
            System.err.println("Ошибка при поиске иконки " + iconFileName + ": " + e.getMessage());
            return false;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}