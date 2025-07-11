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

            // 1. Закрываем куки-баннер, если он есть
            try {
                WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".cookie.show .cookie__close")
                ));
                cookieAccept.click();
                System.out.println("Куки-баннер закрыт");
            } catch (TimeoutException e) {
                System.out.println("Куки-баннер не найден, продолжаем без закрытия");
            }

            // 2. Раскрываем выпадающий список
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                    By.className("select__header")
            ));
            selectHeader.click();

            // 3. Выбираем первый вариант из списка
            WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__option:first-child")
            ));
            firstOption.click();

            // 4. Заполняем поля формы
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("connection-phone")
            ));
            phoneField.sendKeys("297777777");

            WebElement sumField = driver.findElement(By.id("connection-sum"));
            sumField.sendKeys("500");

            WebElement emailField = driver.findElement(By.id("connection-email"));
            emailField.sendKeys("ekate-meshkova@example.com");

            // 5. Кликаем кнопку "Продолжить" с проверкой
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.button:not([disabled])")
            ));

            // Дополнительная проверка, что элемент видим и кликабелен
            wait.until(ExpectedConditions.visibilityOf(continueButton));
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));

            // Прокручиваем к элементу перед кликом
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();

            System.out.println("Форма успешно отправлена");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}



