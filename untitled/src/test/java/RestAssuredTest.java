import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void testGetRequestWithFullValidation() {
        given()
                .baseUri("https://postman-echo.com")  // Базовый URL без конечной точки
                .when()
                .get("/get")                          // Указываем конечную точку отдельно
                .then()
                .assertThat()
                .statusCode(200);                 // Проверка кода ответа

    }
}








