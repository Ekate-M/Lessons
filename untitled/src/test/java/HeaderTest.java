import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
//1.Проверить название указанного блока;

public class HeaderTest {
    public static void main(String[] args) {
        // 1. Автоматическая настройка драйвера
        WebDriverManager.chromedriver().setup();

        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable");
        options.addArguments("--start-maximized");


        // Установка пути к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");

        // 3. Инициализация драйвера
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // 4. Открытие страницы МТС
            driver.get("https://www.mts.by");

            // 5. Принятие куков (если есть)
            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(., 'Принять') or contains(., 'Согласен')]")));
                acceptCookies.click();
            } catch (TimeoutException e) {
                System.out.println("Не найдена кнопка принятия куков, продолжаем...");
            }

            // 6. Поиск заголовка с локатором
            By headerLocator = By.xpath("//h2[contains(., 'пополнение') and contains(., 'комиссии')]");
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));

            // 7. Проверка видимости
            Assertions.assertTrue(header.isDisplayed(), "Заголовок не отображается");

            // 8. Нормализация текста
            String actualText = header.getText()
                    .replace("\n", " ")
                    .replaceAll("\\s+", " ")
                    .trim();

            // 9. Проверка текста (допускаем небольшие вариации)
            String expectedText = "Онлайн пополнение без комиссии";
            Assertions.assertTrue(actualText.contains("Онлайн пополнение"), "Не найден текст 'Онлайн пополнение'");
            Assertions.assertTrue(actualText.contains("без комиссии"), "Не найден текст 'без комиссии'");

            System.out.println("Тест успешно пройден! Найден заголовок: " + actualText);

        } catch (Exception e) {
            System.err.println("Ошибка теста: " + e.getMessage());
            e.printStackTrace();

            // Диагностика
            System.out.println("Текущий URL: " + driver.getCurrentUrl());
            System.out.println("Page source length: " + driver.getPageSource().length());

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}