package com.mahcks.weatheralarm;

public class Alarm {
	private String name;
	private String time;
	private String days;
	private boolean isSmart;
	private boolean isCres;
	private boolean isSnooze;
	private int volume;
	
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
