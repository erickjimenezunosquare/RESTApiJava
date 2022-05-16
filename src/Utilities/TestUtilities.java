package Utilities;

import com.google.gson.Gson;

import Logger.Log;

public class TestUtilities 
{
	public static String ReplaceCustomKey(String text, String keyToBeReplaced, String keyNewValue)
	{
		String finalText = text.replace("{" + keyToBeReplaced + "}", keyNewValue);
		
		return finalText;
	}
	
	public static String Serialize(Object object)
	{
		String jsonBody = new Gson().toJson(object);		
		Log.Print("Serialized body: " + jsonBody);
		
		return jsonBody;
	}
}
