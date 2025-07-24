package core;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookieUtils {
    public static void acceptCookies(WebDriver driver, WebDriverWait wait) {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'cookie')]//button[contains(., 'Принять') or contains(., 'Согласен')]")
            ));
            acceptButton.click();
        } catch (TimeoutException e) {
            System.out.println("Cookie banner not found or already accepted");
        }
    }
}
