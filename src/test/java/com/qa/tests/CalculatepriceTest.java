package com.qa.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.util.LogUtilities;
import com.qa.util.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CalculatepriceTest extends TestBase {

	String calculatepicepath;
	String bearerToken;
	Response response;
	String totalRentAmount = "8550";

	@BeforeClass
	public void setUp() {
		TestBase.init();
		RestAssured.baseURI = prop.getProperty("serviceurl");
		bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MDk4YjY3NDJhMmU2ODM0NWNlMzkxZGQiLCJpc0FkbWluIjpmYWxzZSwiaWF0IjoxNjIwNjUxNzA2fQ.1gj5JvCr2BGSEJPXO_K2Z1yVrI90XjgxK6s9uDj0lTo";
		calculatepicepath = "/car/calculate-price/6098bafd1ff5990015be8e20/2021-05-23/2021-05-24";
		response = given().header("Authorization", bearerToken).when().get(calculatepicepath).then().extract()
				.response();
	}

	@Test
	void checkStatusCodeCalculateprice() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkstatusLineCalculateprice() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkResposeBodyCalculateprice() {
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains(totalRentAmount), true);
	}

	@Test
	void checkContentTypeCalculateprice() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		LogUtilities.info("Calculate price test completed");
	}

}
