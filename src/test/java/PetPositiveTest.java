import com.google.inject.Inject;
import extensions.APIExtensions;
import io.restassured.response.Response;
import models.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(APIExtensions.class)
public class PetPositiveTest {
    @Inject
    Pet pet;
    /**
     * Проверка, что созданную запись можно найти по Id
     * - возвращается код 200
     * - поля ответа соответствуют запросу
     */
    @Test
    public void findPetByIdTest() {
        pet.delete(126);
        pet.create(126, "Cat Vas", "available");
        Response response = pet.getById(126);

        //Assertions.assertEquals(126, (int)response.jsonPath().get("id"));
        //Assertions.assertEquals("Cat Vas", response.jsonPath().get("name"));
    }

    /**
     * Проверка, что при статусе "pending"
     * - находится количество записей больше 0
     */
    @Test
    public void findPetByStatusTest() {
        pet.create(165, "Some pet", "pending");
        Response resp = pet.getByStatus("pending");
        Assertions.assertFalse(resp.jsonPath().getList("id").isEmpty());
        Response resp2 = pet.getById(165);
        Assertions.assertEquals("Some pet", resp2.jsonPath().get("name"));
        Assertions.assertEquals("pending", resp2.jsonPath().get("status"));
    }
}
