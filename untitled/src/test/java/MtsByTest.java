import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class MtsByTest {
    public static void main(String[] args) {
        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable"); // Явно указываем версию
        options.addArguments("--start-maximized");

        // Инициализация драйвера
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Открываем страницу
            driver.get("https://www.mts.by/");
            System.out.println("Заголовок страницы: " + driver.getTitle());

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Уточненные локаторы для страницы МТС
            List<String> xpaths = Arrays.asList(
                    "//section[contains(@class,'services')]//h2[contains(., 'пополнение')]",
                    "//div[contains(@class,'payment')]//h3[contains(., 'Онлайн')]",
                    "//*[contains(text(), 'Онлайн пополнение без комиссии')]",
                    "//*[contains(@class,'service-card') and contains(., 'пополнение')]"
            );

            WebElement paymentElement = findElement(wait, xpaths);

            if (paymentElement != null) {
                verifyPaymentText(paymentElement);
            } else {
                System.out.println("Элемент не найден. Возможные причины:");
                System.out.println("- Изменилась структура страницы");
                System.out.println("- Элемент загружается динамически");
                System.out.println("- Неправильные локаторы");
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Тест завершен");
        }
    }

    private static WebElement findElement(WebDriverWait wait, List<String> xpaths) {
        for (String xpath : xpaths) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                System.out.println("Успех: элемент найден по XPath - " + xpath);
                return element;
            } catch (Exception e) {
                System.out.println("Элемент не найден по XPath: " + xpath);
            }
        }
        return null;
    }

    private static void verifyPaymentText(WebElement element) {
        String expected = "Онлайн пополнение без комиссии";
        String actual = element.getText().trim();

        System.out.println("Ожидаемый текст: " + expected);
        System.out.println("Фактический текст: " + actual);

        if (actual.equals(expected)) {
            System.out.println("Текст полностью совпадает");
        } else if (actual.contains(expected)) {
            System.out.println("Текст частично совпадает");
        } else {
            System.out.println("Текст не совпадает");
        }
    }
}