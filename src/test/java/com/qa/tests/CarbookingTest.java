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

public class CarbookingTest extends TestBase {

	String bookcarpath;
	String carbookBody;
	RequestSpecBuilder builder;
	RequestSpecification requestSpec;
	Response response;
	String bearerToken;
	String message = "Car is already booked for selected dates.";

	@BeforeClass
	public void setUp() {

		TestBase.init();

		RestAssured.baseURI = prop.getProperty("serviceurl");

		bookcarpath = "/car/book";

		bearerToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MDk4YjY3NDJhMmU2ODM0NWNlMzkxZGQiLCJpc0FkbWluIjpmYWxzZSwiaWF0IjoxNjIwNjUxNzA2fQ.1gj5JvCr2BGSEJPXO_K2Z1yVrI90XjgxK6s9uDj0lTo";

		carbookBody = "{\"car_id\": \"6098bb6e1ff5990015be8e26\",\"car_license_number\": \"KA01EM1000\",\"total_rent_bill\": 5100,\"from_date_time\": \"2022-08-27\",\"to_date_time\": \"2022-08-28\"}";

		builder = new RequestSpecBuilder();

		builder.setBody(carbookBody);

		builder.setContentType("application/json; charset=utf-8");

		requestSpec = builder.build();

		response = given().header("Authorization", bearerToken).spec(requestSpec).when().post(bookcarpath).then()
				.extract().response();
	}

	@Test
	void checkStatusCodeCarBooking() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 409);
	}

	@Test
	void checkstatusLineCarBooking() {
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 409 Conflict");

	}

	@Test
	void checkResposeBodyCarBooking() {
		String responseBody = response.getBody().asString();
		System.out.println("responseBody is :" + responseBody);
		Assert.assertEquals(responseBody.contains(message), true);
	}

	@Test
	void checkContentTypeCarBooking() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		LogUtilities.info("Car booking test completed");
	}
}
