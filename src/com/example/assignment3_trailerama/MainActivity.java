package com.example.assignment3_trailerama;
//Import All kinds of cool stuff

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Create a new trailer model to begin working with the data
		TrailerModel trailerModel = new TrailerModel(getAssets(),this);
		
		//Check if the database has data, otherwise, add data
		if(trailerModel.GetRowCount() <= 1)
		{
			trailerModel.InsertTrailer("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
										"shawshank.jpg", "www.google.ca", 5);
			trailerModel.InsertTrailer("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
					"godfather.jpg", "www.google.ca", 5);
			trailerModel.InsertTrailer("The God Father II","The early life and career of Vito Corleone in 1920s New York is portrayed while his son, Michael, expands and tightens his grip on his crime syndicate stretching from Lake Tahoe, Nevada to pre-revolution 1958 Cuba.",
					"godfather2.jpg", "www.google.ca", 5);
			trailerModel.InsertTrailer("Pulp Fiction", "The lives of two mob hit men, a boxer, a gangster's wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
					"pulpfiction.jpg", "www.google.ca", 5);
			trailerModel.InsertTrailer("The Good, the Bad and the Ugly", "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.",
					"goodbad.jpg", "www.google.ca", 5);
		}
		
		//Create the custom base adapter and cast is to a list adapter
		TrailerAdapter trailerAdapter = new TrailerAdapter(this);
		try 
		{
			trailerAdapter.updateTrailers(trailerModel.GetAllTrailers());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get list view and set the adapter
		final ListView trailerListView = (ListView) this.findViewById(R.id.trailerListView);
		trailerListView.setAdapter(trailerAdapter);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
