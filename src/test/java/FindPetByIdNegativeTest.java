import io.restassured.response.Response;
import models.Pet;
import org.junit.Assert;
import org.junit.Test;

public class FindPetByIdNegativeTest {
	/**
	 * Проверка, что при получении записи с несуществующим Id
	 * - возвращается код 404
	 * - выводится сообщение "Pet Not Found"
	 */
	@Test
	public void findPetById_Test() {
		Pet.delete(123);
		Response response = Pet.getById(123);
		Assert.assertEquals(404, response.getStatusCode());
		Assert.assertEquals("Pet not found", response.jsonPath().getString("message"));
	}
}
