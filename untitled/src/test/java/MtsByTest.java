
public class MtsByTest extends BaseTest {
    private MtsByPage mtsByPage;

    @Before
    public void setUpTest() {
        openUrl("https://www.mts.by/");
        mtsByPage = new MtsByPage(driver, wait);
    }

    @Test
    public void testPaymentFormSubmission() {
        // 1. Удаляем куки-баннер
        CookieBannerHandler.removeCookieBanner(driver);

        // 2. Раскрываем выпадающий список и выбираем первый вариант
        mtsByPage.openDropdownAndSelectFirstOption();

        // 3. Заполняем поля формы
        mtsByPage.fillPhoneField("297777777");
        mtsByPage.fillAmountField("500");
        mtsByPage.fillEmailField("ekate-meshkova@example.com");

        // 4. Кликаем кнопку "Продолжить"
        mtsByPage.clickContinueButton();

        // Здесь можно добавить проверки успешности отправки формы
    }
}