package Files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	
	public static JsonPath RawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}
}
