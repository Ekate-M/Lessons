
import core.CookieUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceDetailsLinkTest extends BaseTest {

    @Test
    public void testServiceDetailsLink() {
        // 1. Принимаем куки с обработкой перекрытия
        acceptCookiesWithJS();

        // 2. Находим ссылку "Подробнее о сервисе"
        WebElement detailsLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'poryadok-oplaty-i-bezopasnost-internet-platezhey') and " +
                        "(contains(text(), 'Подробнее о сервисе') or contains(., 'Подробнее о сервисе'))]")));

        // 3. Сохраняем текущий URL
        String originalUrl = driver.getCurrentUrl();

        // 4. Кликаем по ссылке через JavaScript
        ((JavascriptExecutor)driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();",
                detailsLink);

        // 5. Ожидаем изменения URL (более гибкая проверка)
        String expectedUrlPattern = ".*poryadok-oplaty.*";
        wait.until(ExpectedConditions.urlMatches(expectedUrlPattern));

        // 6. Проверяем фактический URL
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.matches(expectedUrlPattern),
                "Ожидался URL содержащий 'poryadok-oplaty', но получили: " + currentUrl);
    }

    private void acceptCookiesWithJS() {
        // Находим кнопку принятия cookie
        WebElement cookieAccept = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(@class, 'cookie__ok') or contains(@id, 'cookie-agree')]")));

        // Кликаем через JavaScript, минуя перекрытие
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", cookieAccept);

        // Ждем исчезновения баннера
        wait.until(ExpectedConditions.invisibilityOf(cookieAccept));
    }
}



