

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MtsPaymentIconsTest {
    public static void main(String[] args) {

        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        options.addArguments("--start-maximized");

        // Установка пути к драйверу

        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        // Настройка драйвера
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
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
                checkPaymentIcon(driver, wait, icon);
            }

        } finally {
            driver.quit();
        }
    }

    private static void checkPaymentIcon(WebDriver driver, WebDriverWait wait, String iconFileName) {
        try {
            // 1. Пробуем найти как обычное изображение
            List<WebElement> imgElements = driver.findElements(
                    By.xpath("//img[contains(@src, '" + iconFileName + "')]")
            );

            if (!imgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(imgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как изображение");
                return;
            }

            // 2. Пробуем найти как SVG через use
            List<WebElement> svgElements = driver.findElements(
                    By.xpath("//*[local-name()='use'][contains(@*[local-name()='href'], '" + iconFileName + "')]")
            );

            if (!svgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(svgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как SVG");
                return;
            }

            // 3. Пробуем найти как SVG через image
            svgElements = driver.findElements(
                    By.xpath("//*[local-name()='image'][contains(@*[local-name()='href'], '" + iconFileName + "')]")
            );

            if (!svgElements.isEmpty()) {
                WebElement icon = wait.until(ExpectedConditions.visibilityOf(svgElements.get(0)));
                System.out.println("Найдена иконка " + iconFileName + " как SVG image");
                return;
            }

            System.err.println("Иконка " + iconFileName + " не найдена");

        } catch (Exception e) {
            System.err.println("Ошибка при поиске иконки " + iconFileName + ": " + e.getMessage());
        }
    }
}