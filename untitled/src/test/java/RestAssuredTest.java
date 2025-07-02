
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void testGetRequestWithFullValidation() {
        given()
                .baseUri("https://postman-echo.com/get")  // Базовый URL без эндпоинта
                .queryParam("test", "example")         // Добавляем тестовый параметр
                .when()
                .get("/get")                          // Указываем эндпоинт отдельно
                .then()
                .assertThat()
                .statusCode(200)                       // Проверка кода ответа
                // Проверка структуры ответа
                .body("args.test", equalTo("example")) // Проверка переданного параметра
                .body("url", equalTo("https://postman-echo.com/get?test=example"))
                .body("headers", hasKey("host"))       // Проверка обязательных заголовков
                .body("headers.host", equalTo("postman-echo.com"))
                .body("$", hasKey("args"))             // Проверка наличия поля args
                .log().all();                          // Логирование полного ответа
    }
}












