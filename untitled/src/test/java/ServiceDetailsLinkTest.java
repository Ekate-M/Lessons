import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceDetailsLinkTest extends BaseTest {

    @Test
    public void testServiceDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'poryadok-oplaty-i-bezopasnost-internet-platezhey')]")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", link);

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(., 'Порядок оплаты и безопасность интернет-платежей')]")));

        assertTrue(content.isDisplayed(), "Expected content not found");
    }
}