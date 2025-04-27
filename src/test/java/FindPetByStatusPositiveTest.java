import io.restassured.response.Response;
import models.Pet;
import org.junit.Assert;
import org.junit.Test;

public class FindPetByStatusPositiveTest {
    /**
     * Проверка, что при статусе "pending"
     * - возвращается код 200
     * - находится количество записей больше 0
     */
    @Test
    public void findPetByStatusTest() {
        Pet.create(165, "Some pet", "pending");
        Response resp = Pet.getByStatus("pending");
        int numberOfRecords = resp.jsonPath().getList("id").size();
        Assert.assertTrue(numberOfRecords != 0);
    }
}
