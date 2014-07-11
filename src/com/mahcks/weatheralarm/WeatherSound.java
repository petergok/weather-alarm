package com.mahcks.weatheralarm;

import java.io.IOException;

import android.app.Activity;
import android.media.*;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Handler;

class Crescendo implements Runnable{
	
	public MediaPlayer mediaPlayer;
	public Handler handler;
	public static int t = 0;
	Crescendo(MediaPlayer mp,Handler handler){
		mediaPlayer = mp;
		this.handler = handler;
		t = 0;
	}
	@Override 
	public void run(){
		
		mediaPlayer.setVolume(t*0.01f,t*0.01f);
		
		if(t<100){
			t++;
			handler.postDelayed(this,500);
		}
		
	}
}

public class WeatherSound {
	
	public static MediaPlayer mp;
	
	public static void stopSound(){
		mp.stop();
		Crescendo.t = 0;
	}

	public static void playSound(Activity act, String sound, Boolean cres){
		
		act.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		
	    
	    AudioManager audioManager = (AudioManager) act.getSystemService(act.AUDIO_SERVICE);
        float volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        
        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_PLAY_SOUND);
        
        System.out.println("VOLUME:  "+audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        
        mp = MediaPlayer.create(act, WeatherGetter.toRes(sound));
        try {
        	if(cres)mp.setVolume(0f, 0f);
        	else mp.setVolume(1.00f, 1.00f);
        	mp.setLooping(true);
			mp.start();
	        
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       if(cres)new Crescendo(mp,new Handler()).run();
        
        System.out.println("volume: "+volume);
	}
	
	
}
