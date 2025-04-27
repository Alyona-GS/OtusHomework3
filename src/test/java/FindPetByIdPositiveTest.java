import io.restassured.response.Response;
import models.Pet;
import org.junit.Assert;
import org.junit.Test;

public class FindPetByIdPositiveTest {
    /**
     * Проверка, что созданную запись можно найти по Id
     * - возвращается код 200
     * - поля ответа соответствуют запросу
     */
    @Test
    public void findPetByIdTest() {
        Pet.delete(126);
        Pet.create(126, "Cat Vasya", "available");
        Response response = Pet.getById(126);
        Assert.assertEquals(126, (int)response.jsonPath().get("id"));
        Assert.assertEquals("Cat Vasya", response.jsonPath().get("name"));
    }
}
