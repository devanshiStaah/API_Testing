package APITesting;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import io.restassured.response.Response;

public class cookiesDemo {

	@Test
	public void cookie() {
		
		given()
		.when()
		.get("https://www.google.com/")
		.then()
			//.cookie("NID","511%3Dggm7gT5xB9H8a7ZFG1__j__6_SsRsjcItji1XGOCh0TFmryGnwPy9jP8qrTcjhphmT7YnLlfdhluz0Ta0qf4z7uexjMPz_056gSxTelvX8yxml2dmAAKuYOfVtzLhb0DBzg0Jz366M-lJUplsd3b4PIp8GgqC0f7MjFirQhzrJs")
			//.log().all();		
			.log().cookies();
	}
	
	//@Test
	public void cookingInfo() {
		
		Response res = given()
				.when()
				.get("https://www.google.com/");
		//System.out.println("Cookies Value ==> " + res.getCookie("NID"));
		Map<String,String> cookies = res.getCookies();
		for(String k : cookies.keySet()) {
			String ck = res.getCookie(k);
			System.out.println(k + ":" + ck);
		}
		
	}
}
