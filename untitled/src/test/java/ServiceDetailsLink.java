import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceDetailsLink extends BaseTest {

    @Test
    public void testServiceDetailsLink() {
        // 1. Получаем текущее количество открытых вкладок
        int initialTabCount = driver.getWindowHandles().size();

        // 2. Находим и кликаем на кнопку "Продолжить"
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'poryadok-oplaty-i-bezopasnost-internet-platezhey')]")));

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", link);

        // 3. Ожидаем открытия нового окна/вкладки
        WebDriverWait windowWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        windowWait.until(ExpectedConditions.numberOfWindowsToBe(initialTabCount + 1));

        // 4. Переключаемся на новую вкладку
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        // 5. Проверяем URL новой вкладки
        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        // 6. Проверяем содержимое новой вкладки
        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(., 'Порядок оплаты и безопасность интернет-платежей')]")));

        assertTrue(content.isDisplayed(), "Ожидаемый контент не найден на новой вкладке");

        // 7. Закрываем новую вкладку и возвращаемся обратно
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}