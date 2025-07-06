

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
}