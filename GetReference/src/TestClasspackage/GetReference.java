package TestClasspackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetReference {
	
	@Test
	public static void execute() {
		
	//step 1 : declare base URL
	RestAssured.baseURI="http://restapi.adequateshop.com/";
	
	//step 2 : validate response body and status code
	int statusCode=given().header("Content-Type", "application/json").when().get("api/Tourist?page=2")
			              .then().extract().statusCode();
	
	System.out.println(statusCode);
	
	
	String responseBody=given().header("Content-Type", "application/json").when().get("api/Tourist?page=2")
                               .then().extract().response().asString();
	System.out.println("responseBody : "+responseBody);
	
	//step 3 : validate count
	JsonPath jspResponse = new JsonPath(responseBody);
	int count = jspResponse.getList("data").size();
	System.out.println("Count : "+count);
	
	//step 3 : expected results
	int ids [] = {234157,234158,234159,234160,234161,234162,234163,234164,234165,234166};
    String[] touristname = {"2023May27081759_0003","2023May27081800_0004","2023May27081800_0005","2023May27081800_0006","2023May27081800_0007"
    		           ,"2023May27081800_0008","2023May27081800_0009","2023May27081800_0010","Mike","Mike"};
  
	String[] touristemails = {"2023May27081759_0003@gmail.com","2023May27081800_0004@gmail.com","2023May27081800_0005@gmail.com","2023May27081800_0006@gmail.com",
			          "2023May27081800_0007@gmail.com","2023May27081800_0008@gmail.com","2023May27081800_0009@gmail.com","2023May27081800_0010@gmail.com",
			           "mike12345603@gmail.com","mike12345603@gmail.com"};
	String[] touristlocation = {"USA", "USA", "USA", "USA", "USA", "USA","USA","USA","USA","USA"};
	
	

	// validate each object
	for (int i = 0; i > count; i++) {
		// expected result

		int exp_Id = ids[i];
		String exp_Touristname = touristname[i];
		String exp_Touristemails = touristemails[i];
		String exp_Touristlocation = touristlocation[i];
		
		
		String stringId = jspResponse.getString("data[" + i + "].id");
		int res_Id = Integer.parseInt(stringId);

		String res_Email = jspResponse.getString("data[" + i + "].touristemails");
		
		String res_Name = jspResponse.getString("data[" + i + "].touristnames");
		
		String res_Touristlocation = jspResponse.getString("data[" + i + "].touristlocation");
	

		
		// validate
		Assert.assertEquals(res_Id, exp_Id, "ID at index " + i);
		Assert.assertEquals(res_Email, exp_Touristemails, "Email at index " + i);
		Assert.assertEquals(res_Name, exp_Touristname, "Name at index " + i);
		Assert.assertEquals(res_Touristlocation, exp_Touristlocation, "Location Name at index " + i);
	
	     }
	
	}
}
