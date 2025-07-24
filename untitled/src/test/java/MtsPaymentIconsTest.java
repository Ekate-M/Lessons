import core.CookieUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MtsPaymentIconsTest extends BaseTest {
    private static final List<String> EXPECTED_ICONS = Arrays.asList(
            "Visa", "Mastercard", "Белкарт", "Verified By Visa"
    );

    @Test
    public void verifyPaymentIcons() {

        driver.get("https://www.mts.by/");


        CookieUtils.acceptCookies(driver, wait);



        WebElement payPartnersContainer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".pay__partners")
                )
        );

        //  Ищем иконки только внутри этого контейнера
        List<WebElement> icons = payPartnersContainer.findElements(By.xpath(
                ".//img[" +
                        "contains(@src, 'visa') or " +
                        "contains(@src, 'mastercard') or " +
                        "contains(@src, 'belkart') or " +
                        "contains(@alt, 'Visa') or " +
                        "contains(@alt, 'Mastercard') or " +
                        "contains(@alt, 'Белкарт')]"
        ));


        System.out.println("Найдено " + icons.size() + " иконок платежных систем:");
        icons.forEach(icon -> {
            System.out.println(" - Тег: " + icon.getTagName());
            System.out.println("   Src: " + icon.getAttribute("src"));
            System.out.println("   Alt: " + icon.getAttribute("alt"));
            System.out.println("   Class: " + icon.getAttribute("class"));
        });


        for (String expectedIcon : EXPECTED_ICONS) {
            boolean found = icons.stream().anyMatch(icon -> {
                String src = icon.getAttribute("src") != null ?
                        icon.getAttribute("src").toLowerCase() : "";
                String alt = icon.getAttribute("alt") != null ?
                        icon.getAttribute("alt").toLowerCase() : "";

                if (expectedIcon.equals("Белкарт")) {
                    return src.contains("belkart") || alt.contains("белкарт");
                }
                return src.contains(expectedIcon.toLowerCase()) ||
                        alt.contains(expectedIcon.toLowerCase());
            });

            assertTrue(found, "Не найдена иконка: " + expectedIcon +
                    "\nНайденные иконки: " + getFoundIconsInfo(icons));
        }
    }

    private String getFoundIconsInfo(List<WebElement> icons) {
        StringBuilder sb = new StringBuilder();
        icons.forEach(icon -> {
            sb.append("\n- Src: ").append(icon.getAttribute("src"))
                    .append(", Alt: ").append(icon.getAttribute("alt"))
                    .append(", Class: ").append(icon.getAttribute("class"));
        });
        return sb.toString();
    }
}