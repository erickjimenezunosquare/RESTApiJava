package com.ejimenez;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1

import Deserialize.GetLocationResponse;

import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
import com.fasterxml.jackson.core.JsonProcessingException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import Files.ReUsableMethods;
import Logger.Log;

public class PostMapLocation 
{
	public static void main(String[] args)
	{
		
	}
	
	
	/*
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException 
	{
		//validate if Add Place API is working as expected
		
		//given - all input details
		//when - submit the API (resource/endpoint and http method)
		//then - validate the response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		//..............................................................................................................................
		//..................................................... STEP 1 - ADD PLACE .....................................................
		//..............................................................................................................................
		
		String originalAddress = "111 Primera Direccion (Original)";
		String response=
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type","application/json")
			.body("{\r\n"
					+ "	\"location\":{\r\n"
					+ "		\"lat\": -38.383494,\r\n"
					+ "		\"lng\": 33.427362\r\n"
					+ "	},\r\n"
					+ "	\"accuracy\": 50,\r\n"
					+ "	\"name\": \"Lisani\",\r\n"
					+ "	\"phone_number\": \"(+91) 983 893 3937\",\r\n"
					+ "	\"address\": \"111 primera direccion\",\r\n"
					+ "	\"types\": [\r\n"
					+ "		\"shoe park\",\r\n"
					+ "		\"shop\"\r\n"
					+ "	],\r\n"
					+ "	\"website\": \"https://rahulshettyacademy.com\",\r\n"
					+ "	\"language\": \"French-IN\"\r\n"
					+ "}")
		.when()
			.post("maps/api/place/add/json")
		.then()
			.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		Log.Print(response);
		
		JsonPath js=new JsonPath(response); //for parsing Json
		String placeId=js.getString("place_id");
		
		Log.Print(placeId);
		Log.PrintRowLineWithId();
		
		//..............................................................................................................................
		//................................................... STEP 2 - UPDATE PLACE ....................................................
		//..............................................................................................................................
		
		String newAddress = "222 Segunda Direccion (Nueva)";
		
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type","application/json")
			.body("{\r\n" + 
				"\"place_id\":\"" + placeId + "\",\r\n" + 
				"\"address\":\"" + newAddress + "\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when()
			.put("maps/api/place/update/json")
		.then()
			.assertThat().log().all()
				.statusCode(200)
				.body("msg", equalTo("Address successfully updated"));
		
		Log.PrintRowLineWithId();
		
		//..............................................................................................................................
		//..................................................... STEP 3 - GET PLACE .....................................................
		//..............................................................................................................................
		
		String getPlaceResponse = 
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.queryParam("place_id", placeId)
		.when()
			.get("maps/api/place/get/json")
		.then()
			.assertThat().log().all()
				.statusCode(200)
				.extract().response().asString();
		
		JsonPath js1=ReUsableMethods.RawToJson(getPlaceResponse);
		String actualAddress =js1.getString("address");

		Log.Print(actualAddress);		
		Log.AssertAreEqual(actualAddress, newAddress, "Address validation");
		Log.PrintRowLineWithId();
		
		//..............................................................................................................................
		//................................................... STEP 4 - Deserialize .....................................................
		//..............................................................................................................................
		
		// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
		// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
		//ObjectMapper om = new ObjectMapper();
		//Root root = om.readValue(myJsonString), Root.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		//GetLocationResponse getLocationResponse0 = null;		
		GetLocationResponse getLocationResponse = objectMapper.readValue(getPlaceResponse, GetLocationResponse.class);	
		
		Log.Print("Location > Latitude :: " + getLocationResponse.location.latitude);
		Log.Print("Location > Longitude :: " + getLocationResponse.location.longitude);
		Log.Print("Accuracy :: " + getLocationResponse.accuracy);
		Log.Print("Name :: " + getLocationResponse.name);
		Log.Print("Phone number :: " + getLocationResponse.phone_number);
		Log.Print("Address :: " + getLocationResponse.address);
		Log.Print("Types :: " + getLocationResponse.types);
		Log.Print("Website :: " + getLocationResponse.website);
		Log.Print("Language :: " + getLocationResponse.language);
		
		Log.PrintRowLineWithId();
	}*/
}
