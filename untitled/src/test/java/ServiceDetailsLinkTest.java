import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Assertions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ServiceDetailsLinkTest {
            public static void main(String[] args) {
                // Настройка ChromeOptions
                ChromeOptions options = new ChromeOptions();
                options.setBrowserVersion("stable");
                options.addArguments("--start-maximized");

                // Установка пути к драйверу
                System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");


        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // 1. Открытие страницы
            driver.get("https://www.mts.by/");
            System.out.println("Открыта главная страница MTS");

            // 2. Закрытие cookie-уведомления, если оно есть
            try {
                WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='cookie show']//button[contains(text(),'Принять')]")
                ));
                cookieAccept.click();
                System.out.println("Cookie-уведомление закрыто");
            } catch (Exception e) {
                System.out.println("Cookie-уведомление не найдено или не требует закрытия");
            }

            // 3. Поиск и клик по ссылке
            WebElement moreInfoLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/' and contains(text(),'Подробнее о сервисе')]")
            ));
            System.out.println("Ссылка найдена: " + moreInfoLink.getText());

            // Прокрутка к элементу перед кликом
            ((ChromeDriver) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", moreInfoLink);
            moreInfoLink.click();
            System.out.println("Клик выполнен");

            // 4. Проверка URL
            wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Текущий URL: " + currentUrl);

            Assertions.assertTrue(
                    currentUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                    "URL не содержит ожидаемый фрагмент. Фактический URL: " + currentUrl
            );


            System.out.println("Тест успешно выполнен!");

        } catch (Exception e) {
            System.err.println("Ошибка в тесте: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Браузер закрыт");
            }
        }
    }
}