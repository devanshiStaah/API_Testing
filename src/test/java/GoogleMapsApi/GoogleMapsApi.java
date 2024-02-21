package GoogleMapsApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoogleMapsApi {
	
	//https://rahulshettyacademy.com/maps/api/place/add/json?key= qaclick123
	String id;
	@Test(priority=1)
	public void addPlace() {
		RestAssured.baseURI="https://rahulshettyacademy.com";

		String response = given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body("{\n"
					+ "  \"location\": {\n"
					+ "    \"lat\": -38.383494,\n"
					+ "    \"lng\": 33.427362\n"
					+ "  },\n"
					+ "  \"accuracy\": 50,\n"
					+ "  \"name\": \"Frontline house\",\n"
					+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
					+ "  \"address\": \"29, side layout, cohen 09\",\n"
					+ "  \"types\": [\n"
					+ "    \"shoe park\",\n"
					+ "    \"shop\"\n"
					+ "  ],\n"
					+ "  \"website\": \"http://google.com\",\n"
					+ "  \"language\": \"French-IN\"\n"
					+ "}\n"
					+ "")
		.when()
			.post("maps/api/place/add/json")
		.then()
			.log().all()
			.assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		 id = js.getString("place_id");
	}
	
	@Test(priority=2)
	public void updatePlace() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given()
			.log().all()
			.queryParam("place_id", id)
			.queryParam("key", "qaclick123")			
			.body("{\n"
					+ "\"address\":\"70 Summer walk, USA\",\n"
					+ "\"key\":\"qaclick123\"\n"
					+ "}\n"
					+ "")
		.when()
			.put("maps/api/place/get/json")
		.then()
			.log().all();
	}
	
	@Test(priority=3)
	public void getPlace() {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given()
			.queryParam("place_id", id)
			.queryParam("key", "qaclick123")
		.when()
			.get("maps/api/place/get/json")
		.then()
			.log().all();
	}
	
	@Test(priority = 4)
	public void deletePlace() {
	    RestAssured.baseURI = "https://rahulshettyacademy.com";
	    given()
	        .queryParam("place_id", id)
	        .queryParam("key", "qaclick123")
	    .when()
	        .delete("maps/api/place/delete/json")
	    .then()
	        .log().all()
	        .assertThat().statusCode(200); // Update the assertion to check for status code 200
	}

}
