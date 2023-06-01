package TestClasspackage;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetReference3 {
	
	@Test
	public static void execute() {
		// declare base URL
		RestAssured.baseURI = "https://reqres.in/";

		// step 2 : Validate response body and status code

		int statusCode = given().header("Content-Type", "application/json").when().get("api/users?page=2").then()
				.extract().statusCode();
		System.out.println("statusCode : " + statusCode);

		String responseBody = given().header("Content-Type", "application/json").when().get("api/users?page=2").then()
				.extract().response().asString();
		System.out.println("responseBody : " + responseBody);

		// step 3 : expected results
		int ids[] = { 7, 8, 9, 10, 11, 12 };
		String[] emails = { "michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in",
				"byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in" };
		String[] firstNames = { "Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel" };
		String[] lastNames = { "Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell" };
		String[] avatars = { "https://reqres.in/img/faces/7-image.jpg", "https://reqres.in/img/faces/8-image.jpg",
				"https://reqres.in/img/faces/9-image.jpg", "https://reqres.in/img/faces/10-image.jpg",
				"https://reqres.in/img/faces/11-image.jpg", "https://reqres.in/img/faces/12-image.jpg" };

		JsonPath jspResponse = new JsonPath(responseBody);
		int count = jspResponse.getList("data").size();
		System.out.println("Count : " + count);

		// validate each object
		for (int i = 0; i < count; i++) {
			// expected result

			int exp_Id = ids[i];
			String exp_Email = emails[i];
			String exp_FirstName = firstNames[i];
			String exp_LastName = lastNames[i];
			String exp_Avatar = avatars[i];

			String stringId = jspResponse.getString("data[" + i + "].id");
			int res_Id = Integer.parseInt(stringId);

			String res_Email = jspResponse.getString("data[" + i + "].email");

			String res_FirstName = jspResponse.getString("data[" + i + "].first_name");

			String res_LastName = jspResponse.getString("data[" + i + "].last_name");

			String res_Avatar = jspResponse.getString("data[" + i + "].avatar");

			// validate
			Assert.assertEquals(res_Id, exp_Id, "ID at index " + i);
			Assert.assertEquals(res_Email, exp_Email, "Email at index " + i);
			Assert.assertEquals(res_FirstName, exp_FirstName, "First Name at index " + i);
			Assert.assertEquals(res_LastName, exp_LastName, "First Name at index " + i);
			Assert.assertEquals(res_Avatar, exp_Avatar, "Avatar at index " + i);

		}
	}

}