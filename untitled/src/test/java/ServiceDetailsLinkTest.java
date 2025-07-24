import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceDetailsLinkTest extends BaseTest {

    @Test
    public void testServiceDetailsLink() {
        // 1. Получаем текущее количество открытых вкладок
        int initialTabCount = driver.getWindowHandles().size();

        // 2. Находим и кликаем на ссылку "Порядок оплаты и безопасность интернет-платежей"
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'poryadok-oplaty-i-bezopasnost-internet-platezhey')]")));

        // Кликаем через JavaScript для избежания возможных проблем с перекрытием элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", link);

        // 3. Ожидаем открытия новой вкладки (ожидаем увеличения количества вкладок)
        WebDriverWait windowWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        windowWait.until(ExpectedConditions.numberOfWindowsToBe(initialTabCount + 1));

        // 4. Переключаемся на новую вкладку
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        // 5. Проверяем URL новой вкладки
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        assertEquals(expectedUrl, driver.getCurrentUrl(), "URL новой вкладки не соответствует ожидаемому");

        // 6. Проверяем наличие основного контента на странице
        // Более надежный селектор для проверки заголовка или основного контента
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(), 'Порядок оплаты и безопасность интернет-платежей')]")));

        assertTrue(pageTitle.isDisplayed(), "Заголовок страницы не найден или не отображается");

        // 7. Закрываем новую вкладку и возвращаемся обратно
        driver.close();
        driver.switchTo().window(tabs.get(0));

        // Дополнительная проверка, что вернулись на исходную вкладку
        assertNotEquals(expectedUrl, driver.getCurrentUrl(), "Не удалось вернуться на исходную вкладку");
    }
}