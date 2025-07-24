import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentIconsTest extends BaseTest {
    private static final List<String> EXPECTED_ICONS = Arrays.asList(
            "Visa", "Mastercard", "Белкарт", "Verified By Visa"
    );

    @Test
    public void verifyPaymentIcons() {
        driver.get("https://www.mts.by/");

        List<WebElement> icons = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".payment-icons img, .payment-icons svg")
                ));

        // Проверка количества иконок
        assertEquals(EXPECTED_ICONS.size(), icons.size(),
                "Количество иконок не соответствует ожидаемому");

        // Проверка наличия всех иконок
        List<String> foundIconNames = new ArrayList<>();
        for (WebElement icon : icons) {
            String iconName = getIconName(icon);
            foundIconNames.add(iconName);
            assertTrue(icon.isDisplayed(), "Иконка не отображается: " + iconName);
        }

        for (String expectedIcon : EXPECTED_ICONS) {
            assertTrue(foundIconNames.stream().anyMatch(name ->
                            name.toLowerCase().contains(expectedIcon.toLowerCase())),
                    "Не найдена иконка: " + expectedIcon);
        }
    }

    private String getIconName(WebElement icon) {
        return Optional.ofNullable(icon.getAttribute("alt"))
                .or(() -> Optional.ofNullable(icon.getAttribute("title")))
                .or(() -> Optional.ofNullable(icon.getAttribute("class")))
                .orElse("");
    }
}