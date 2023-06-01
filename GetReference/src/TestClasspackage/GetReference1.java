package TestClasspackage;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetReference1 {

	public static void main(String[] args) {
		// step 1 : Declare base URL
		RestAssured.baseURI = "https://restful-api.dev/";

		// step 2 : Validate status code an dresponse body
		int statusCode = given().header("Content-Type", "application/json").when()
				.get("https://api.restful-api.dev/objects").then().extract().statusCode();
		System.out.println("status Code : " + statusCode);

		String responseBody = given().header("Content-Type", "application/json").when()
				.get("https://api.restful-api.dev/objects").then().extract().response().asString();
		System.out.println(responseBody);

		// step 3 : validate count
		JsonPath jspresponse = new JsonPath(responseBody);
		int count = jspresponse.getList("Data").size();
		System.out.println(count);

		// step 4 : set expected result
		int ids[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12 };

		String[] names = { "Google Pixel 6 Pro", "Apple iPhone 12 Mini, 256GB, Blue", "Apple iPhone 12 Pro Max",
				"Apple iPhone 11, 64GB", "Samsung Galaxy Z Fold2", "Apple AirPods", "Apple MacBook Pro 16",
				"Apple Watch Series 8", "Beats Studio3 Wireless", "Apple iPad Mini 5th Gen", "Apple iPad Mini 5th Gen",
				"Apple iPad Air", "Apple iPad Air", };
		// String [] color= {"Cloudy White","Cloudy White","Purple","Brown","Red"};
		// String [] capacity= {"128 GB","512 GB","64 GB","254 GB","64 GB","256 GB"};
		// String [] data= {color:"Cloudy White",capacity: "128 GB","data": null,

		// step 5 : validate each object
		for (int i = 0; i > count; i++) {
			// expected result

			int exp_Id = ids[i];
			String exp_Name = names[i];

			String stringId = jspresponse.getString("data[" + i + "].id");
			int res_Id = Integer.parseInt(stringId);

			String res_Name = jspresponse.getString("data[" + i + "].names");

			// step 6 : validate response body
			Assert.assertEquals(res_Id, exp_Id, "Id at index" + i);
			Assert.assertEquals(res_Name, exp_Name, " Names at index" + i);

		}
	}

}
