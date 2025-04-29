package models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pet {
    public Pet() {}
    public void create(int id, String name, String status) {
        String animal = getStringFromFile("./src/main/resources/createPet.json");
        animal = animal
                .replaceAll("\"petId\"", "" + id)
                .replaceAll("animal", name)
                .replaceAll("Status", status);
        RestAssured.given()
                .body(animal)
                .contentType(ContentType.JSON)
                .when()
                .post("/pet")
                .then()
                .statusCode(200);
    }

    public void delete(int id) {
        RestAssured.given()
                .pathParam("petId", id)
                .when()
                .delete("/pet/{petId}");
    }

    private String getStringFromFile(String filePath) {
        String content = null;
        try {
            content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            new RuntimeException("Generating file failed", e);
        }
        return content;
    }

    public Response getById(int id) {
        return RestAssured.given()
                .pathParam("petId", id)
                .when()
                .get("/pet/{petId}")
                .then()
                .extract()
                .response();
    }

    public Response getByStatus(String status) {
        return RestAssured.given()
                .param("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .extract()
                .response();
    }
}
