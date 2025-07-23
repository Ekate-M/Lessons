
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MtsByTest extends BaseTest {

    @Test
    @DisplayName("Проверка заголовка блока 'Онлайн пополнение без комиссии'")
    public void testPaymentBlockTitle() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(., 'пополнение') and contains(., 'комиссии')]")
        ));

        String normalizedText = header.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        assertAll(
                () -> assertTrue(normalizedText.contains("Онлайн пополнение"),
                        "Заголовок должен содержать 'Онлайн пополнение'"),
                () -> assertTrue(normalizedText.contains("без комиссии"),
                        "Заголовок должен содержать 'без комиссии'")
        );
    }


    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testServiceDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));
        link.click();

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".service-content")
        ));
        assertTrue(content.isDisplayed(), "Контент страницы не отображается");
    }

    @Test
    @DisplayName("Проверка работы формы оплаты")
    public void testPaymentForm() {
        // Выбор услуги связи
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".service-select")
        ));
        dropdown.click();

        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select-option:first-child")
        ));
        firstOption.click();

        // Заполнение полей
        fillField("phone", "297777777");
        fillField("amount", "500");
        fillField("email", "test@example.com");

        // Проверка кнопки
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".continue-button")
        ));
        assertTrue(continueButton.isEnabled(), "Кнопка должна быть активна");


    }

    private void fillField(String fieldName, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(fieldName)
        ));
        field.clear();
        field.sendKeys(value);
    }
}