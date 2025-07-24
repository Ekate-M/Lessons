package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentModalPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы элементов модального окна
    private final By modalTitle = By.cssSelector(".payment-modal-title");
    private final By phoneDisplay = By.cssSelector(".payment-phone-number");
    private final By amountDisplay = By.cssSelector(".payment-amount");
    private final By continueButton = By.cssSelector(".continue-button-amount");
    private final By paymentIcons = By.cssSelector(".payment-system-icon");
    private final By cardNumberInput = By.cssSelector("input[data-field='number']");
    private final By expiryInput = By.cssSelector("input[data-field='expiry']");
    private final By nameInput = By.cssSelector("input[data-field='name']");
    private final By cvvInput = By.cssSelector("input[data-field='cvv']");

    public PaymentModalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle)).getText();
    }

    public String getDisplayedPhoneNumber() {
        return driver.findElement(phoneDisplay).getText();
    }

    public String getDisplayedAmount() {
        return driver.findElement(amountDisplay).getText();
    }

    public String getContinueButtonText() {
        return driver.findElement(continueButton).getText();
    }

    public List<WebElement> getPaymentSystemIcons() {
        return driver.findElements(paymentIcons);
    }

    public String getCardNumberPlaceholder() {
        return driver.findElement(cardNumberInput).getAttribute("placeholder");
    }

    public String getExpiryPlaceholder() {
        return driver.findElement(expiryInput).getAttribute("placeholder");
    }

    public String getNamePlaceholder() {
        return driver.findElement(nameInput).getAttribute("placeholder");
    }

    public String getCvvPlaceholder() {
        return driver.findElement(cvvInput).getAttribute("placeholder");
    }
}