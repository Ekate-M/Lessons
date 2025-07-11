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


        // Открываем страницу
        driver.get("https://www.mts.by/");


        driver.findElement(By.className("select__header"));
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("500");
        driver.findElement(By.id("connection-email")).sendKeys("ekate-meshkova@xample.ru");
        driver.findElement(By.className("button")).click();
        driver.quit();
    }}


        //String expectedTitle = "Онлайн пополнение без комиссии";
          //  String actualTitle = blockTitle.getText().trim();
            //Assert.assertEquals(blockTitle.getText(),expectedTitle);

