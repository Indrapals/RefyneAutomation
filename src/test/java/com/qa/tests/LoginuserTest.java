package com.qa.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.util.LogUtilities;
import com.qa.util.TestBase;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginuserTest extends TestBase {

	String loginapipath;
	String loginapiBody;
	RequestSpecBuilder builder;
	RequestSpecification requestSpec;
	Response response;
	String message = "User logged in successfully.";

	@BeforeClass
	public void setUp() {

		TestBase.init();

		RestAssured.baseURI = prop.getProperty("serviceurl");

		loginapipath = "/auth/login";

		loginapiBody = "{\"mobile\":\"9988776655\",\"password\":\"testuser@123\"}";

		builder = new RequestSpecBuilder();

		builder.setBody(loginapiBody);

		builder.setContentType("application/json; charset=utf-8");

		requestSpec = builder.build();

		response = given().spec(requestSpec).when().post(loginapipath).then().extract().response();

	}

	@Test
	void checkStatusCodeLoginuser() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkstatusLineLoginuser() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkResposeBodyLoginuser() {
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains(message), true);
	}

	@Test
	void checkContentTypeLoginuser() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		LogUtilities.info("Login user test completed");
	}

}
