package com.example.assignment3_trailerama;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class TrailerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		String url;
		Bundle bundle = getIntent().getExtras();
		if(bundle != null)
		{
			url = bundle.getString("url");
		}
		else
		{
			url = "";
		}
		
		MediaPlayer mediaPlayer = new MediaPlayer();
		//mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try 
		{
			//These require error handling for various reasons
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} 
		catch (IllegalArgumentException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SecurityException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalStateException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			Toast.makeText(this, "That Trailer Does not exist, or was deleted. Sorry :(",10);
			e.printStackTrace();
		}
		
		setContentView(R.layout.activity_trailer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trailer, menu);
		return true;
	}

}
