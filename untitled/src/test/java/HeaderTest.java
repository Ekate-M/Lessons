import core.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class HeaderTest extends BaseTest {
    @Test
    public void testPaymentBlockTitle() {
        By headerLocator = By.xpath("//h2[contains(., 'пополнение') and contains(., 'комиссии')]");
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));

        assertTrue(header.isDisplayed(), "Заголовок не отображается");

        String actualText = header.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        assertAll(
                () -> assertTrue(actualText.contains("Онлайн пополнение")),
                () -> assertTrue(actualText.contains("без комиссии"))
        );
    }
}