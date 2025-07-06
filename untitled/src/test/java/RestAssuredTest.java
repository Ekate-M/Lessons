import org.testng.annotations.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void testGetRequestWithParams() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args", equalTo(Collections.emptyMap()))  // Проверяем, что args пуст
                .body("headers.host", equalTo("postman-echo.com")) // Проверка конкретного заголовка
                .body("url", equalTo("https://postman-echo.com/get"));  // URL без параметров
    }

    @Test
    public void testPostRequestWithFullValidation() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                // Проверка args (query-параметров)
                .body("args", equalTo(Collections.emptyMap()))
                // Проверка данных в теле ответа
                .body("data", equalTo(requestBody))
                // Проверка files (загруженных файлов)
                .body("files", equalTo(Collections.emptyMap()))
                // Проверка form (form-data)
                .body("form", equalTo(Collections.emptyMap()))
                // Проверка заголовков
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", containsString("text/plain"))
                .body("headers.user-agent", notNullValue())
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'content-length'", equalTo("58"))
                // Проверка json (парсинг тела как JSON)
                .body("json", nullValue())
                // Проверка URL
                .body("url", equalTo("https://postman-echo.com/post"));
    }
    @Test
    public void testPostRequestWithJsonValidation() {
        String requestBody = "test body"; // Длина 9 символов
        int expectedLength = requestBody.length();

        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                // Проверка длины содержимого
                .body("headers.'content-length'", equalTo(String.valueOf(expectedLength)))
                // Остальные проверки
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("application/json"));
    }

}
