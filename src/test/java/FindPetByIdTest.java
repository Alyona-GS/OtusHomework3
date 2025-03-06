import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

public class FindPetByIdTest {
    /**
     * Проверка, что созданную запись можно найти по Id
     * - возвращается код 200
     * - поля ответа соответствуют запросу
     */
    @Test
    public void findPetById_OkTest() {
        JsonPath responseJson = RestAssured.given().baseUri("https://petstore.swagger.io/v2")
                .pathParam("petId", 10)
                .when()
                .get("/pet/{petId}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().body().jsonPath();
        Assert.assertEquals(10, (int)responseJson.get("id"));
        Assert.assertEquals("doggie", responseJson.get("name"));
    }

    /**
     * Проверка, что при получении записи с несуществующим Id
     * - возвращается код 404
     * - выводится сообщение "Pet Not Found"
     */
    @Test
    public void findPetById_NotFoundTest() {
        RestAssured.given().baseUri("https://petstore.swagger.io/v2")
            .pathParam("petId", 123)
            .when()
            .delete("/pet/{petId}");
        String message = RestAssured.given().baseUri("https://petstore.swagger.io/v2")
                .pathParam("petId", 123)
                .when()
                .get("/pet/{petId}")
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .response().body().jsonPath().getString("message");
        Assert.assertEquals("Pet not found", message);
    }
}
