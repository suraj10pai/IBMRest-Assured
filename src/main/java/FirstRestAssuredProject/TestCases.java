package FirstRestAssuredProject;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestCases {
	
	@Test(priority=1)
	public void Post() throws IOException {
		
		
		FileInputStream fs= new FileInputStream(new File(System.getProperty("user.dir")+"\\data\\Post.json"));
		
		Response res=BaseClass.BaseClassMethod(IOUtils.toString(fs),"POST","/user","");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}
	
	
	@Test(priority=2)
	public void Get() throws IOException {
		
		
		Response res=BaseClass.BaseClassMethod("AtulK","GET","/user","");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		
	}
	
	@Test(priority=3)
	public void Put() throws IOException {
		
		FileInputStream fs= new FileInputStream(new File(System.getProperty("user.dir")+"\\data\\Put.json"));
		
		Response res=BaseClass.BaseClassMethod(IOUtils.toString(fs),"PUT","/user","AtulK");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		
	}
	
	@Test(priority=4)
	public void Delete() throws IOException {
		
		Response res=BaseClass.BaseClassMethod("","DELETE","/user","AtulK");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
		
	}
	
	@Test(priority=5)
    public void DeleteNegativeScenario() throws IOException {
		
		Response res=BaseClass.BaseClassMethod("","DELETE","/user","Anything");
		
		Assert.assertEquals(res.getStatusCode(), 404);
		
	}
	@Test(priority=6)
	public void PostMethodUsingStringBody(){//4.Perform the POST Operation with escape char , perform assertions for success code
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		String body="{\"username\":\"sonaliG\",\"firstName\":\"sonali\",\"lastName\":\"gupta\",\"email\":\"sonalig@.com\",\"password\":\"12345\",\"phone\":\"888888888\",\"userStatus\":0}";
		Response resp=given()
		//.header("content-type","application/json")
		.contentType(ContentType.JSON)
		.body(body).
		when()
		.post("/user");
		System.out.println(resp.asString());
		assertEquals(resp.getStatusCode(),200);
	
	}
	@Test(priority=7)
	public void PutMethodUsingStringBody(){//6. Perform the PUT Operation with escape char, perform assertions for success code
		RestAssured.baseURI= "https://petstore.swagger.io/v2";
		String body="{\"username\":\"sonaliGupta\",\"firstName\":\"sonali\",\"lastName\":\"gupta\",\"email\":\"sonalig@.com\",\"password\":\"12345\",\"phone\":\"888888888\",\"userStatus\":0}";
		Response resp=given()
		//.header("content-type","application/json")
		.contentType(ContentType.JSON)
		.body(body).
		when()
		.put("/user/sonaliG");
		System.out.println(resp.asPrettyString());
		assertEquals(resp.getStatusCode(),200);
	}
	
}