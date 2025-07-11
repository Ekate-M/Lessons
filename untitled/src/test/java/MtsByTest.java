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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTest {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable"); // Явно указываем версию
        options.addArguments("--start-maximized");


        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);


        driver.get("https://www.mts.by/");
        // 4.Заполнить поля и проверить работу кнопки «Продолжить»


        WebElement element = driver.findElement(By.className("select__header"));
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("500");
        driver.findElement(By.id("connection-email")).sendKeys("ekate-meshkova@xample.ru");
        driver.findElement(By.className("button")).click();
        driver.quit();
    }}




