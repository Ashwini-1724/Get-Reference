package TestClasspackage;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetReference2 {
	
	@Test
	public static void execute() {

		// step 1 : Declare base URL
		RestAssured.baseURI = "https://restful-api.dev/";

		// Step 2 : Validate response body
		int statusCode = given().header("Content-Type", "application/json").when()
				.get("https://api.restful-api.dev/objects").then().extract().statusCode();

		System.out.println(statusCode);

		String responseBody = given().header("Content-Type", "application/json").when()
				.get("https://api.restful-api.dev/objects").then().extract().response().asString();

		System.out.println(responseBody);

	}

}
