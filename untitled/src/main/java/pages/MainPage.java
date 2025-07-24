

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы элементов
    private final By serviceDetailsLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");
    private final By serviceDropdown = By.cssSelector(".service-select");
    private final By serviceOptions = By.cssSelector(".select-option");
    private final By phoneInput = By.name("phone");
    private final By amountInput = By.name("amount");
    private final By emailInput = By.name("email");
    private final By continueButton = By.cssSelector(".continue-button");

    // Локаторы для разных типов услуг
    private final By connectionFields = By.cssSelector(".connection-form input");
    private final By internetFields = By.cssSelector(".internet-form input");
    private final By instalmentFields = By.cssSelector(".instalment-form input");
    private final By arrearsFields = By.cssSelector(".arrears-form input");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.mts.by/");
    }

    public void clickServiceDetailsLink() {
        driver.findElement(serviceDetailsLink).click();
    }

    public void selectServiceType(String serviceName) {
        driver.findElement(serviceDropdown).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(serviceOptions));

        driver.findElements(serviceOptions).stream()
                .filter(option -> option.getText().contains(serviceName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void fillPhoneField(String phone) {
        fillField(phoneInput, phone);
    }

    public void fillAmountField(String amount) {
        fillField(amountInput, amount);
    }

    public void fillEmailField(String email) {
        fillField(emailInput, email);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public List<String> getConnectionFieldPlaceholders() {
        return getFieldPlaceholders(connectionFields);
    }

    public List<String> getInternetFieldPlaceholders() {
        return getFieldPlaceholders(internetFields);
    }

    public List<String> getInstalmentFieldPlaceholders() {
        return getFieldPlaceholders(instalmentFields);
    }

    public List<String> getArrearsFieldPlaceholders() {
        return getFieldPlaceholders(arrearsFields);
    }

    private void fillField(By locator, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        field.clear();
        field.sendKeys(value);
    }

    private List<String> getFieldPlaceholders(By fieldsLocator) {
        return driver.findElements(fieldsLocator).stream()
                .map(field -> field.getAttribute("placeholder"))
                .toList();
    }
}