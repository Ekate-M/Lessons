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
                .body("json", nullValue())

    }
    @Test
    public void testPostmanEchoResponse() {
        String requestBody = "test"; // Должно соответствовать content-length: 8

        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)

                // Проверка структуры ответа
                .body("args", equalTo(Collections.emptyMap()))
                .body("files", equalTo(Collections.emptyMap()))
                .body("form", equalTo(Collections.emptyMap()))
                .body("json", nullValue())

                // Проверка данных
                .body("data", equalTo(requestBody))

                // Проверка обязательных заголовков
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", containsString("application/json"))
                .body("headers.accept", equalTo("*/*"))
                .body("headers.'x-forwarded-proto'", equalTo("https"));


    } @Test
    public void testFormDataResponseWithRestAssured() {
        given()
                .baseUri("https://postman-echo.com")  //  URL
                .contentType("application/x-www-form-urlencoded")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .log().all()
                .when()
                .post("/post")
                .then()
                .log().all()
                .statusCode(200)

                // Проверка структуры
                .body("args", equalTo(Collections.emptyMap()))
                .body("data", equalTo(""))
                .body("files", equalTo(Collections.emptyMap()))

                // Проверка данных формы
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))

                // Проверка JSON
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))

                // Проверка заголовков
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.'content-type'", containsString("application/x-www-form-urlencoded"))
                .body("headers.'content-length'", equalTo("19"))
                .body("headers.'x-forwarded-proto'", equalTo("https"));


    }
}
}
