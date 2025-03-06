import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Test;

public class FindPetByStatusTest {
    /**
     * Проверка, что при статусе "pending"
     * - возвращается код 200
     * - находится количество записей больше 0
     */
    @Test
    public void findPetByStatus_OkTest() {
         int numberOfRecords = (int) RestAssured.given().baseUri("https://petstore.swagger.io/v2")
                 .param("status", "pending")
                 .when()
                 .get("/pet/findByStatus")
                 .then()
                 .assertThat()
                 .statusCode(200)
                 .extract()
                 .response().body().jsonPath().getList("id").size();
        Assert.assertTrue(numberOfRecords != 0);
    }

    /**
     * Проверка, что при некорректном статусе
     * - возвращается код 400
     */
    @Test
    public void findPetByStatus_InvalidStatusTest() {
        RestAssured.given().baseUri("https://petstore.swagger.io/v2")
                .param("status", "d")
                .when()
                .get("/pet/findByStatus")
                .then()
                .assertThat()
                .statusCode(400);
    }
}
