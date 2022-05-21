package IBM_May.RestAssured1;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class datafromjsonfile {

	public static Response res = null;
	public static int statuscode = 0; 
	public static FileInputStream jsonfile = null;
	
	@Test(enabled = true, description = "Perform the POST Operation with escape char, perform assertions for success code")
	public void testcase1()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		String body = "[{\"id\":0,\"username\":\"Swat\",\"firstName\":\"Swat\",\"lastName\":\"Rat\",\"email\":\"rat.swat@test.com\",\"password\":\"swatrat*123\",\"phone\":\"9198765430\",\"userStatus\":1}]";
	
		try {
			res = given().header("Content-Type" , "application/json").body(body).
					when().post("/user/createWithArray");
			
					statuscode = res.getStatusCode();
					Assert.assertEquals(statuscode, 200);
					System.out.println("Actual status code matching with expected status code :" + statuscode);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}
	
	
	@Test(enabled = true, description = "Perform the Get Operation, perform assertions for success code")
	public void testcase2()
	{	
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		try {	
			given().
			    queryParam("username", "Swati").
			when()
				.get("/user/{username}");
			
			statuscode = res.getStatusCode();
			Assert.assertEquals(statuscode, 200);
			System.out.println("Actual status code matching with expected status code :" + statuscode);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}
	
	
	@Test(enabled = true, description = "Perform the Get Operation, perform assertions for 404 code")
	public void testcase3()
	{	
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		try {	
			given().
			    queryParam("username", "Ratna").
			when()
				.get("/user/{username}");
			
			statuscode = res.getStatusCode();
			Assert.assertEquals(statuscode, 404);
			System.out.println("Actual status code matching with expected status code :" + statuscode);
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}
	
	
	@Test(enabled = true, description = "Perform the POST Operation with JSON File, perform assertions for success code")
	public void testcase4() throws FileNotFoundException
	{
		jsonfile = new FileInputStream(new File(System.getProperty("user.dir"))+"\\testdata\\jsonfile.json");
	
		try {
			res = given().body(IOUtils.toString(jsonfile))
					.header("Content-Type" , "application/json").
					when().post("https://petstore.swagger.io/v2/user/createWithArray");
			
					statuscode = res.getStatusCode();
					Assert.assertEquals(statuscode, 200);
					System.out.println("Actual status code matching with expected status code :" + statuscode);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());	 
	}
	
	
	@Test(enabled = true, description = "Post method with JSON File, perform assertions for 404 code")
	public void testcase5() throws FileNotFoundException
	{
		jsonfile = new FileInputStream(new File(System.getProperty("user.dir"))+"\\testdata\\jsonfile.json");
		
		try {
			res = given().body(IOUtils.toString(jsonfile))
					.header("Content-Type" , "application/json").
					when().post("https://petstore.swagger.io/v5/user/createWithList");
					
					statuscode = res.getStatusCode();
					Assert.assertEquals(statuscode, 404);
					System.out.println("Actual status code matching with expected status code :" + statuscode);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}

	@Test(enabled = true, description = "Perform the PUT Operation with escape characters, perform assertions for success code")
	public void testcase6() {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2";

			String body = "{\"id\":0,\"username\":\"Swat\",\"firstName\":\"Swati\",\"lastName\":\"Ratna\",\"email\":\"ratna@test.com\",\"password\":\"swatrat*123\",\"phone\":\"9108765475\",\"userStatus\":0}";

			given().header("content-type", "application/json").body(body).when().post("/user/{username}");
			
			statuscode = res.getStatusCode();
			Assert.assertEquals(statuscode, 404);
			System.out.println("Actual status code matching with expected status code :" + statuscode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}

	@SuppressWarnings("unchecked")
	@Test(enabled = true, description = "Perform the PUT Operation with JSON object, perform assertions for 404 code")
	public void testcase7() {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2";

			JSONObject parent = new JSONObject();

			parent.put("id", 0);
			parent.put("username", "Swati");
			parent.put("firstName", "Swat");
			parent.put("lastName", "Ratna");
			parent.put("email", "ratna@test.com");
			parent.put("password", "swatrat@123");
			parent.put("phone", "9198765432");
			parent.put("userStatus", 0);

			given().header("content-type", "application/json").body(parent.toJSONString()).when()
					.post("/user/{username}");
			
			statuscode = res.getStatusCode();
			Assert.assertEquals(statuscode, 404);
			System.out.println("Actual status code matching with expected status code :" + statuscode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}

	@Test(enabled = true, description = "Perform the Delete Operation , perform assertions for 404 code")
	public void testcase8() {
		try {
			RestAssured.baseURI = "https://petstore.swagger.io/v2";

			given().
			queryParam("username", "Swati").
			when().
			delete("/user/{username}");
			
			statuscode = res.getStatusCode();
			Assert.assertEquals(statuscode, 404);
			System.out.println("Actual status code matching with expected status code :" + statuscode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res.asString());
	}
}

