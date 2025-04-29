import com.google.inject.Inject;
import extensions.APIExtensions;
import io.restassured.response.Response;
import models.Pet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(APIExtensions.class)
public class PetNegativeTest {
    @Inject
    Pet pet;
    /**
     * Проверка, что при некорректном статусе
     * - возвращается код 400
     */
    @Test
    public void findPetByInvalidStatusTest() {
        Response response = pet.getByStatus("d");
        int numberOfRecords = response.jsonPath().getList("id").size();
        Assert.assertEquals(0, numberOfRecords);
        Assert.assertEquals(400, response.statusCode());
    }

    /**
     * Проверка, что при получении записи с несуществующим Id
     * - возвращается код 404
     * - выводится сообщение "Pet Not Found"
     */
    @Test
    public void findPetByIdTest() {
        pet.delete(123);
        Response response = pet.getById(123);
        Assert.assertEquals(404, response.getStatusCode());
        Assert.assertEquals("Pet not found", response.jsonPath().getString("message"));
    }
}
