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
	public int id;
	public int earlySnow;
	public int earlyRain;
	public int earlyFog;
	
	public Alarm(){
		name="";
		time="00:00";
		days="fffffff";
		isSmart=1;
		isCres=1;
		isSnooze=1;
		volume=5;	
		isOn=1;
		earlySnow = 0;
		earlyRain = 0;
		earlyFog = 0;
	}
}
