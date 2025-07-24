import core.CookieUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsByTest extends BaseTest {

    @Test
    @DisplayName("Проверка работы формы оплаты услуг связи")
    public void testPaymentForm() {
        // Ожидаем загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Выбираем "Услуги связи" из выпадающего списка
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select__wrapper")
        ));
        dropdown.click();

        WebElement communicationServicesOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".select__now")
        ));
        communicationServicesOption.click();


        fillField(By.id("connection-phone"), "297777777");
        fillField(By.id("connection-sum"), "500");
        fillField(By.id("connection-email"), "test@example.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".button.button__default")));
        continueButton.click();
        assertTrue(continueButton.isEnabled(), "Кнопка должна быть активна");


    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }
}