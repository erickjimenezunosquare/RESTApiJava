package Helpers;

import Features.MapsFeature;

public class BusinessObjects 
{
	private MapsFeature mapsFeature;
	
	public MapsFeature getMapsFeature()
	{
		if(mapsFeature == null)
		{
			mapsFeature = new MapsFeature();
			return mapsFeature;
		}
		else
		{
			return mapsFeature;
		}
	}
}
