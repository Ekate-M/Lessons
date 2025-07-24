import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeaderTest extends BaseTest {
    private static final String EXPECTED_TEXT = "Онлайн пополнение без комиссии";

    @Test
    public void testHeaderContent() {
        // Убедимся, что мы на нужной странице
        assertTrue(driver.getCurrentUrl().startsWith(BASE_URL), "Не открыта главная страница МТС");

        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(., 'пополнение') and contains(., 'комиссии')]")));

        String headerText = header.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        assertTrue(headerText.contains(EXPECTED_TEXT),
                "Заголовок не содержит ожидаемый текст. Фактический текст: " + headerText);
    }
}
