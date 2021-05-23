package com.qa.tests;

import static io.restassured.RestAssured.given;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.util.LogUtilities;
import com.qa.util.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SearchcarTest extends TestBase {

	String searchcarpath;
	String bearerToken;
	Response response;

	@BeforeClass
	public void setUp() {
		DOMConfigurator.configure("log4j.xml");
		TestBase.init();
		RestAssured.baseURI = prop.getProperty("serviceurl");
		bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MDk4YjY3NDJhMmU2ODM0NWNlMzkxZGQiLCJpc0FkbWluIjpmYWxzZSwiaWF0IjoxNjIwNjUxNzA2fQ.1gj5JvCr2BGSEJPXO_K2Z1yVrI90XjgxK6s9uDj0lTo";
		searchcarpath = "/car/search-cars/2021-05-23/2021-05-24";
		response = given().header("Authorization", bearerToken).when().get(searchcarpath).then().extract().response();
	}

	@Test
	void checkStatusCodeSearchcar() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkstatusLineSearchcar() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContentTypeSearchcar() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		LogUtilities.info("Serach car test completed");
	}

}
