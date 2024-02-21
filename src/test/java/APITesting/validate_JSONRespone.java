package APITesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class validate_JSONRespone {
	
	@Test
	public void JSONResponse() {
		
		
		//Approach 1
		/*given()
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.body("[3].name", equalTo("Savitri"));*/
		
		// Approach 2
				Response res = given().when().get("http://localhost:3000/students");
				Assert.assertEquals(res.getStatusCode(), 200);
				
				//Converting json object reposnse to string
				String sname = res.jsonPath().get("[3].name").toString();
				Assert.assertEquals(sname, "Savitri");
	}

}
