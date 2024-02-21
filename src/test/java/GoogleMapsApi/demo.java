package GoogleMapsApi;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class demo {
	
//	given()
//	.when()
//	.then();
	@Test
	public void demo() {
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//Add Place
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
						+ "  \"name\": \"Devnashi house\",\n"
						+ "  \"phone_number\": \"(+91) 986 893 3937\",\n"
						+ "  \"address\": \"118, side layout, cohen 09\",\n"
						+ "  \"types\": [\n"
						+ "    \"shoe park\",\n"
						+ "    \"shop\"\n"
						+ "  ],\n"
						+ "  \"website\": \"http://google.com\",\n"
						+ "  \"language\": \"French-IN\"\n"
						+ "}")
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
			String id = js.getString("place_id");
			System.out.println("IDaaaaa :"+id);

		
		//Updateplace
			// Update place
			given()
			    .queryParam("key", "qaclick123")
			    .header("Content-Type", "application/json")
			    .body("{\n"
			            + "\"place_id\":\"" + id + "\",\n"
			            + "\"address\":\"399 Summer walk, USA\",\n"
			            + "\"key\":\"qaclick123\"\n"
			            + "}")
			.when()
			    .put("maps/api/place/update/json") // Correct endpoint for updating
			.then()
			    .log().all();

		
		//get updated place			
		String addressResponse = given()
				.queryParam("key", "qaclick123")
				.queryParam("place_id", id)
			    .when()
			    .get("maps/api/place/get/json")
			    .then()
			    .extract().asString();

			System.out.println("Full Response: " + addressResponse);

			JsonPath js1 = new JsonPath(addressResponse);
			String placeId = js1.getString("address");

			System.out.println("Place ID: " + placeId);


	}

}
