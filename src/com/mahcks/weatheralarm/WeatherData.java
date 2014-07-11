package com.mahcks.weatheralarm;

import org.json.*;

public class WeatherData {

	public String weather;
	public String type;
	public double temp_f;
	
	public String icon;
	
	public final String SNOW = "snow";
	public final String RAIN = "rain";
	public final String CLOUDY = "cloudy";
	public final String TSTORM = "tstorm";
	public final String CLEAR = "clear";
	
	public WeatherData(JSONObject data){
		
		weather = (String) data.get("weather");
		type = weatherType((String)data.get("icon"));
		temp_f = (Double) data.get("temp_f");
		icon = (String)data.get("icon");
		
		System.out.println(weather+" "+type+" "+temp_f);
	}
	
	private String weatherType(String type){
		if(type.equals("chanceflurries")||type.equals("flurries"))return SNOW;
		if(type.equals("chancerain")||type.equals("rain"))return RAIN;
		if(type.equals("chancesleet")||type.equals("sleet"))return SNOW;
		if(type.equals("chancesnow")||type.equals("snow"))return SNOW;
		if(type.equals("chancetstorms")||type.equals("tstorms"))return TSTORM;
		if(type.equals("cloudy")||type.equals("mostylecloudy")||type.equals("partlycloudy"))return CLOUDY;
		if(type.equals("clear")||type.equals("mostlysunny"))return CLEAR;
		
		return "unknown";
	}
}
