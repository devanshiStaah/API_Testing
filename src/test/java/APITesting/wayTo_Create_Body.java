package APITesting;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.ResponseBodyExtractionOptions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import io.restassured.response.Response;

public class wayTo_Create_Body {
	
	String id;
	//@Test(priority = 1)
	public void using_Hashmap() {

	    HashMap data = new HashMap();
	    data.put("name", "Riya");
	    data.put("age", 23);
	    String courses[] = {"C#", "C++"};
	    data.put("courses", courses);

	    Response response = given()
	            .contentType("application/json")
	            .body(data)
	            .when()
	            .post("http://localhost:3000/students")
	            .then()
	            .statusCode(201)
	            .extract().response();

	    id = response.jsonPath().getString("id");
	}
	
	//@Test(priority = 1)
	public void org_json() {
	    JSONObject data = new JSONObject();
	    data.put("name", "Riya");
	    data.put("age", 23);
	    String courses[] = {"C#", "C++"};
	    data.put("courses", courses);

	    System.out.println("Request Body: " + data.toString()); // Add this line for debugging

	    Response response = given()
	            	.contentType("application/json")
	            	.body(data.toString()) // Ensure the body is a JSON string
	            .when()
	            	.post("http://localhost:3000/students")
	            .then()
	            	.statusCode(201)
	            	.extract().response();

	    id = response.jsonPath().getString("id");
	}
	
	//@Test(priority = 1)
	public void using_pojo() {
		
		POJO_class data = new POJO_class();
		data.setName("Raj");
		data.setAge("26");
		String courses[] = {"Java", "PHP"};
		data.setCourses(courses);

	    Response response = given()
	            	.contentType("application/json")
	            	.body(data) // Ensure the body is a JSON string
	            .when()
	            	.post("http://localhost:3000/students")
	            .then()
	            	.statusCode(201)
	            	.extract().response();

	    id = response.jsonPath().getString("id");
		
		
	}
	
	@Test(priority = 1)
	public void ex_json_file() throws Exception {
		
		File file = new File("/home/staah/Devanshi/eclipse_projects/API_Test/body.json");
		FileReader fr = new FileReader(file);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		

	    Response response = given()
	            	.contentType("application/json")
	            	.body(data.toString()) // Ensure the body is a JSON string
	            .when()
	            	.post("http://localhost:3000/students")
	            .then()
	            	.statusCode(201)
	            	.extract().response();

	    id = response.jsonPath().getString("id");
		
	}
	
	//@Test(priority=2)
	public void deleteStudent() {
		
		given()
		.when()
		.delete("http://localhost:3000/students/"+id)
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority=2)
	public void getStudents() {
		given()
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.log().all();
	}
}
