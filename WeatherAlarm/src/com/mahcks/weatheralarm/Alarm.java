package com.mahcks.weatheralarm;

public class Alarm {
	public String name;
	public String time;
	public String days;
	public int isSmart;
	public int isCres;
	public int isSnooze;
	public int volume;
	public int isOn;
	
	public Alarm(){
		name="";
		time="default";
		days="fffffff";
		isSmart=1;
		isCres=1;
		isSnooze=1;
		volume=5;	
		isOn=1;
	}
}
