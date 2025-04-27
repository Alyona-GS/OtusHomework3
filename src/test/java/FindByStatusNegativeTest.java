import io.restassured.RestAssured;
import org.junit.Test;

public class FindByStatusNegativeTest {
	/**
	 * Проверка, что при некорректном статусе
	 * - возвращается код 400
	 */
	@Test
	public void findPetByInvalidStatusTest() {
		RestAssured.given().baseUri("https://petstore.swagger.io/v2")
				.param("status", "d")
				.when()
				.get("/pet/findByStatus")
				.then()
				.assertThat()
				.statusCode(400);
	}
}
