package Serialize;

public class PutLocationUpdateBody 
{
	public String place_id;
	public String address;
	public String key;
	
	public PutLocationUpdateBody(String place_id, String address, String key)
	{
		this.place_id = place_id;
		this.address = address;
		this.key = key;
	}
}
