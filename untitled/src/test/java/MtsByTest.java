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
        // Настройка ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("stable"); // Явно указываем версию
        options.addArguments("--start-maximized");

        // Инициализация драйвера
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);


        // Открываем страницу
        driver.get("https://www.mts.by/");

        //2.Проверить наличие логотипов платёжных систем;
        // проверяем иконку Visa

        WebElement visaIcon = driver.findElement(By.xpath("/local/templates/new_design/assets/html/images/pages/index/pay/visa.svg")
        );
        assertTrue(visaIcon.isDisplayed(), "Иконка Visa не отображается");
        // проверяем иконку VisaVerified

        WebElement visaVerified = driver.findElement(By.xpath("/local/templates/new_design/assets/html/images/pages/index/pay/visa-verified.svg")
        );
        assertTrue(visaIcon.isDisplayed(), "Иконка VisaVerified не отображается");
        // проверяем иконку mastercard

        WebElement mastercard = driver.findElement(By.xpath("/local/templates/new_design/assets/html/images/pages/index/pay/mastercard.svg")
        );
        assertTrue(visaIcon.isDisplayed(), "Иконка mastercard не отображается");


        WebElement  mastercardSecure = driver.findElement(By.xpath("/local/templates/new_design/assets/html/images/pages/index/pay/mastercard-secure.svg")
        );
        assertTrue(visaIcon.isDisplayed(), "Иконка  mastercardSecure не отображается");
        // проверяем иконку Белкарт

        WebElement belkart = driver.findElement(By.xpath("   /local/templates/new_design/assets/html/images/pages/index/pay/belkart.svg")
        );
        assertTrue(visaIcon.isDisplayed(), "Иконка Белкарт не отображается");







        //boolean pay__form = driver.findElement(By.("/local/templates/new_design/assets/html/images/pages/index/pay/visa.svg")).isDisplayed();

        //boolean pay__form = driver.findElement(By.class("pay__form")).isDisplayed();


        WebElement element = driver.findElement(By.className("select__header"));
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("500");
        driver.findElement(By.id("connection-email")).sendKeys("ekate-meshkova@xample.ru");
        driver.findElement(By.className("button")).click();
        driver.quit();




    }}
        // assertNotNull("Элемент с классом 'pay__form' не найден", element);


// WebElement element = driver.findElement(By.className("pay__form"));


        //String expectedTitle = "Онлайн пополнение без комиссии";
          //  String actualTitle = blockTitle.getText().trim();
            //Assert.assertEquals(blockTitle.getText(),expectedTitle);

