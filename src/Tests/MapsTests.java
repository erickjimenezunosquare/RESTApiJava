package Tests;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Utilities.HttpMethods;

public class MapsTests extends ErickTests 
{
	/*
	@Test
	public void AddPlace()
	{
		BusinessObjects.getMapsFeature()
			.PostAddPlace("Post_PlaceCreation", HttpMethods.POST);
	}
	*/
	
	//https://www.javatpoint.com/convert-java-object-to-json
	
	@Test
	public void AddUpdateAndGetPlace()
	{
		BusinessObjects.getMapsFeature()
			.PostAddPlace("Post_PlaceCreation", HttpMethods.POST)
			.PutUpdatePlace("Put_PlaceUpdate", HttpMethods.PUT)
			.GetPlaceDetails("Get_PlaceInfo", HttpMethods.GET);
	}
}
