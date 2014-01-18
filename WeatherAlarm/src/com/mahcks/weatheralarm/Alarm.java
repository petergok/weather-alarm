package com.mahcks.weatheralarm;

public class Alarm {
	public String name;
	public String time;
	public String days;
	public boolean isSmart;
	public boolean isCres;
	public boolean isSnooze;
	public int volume;
	
	public Alarm(){
		name="Alarm";
		time="00:00";
		days="mtwtfss";
		isSmart=true;
		isCres=true;
		isSnooze=true;
		volume=5;	
	}
}
