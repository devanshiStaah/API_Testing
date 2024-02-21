package APITesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class quey_and_pathParameter {

	//https://reqres.in/api/users?page=2
	
	@Test
	public void quey_and_path_parameter() {
		
		given()
			.pathParam("mypath", "users")
			.queryParam("page", "2")
			.queryParam("id", "12")
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
