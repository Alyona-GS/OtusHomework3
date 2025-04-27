package models;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pet {
	public static void create(int id, String name, String status) {
		String animal = getStringFromFile("./src/main/resources/createPet.json");
		animal = animal
				.replaceAll("\"petId\"", "" + id)
				.replaceAll("animal", name)
				.replaceAll("status", status);
		RestAssured.given().baseUri("https://petstore.swagger.io/v2")
				.body(animal)
				.contentType(ContentType.JSON)
				.when()
				.post("/pet")
				.then()
				.statusCode(200);
	}

	public static void delete(int id) {
		RestAssured.given().baseUri("https://petstore.swagger.io/v2")
				.pathParam("petId", id)
				.when()
				.delete("/pet/{petId}");
	}

	private static String getStringFromFile(String filePath) {
		String content = null;
		try {
			content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			new RuntimeException("Generating file failed", e);
		}
		return content;
	}

	public static Response getById(int id) {
		return RestAssured.given().baseUri("https://petstore.swagger.io/v2")
				.pathParam("petId", id)
				.when()
				.get("/pet/{petId}")
				.then()
				.extract()
				.response();
	}

	public static Response getByStatus(String status) {
		return RestAssured.given().baseUri("https://petstore.swagger.io/v2")
				.param("status", status)
				.when()
				.get("/pet/findByStatus")
				.then()
				.extract()
				.response();
	}
}
