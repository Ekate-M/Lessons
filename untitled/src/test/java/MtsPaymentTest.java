import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.PaymentModalPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsPaymentTest {
    private WebDriver driver;
    private MainPage mainPage;
    private PaymentModalPage paymentModalPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        paymentModalPage = new PaymentModalPage(driver);
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testServiceDetailsLink() {
        mainPage.open();
        mainPage.clickServiceDetailsLink();

        // Проверка URL
        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
    }

    @Test
    @DisplayName("Проверка надписей в полях разных услуг")
    public void testFieldPlaceholders() {
        mainPage.open();

        // Проверка для услуг связи
        mainPage.selectServiceType("Услуги связи");
        List<String> connectionPlaceholders = mainPage.getConnectionFieldPlaceholders();
        assertEquals("Номер телефона", connectionPlaceholders.get(0));
        assertEquals("Сумма", connectionPlaceholders.get(1));
        assertEquals("Email", connectionPlaceholders.get(2));

        // Проверка для домашнего интернета
        mainPage.selectServiceType("Домашний интернет");
        List<String> internetPlaceholders = mainPage.getInternetFieldPlaceholders();
        assertEquals("Номер договора", internetPlaceholders.get(0));
        // ... остальные проверки
    }

    @Test
    @DisplayName("Проверка полного цикла оплаты для услуг связи")
    public void testConnectionPaymentFlow() {
        mainPage.open();
        mainPage.selectServiceType("Услуги связи");

        // Заполнение формы
        mainPage.fillPhoneField("297777777");
        mainPage.fillAmountField("500");
        mainPage.fillEmailField("test@example.com");
        mainPage.clickContinueButton();

        // Проверки в модальном окне
        assertEquals("Подтверждение платежа", paymentModalPage.getModalTitle());
        assertTrue(paymentModalPage.getDisplayedPhoneNumber().contains("297777777"));
        assertTrue(paymentModalPage.getDisplayedAmount().contains("5.00"));
        assertTrue(paymentModalPage.getContinueButtonText().contains("5.00"));

        // Проверка плейсхолдеров полей карты
        assertEquals("Номер карты", paymentModalPage.getCardNumberPlaceholder());
        assertEquals("ММ/ГГ", paymentModalPage.getExpiryPlaceholder());
        assertEquals("Имя на карте", paymentModalPage.getNamePlaceholder());
        assertEquals("CVV", paymentModalPage.getCvvPlaceholder());

        // Проверка иконок платежных систем
        List<String> expectedIcons = List.of("Visa", "Mastercard", "Белкарт", "Verified By Visa");
        assertEquals(expectedIcons.size(), paymentModalPage.getPaymentSystemIcons().size());
    }
}