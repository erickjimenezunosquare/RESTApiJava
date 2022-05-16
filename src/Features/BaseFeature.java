package Features;

import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Logger.Log;
import Utilities.HttpMethods;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public abstract class BaseFeature 
{
	protected HttpMethods RestMethod;
	protected int ExpectedResponseStatusCode = 200;
	protected String ExpectedServer = "Apache/2.4.41 (Ubuntu)";
	protected String ApiURL; 			//    https://rahulshettyacademy.com
	protected String EndpointURL; 		//   maps/api/place/add/json?key=qaclick123
	protected String CompleteURL; 		//   https://rahulshettyacademy.com/maps/api/place/add/json?key=qaclick123
	protected String StringResponse;    //   JSON response
	protected RequestSpecification RestRequest = null; //Object with REST request
	protected ValidatableResponse RestResponse = null; //Object with REST response
	private ObjectMapper objectMapper = null;   //Object required to deserialize 
	
	private String endpointOriginal = "";
	
	protected String GetEndpointOriginal()
	{
		return endpointOriginal;
	}
	
	private void SetEndpointOriginal(String newStringValue)
	{
		endpointOriginal = newStringValue;
	}	
	
	public BaseFeature()
	{
		objectMapper = new ObjectMapper();
		
		//Establish reference to point to static field from External Library
		RestRequest = RestAssured.requestSpecification;
		
		Log.Print("RestRequest = RestAssured.requestSpecification;");
	}
	
	protected void AssignMainExecutionValues(String testId, HttpMethods method)
	{
		this.RestMethod = method;
		
		Log.Print("Executing [" + method + "] test id: " + testId);
		
		//TODO Pending implementation: Excel Data Driven
	}
	
	protected void LoadFeatureData(String testId)
	{
		//TODO Pending implementation: Excel Data Driven
		SetEndpointOriginal(""); //Reset endpoint value
	
		switch(testId)
		{
			case "Post_PlaceCreation":
				//"maps/api/place/add/json"; //original endpoint without query parameters
				SetEndpointOriginal("maps/api/place/add/json?key={keyValue}");
				ExpectedResponseStatusCode = 200;
				break;
			
			case "Put_PlaceUpdate":
				//"maps/api/place/update/json"; //original endpoint without query parameters
				SetEndpointOriginal("maps/api/place/update/json?key={keyValue}");
				ExpectedResponseStatusCode = 200;
				break;
			
			case "Get_PlaceInfo":
				//"maps/api/place/get/json"; //original endpoint without query parameters
				SetEndpointOriginal("maps/api/place/get/json?key={keyValue}&place_id={place_idValue}");
				ExpectedResponseStatusCode = 200;
				break;
				
			default:
				Log.AssertFail("Unknown test id: " + testId);
		}
		
		CompleteURL = ApiURL + GetEndpointOriginal();
	}	
	
	//----------------------------------------------------- TIER 1 METHODS (PROTECTED) -------------------------------------------------
	
	protected void ExecuteRESTRequest()//No need to receive HttpMethod because it was assigned in public method from child Feature
	{
		switch(RestMethod)
		{
			case DELETE:
				ExecuteDeleteRESTRequest();
				break;
			
			case GET:
				ExecuteGetRESTRequest();
				break;		
		}		
	}
	
	protected void ExecuteRESTRequest(String jsonBody)//No need to receive HttpMethod because it was assigned in public method from child Feature
	{
		switch(RestMethod)
		{
			case POST:
				ExecutePostRESTRequest(jsonBody);
				break;
			
			case PUT:
				ExecutePutRESTRequest(jsonBody);
				break;				
		}		
	}
	
	private void ExecutePostRESTRequest(String jsonBody)
	{
		ResetRESTResponse();
		Log.Print("Hitting 'POST' REST API with URL:" + CompleteURL.replace(".com", ".com/"));
		
		//protected RequestSpecification RestRequest = null; //Object with REST request
		//protected ValidatableResponse RestResponse = null; //Object with REST response
		
		RestResponse =
				given() //return RequestSpecification instance
					.log().all()
					//.queryParam("key", "qaclick123")//NO NEED TO send query params as key-value-pair, since they are already included on URL
					.header("Content-Type","application/json")
					.body(jsonBody)
				.when() //return RequestSpecification instance
					.post(EndpointURL)
				.then() //return ValidatableResponse instance
					.assertThat()
						.statusCode(ExpectedResponseStatusCode)
						.header("server", ExpectedServer);
		
		StringResponse = RestResponse.extract().response().asString();				
		Log.Print("Complete REST Response content: " + StringResponse);
	}
	
	private void ExecutePutRESTRequest(String jsonBody)
	{
		ResetRESTResponse();
		Log.Print("Hitting 'PUT' REST API with URL:" + CompleteURL.replace(".com", ".com/"));
		
		RestResponse =
				given() //return RequestSpecification instance
					.log().all()
					.header("Content-Type","application/json")
					.body(jsonBody)
				.when() //return RequestSpecification instance
					.put(EndpointURL)
				.then() //return ValidatableResponse instance
					.assertThat()
						.statusCode(ExpectedResponseStatusCode)
						.header("server", ExpectedServer);
		
		StringResponse = RestResponse.extract().response().asString();				
		Log.Print("Complete REST Response content: " + StringResponse);
	}
	
	private void ExecuteGetRESTRequest()
	{
		ResetRESTResponse();
		Log.Print("Hitting 'GET' REST API with URL:" + CompleteURL.replace(".com", ".com/"));
		
		RestResponse =
				given() //return RequestSpecification instance
					.log().all()
				.when() //return RequestSpecification instance
					.get(EndpointURL)
				.then() //return ValidatableResponse instance
					.assertThat()
						.statusCode(ExpectedResponseStatusCode)
						.header("server", ExpectedServer);
		
		StringResponse = RestResponse.extract().response().asString();				
		Log.Print("Complete REST Response content: " + StringResponse);
	}
	
	private void ExecuteDeleteRESTRequest()
	{
		ResetRESTResponse();
		Log.Print("Hitting 'DELETE' REST API with URL:" + CompleteURL.replace(".com", ".com/"));
		
		RestResponse =
				given() //return RequestSpecification instance
					.log().all()
				.when() //return RequestSpecification instance
					.delete(EndpointURL)
				.then() //return ValidatableResponse instance
					.assertThat()
						.statusCode(ExpectedResponseStatusCode)
						.header("server", ExpectedServer);
		
		StringResponse = RestResponse.extract().response().asString();
	}
	
	/*
	public <T> T readValue(String content, Class<T> valueType)
    throws JsonProcessingException, JsonMappingException 
    
    ObjectMapper objectMapper = new ObjectMapper();	
	GetLocationResponse getLocationResponse = objectMapper.readValue(getPlaceResponse, GetLocationResponse.class);
	*/	
	
	protected <T> T DeserializeResponse(String responseJson, Class<T> valueType)
	{		
		try
		{
			return objectMapper.readValue(responseJson, valueType);
		}
		catch(JsonMappingException e)
		{
			Log.AssertFail("'JsonMappingException' Failure: " + e.getMessage());
		}
		catch(JsonProcessingException e)
		{
			Log.AssertFail("'JsonProcessingException' Failure: " + e.getMessage());
		}
		
		return null;		
	}
	
	private void ResetRESTResponse()
	{
		RestResponse = null;
	}
}
