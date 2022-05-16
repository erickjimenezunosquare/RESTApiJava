package Features;

import java.util.ArrayList;
import java.util.Arrays;
import Deserialize.*;
import Logger.Log;
import Serialize.PostLocationAddBody;
import Serialize.PostLocationAddBodyLocation;
import Serialize.PutLocationUpdateBody;
import URLs.ServicesURLs;
import Utilities.HttpMethods;
import Utilities.TestUtilities;
import io.restassured.RestAssured;

public class MapsFeature extends BaseFeature
{
	private PostLocationAddResponse postLocationAddResponse;
	private PutLocationUpdateResponse putLocationUpdateResponse;
	private GetLocationResponse getLocationResponse;
	
	private String originalAddress = "111 Primera Direccion (Original)";
	private String newAddress = "222 Segunda Direccion (Actualizada)";
	private String keyValue = "qaclick123";
	private String newPlaceId = null;
	
	public MapsFeature()
	{
		ApiURL = ServicesURLs.MainURLMaps;
		RestAssured.baseURI = ApiURL;
	}
	
	public MapsFeature PostAddPlace(String testId, HttpMethods method)
	{
		AssignMainExecutionValues(testId, method);
		Post_AddPlace(testId);
		
		return this;
	}
	
	public MapsFeature PutUpdatePlace(String testId, HttpMethods method)
	{
		AssignMainExecutionValues(testId, method);
		Put_UpdatePlace(testId);
		
		return this;
	}
	
	public MapsFeature GetPlaceDetails(String testId, HttpMethods method)
	{
		AssignMainExecutionValues(testId, method);
		Get_PlaceDetails(testId);
		
		return this;
	}
	
	private void Post_AddPlace(String testId)
	{
		PostLocationAddBody body = null;
		
		Log.StartMethod("Post_AddPlace");
		
		//Determine URL by test id (currently is a switch. Replace with DataDriven in the future)
		LoadFeatureData(testId);
		
		//Create instance (later will be serialized: Java Class --> JSON string)
		body = new PostLocationAddBody();
		
		body.accuracy = 50;
		body.name = "Erick Test";
		body.phone_number = "(+91) 983 893 3937";
		body.address = originalAddress;
		body.website = "https://unosquare.com";
		body.language = "French-IN";
		body.location = new PostLocationAddBodyLocation(-38.383494, 33.427362);
		//https://www.java67.com/2015/10/how-to-declare-arraylist-with-values-in-java.html
		body.types = new ArrayList<>(Arrays.asList("shoe park", "shop"));	
		
		//Replace keys in URL
		EndpointURL = TestUtilities.ReplaceCustomKey(GetEndpointOriginal(), "keyValue", keyValue);
		
		//Execute REST 
		ExecuteRESTRequest(TestUtilities.Serialize(body));
		
		//Deserialize
		postLocationAddResponse = DeserializeResponse(StringResponse, PostLocationAddResponse.class);		
		Log.AssertIsNotNull(postLocationAddResponse, "postLocationAddResponse instance should NOT be null");
		
		//Store place id from deserialized instance
		newPlaceId = postLocationAddResponse.place_id;
		Log.Print("Generated newPlaceId: " + newPlaceId);			
		
		//Validations TODO		
		Log.EndMethod("Post_AddPlace");
	}
	
	private void Put_UpdatePlace(String testId)
	{		
		PutLocationUpdateBody body = null;
		
		Log.StartMethod("Put_UpdatePlace");
		
		//Determine URL by test id (currently is a switch. Replace with DataDriven in the future)
		LoadFeatureData(testId);
		
		body = new PutLocationUpdateBody(newPlaceId, newAddress, keyValue);
		
		//Replace keys in URL
		EndpointURL = TestUtilities.ReplaceCustomKey(GetEndpointOriginal(), "keyValue", keyValue);
		
		//Execute REST 
		ExecuteRESTRequest(TestUtilities.Serialize(body));
		
		//Deserialize
		putLocationUpdateResponse = DeserializeResponse(StringResponse, PutLocationUpdateResponse.class);
		Log.AssertIsNotNull(putLocationUpdateResponse, "putLocationUpdateResponse instance should NOT be null");
		
		//Store place id from deserialized instance
		String message = putLocationUpdateResponse.msg;
		Log.AssertAreEqual("Address successfully updated", message, "Message validation");	
		
		//Validations TODO	
		Log.EndMethod("Put_UpdatePlace");
	}
	
	private void Get_PlaceDetails(String testId)
	{
		Log.StartMethod("Get_PlaceDetails");
		
		//Determine URL by test id (currently is a switch. Replace with DataDriven in the future)
		LoadFeatureData(testId);
		
		//Replace keys in URL
		EndpointURL = TestUtilities.ReplaceCustomKey(GetEndpointOriginal(), "keyValue", keyValue);
		EndpointURL = TestUtilities.ReplaceCustomKey(EndpointURL, "place_idValue", newPlaceId);
		
		//Execute REST 
		ExecuteRESTRequest();
		
		//Deserialize
		getLocationResponse = DeserializeResponse(StringResponse, GetLocationResponse.class);
		Log.AssertIsNotNull(getLocationResponse, "getLocationResponse instance should NOT be null");
		
		//Store place id from deserialized instance
		String getAddress = getLocationResponse.address;
		
		Log.AssertAreEqual(newAddress, getAddress, "Address updated validation");
		Log.AssertAreNotEqual(originalAddress, getAddress, "Address old validation");
		
		//Validations TODO
		Log.EndMethod("Get_PlaceDetails");
	}
}
