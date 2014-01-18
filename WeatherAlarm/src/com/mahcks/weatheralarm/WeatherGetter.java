package com.mahcks.weatheralarm;

import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.*;



public class WeatherGetter {

	public static WeatherData getWeather(){
		try {
				System.out.println("Getting weather");
			  URL url = new URL("http://api.wunderground.com/api/8092824ad063a128/conditions/q/US/Michigan/Detroit.json");
			  HttpURLConnection con = (HttpURLConnection) url
			    .openConnection();
			  
			  System.out.println("STREAM:" +con.getInputStream().toString());
			  
			  JSONObject data = new JSONObject(readStream(con.getInputStream()));
			  
			  if(data==null)return null;
			  if(data.has("current_observation")){
				  return new WeatherData((JSONObject)data.get("current_observation"));
			  }
			  
			  

			  
			  } catch (Exception e) {
			  System.out.println("error");
			  e.printStackTrace();
			}
			return null;
	}
	
	
	static private String readStream(InputStream in) {
		  BufferedReader reader = null;
		  StringBuffer strBuf = new StringBuffer();
		  try {
		    reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		      strBuf.append(line);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  } finally {
		    if (reader != null) {
		      try {
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		        }
		    }
		    
		    if(strBuf.length()>0){
		    	return strBuf.toString();
		    }return null;
		  }
		}
		
	
	
}
