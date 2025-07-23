import core.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceDetailsLinkTest extends BaseTest {
    @Test
    public void testServiceDetailsLink() {
        WebElement moreInfoLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href,'poryadok-oplaty-i-bezopasnost-internet-platezhey') and " +
                        "contains(text(),'Подробнее о сервисе')]")
        ));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", moreInfoLink);
        moreInfoLink.click();

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
    }
}