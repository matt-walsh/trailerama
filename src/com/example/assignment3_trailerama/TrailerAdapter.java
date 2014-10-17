package com.example.assignment3_trailerama;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class TrailerAdapter extends BaseAdapter
{
    private ArrayList<Trailer> trailers = new ArrayList<Trailer>();

    private final Context context;

    // the context is needed to inflate views in getView()
    public TrailerAdapter(Context context) 
    {
        this.context = context;
    }
    
    //Update the trailer list and notify the listView
    public void updateTrailers(ArrayList<Trailer> trailers) 
    {
        this.trailers = trailers;
        notifyDataSetChanged();
    }
    
    //Return the size of the list
    @Override
    public int getCount() 
    {
        return trailers.size();
    }
    
    //Return a trailer at a specific point in the list
    @Override
    public Trailer getItem(int position) 
    {
    	if(position <= getCount() - 1)
    	{
    		return trailers.get(position);
    	}
    	
        return null;
    }
    
    
    //Get trailer ID
    @Override
    public long getItemId(int position) 
    {
    	Trailer trailer = trailers.get(position);
    	trailer.getTrailerId();
        return trailer.getTrailerId();

    }

    //Replacing the list items, with the template list items.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	//Inflate the template view
    	View rootView = LayoutInflater.from(context)
    		      .inflate(R.layout.trailer_template, parent, false);
    	
    	//find all of the widgets and assign to variables
    	ImageView trailerImg = (ImageView) rootView.findViewById(R.id.trailer_img); 
    	TextView titleView = (TextView) rootView.findViewById(R.id.trailer_title);
	    TextView descView = (TextView) rootView.findViewById(R.id.trailer_desc);
	    RatingBar ratingBar = (RatingBar) rootView.findViewById(R.id.trailer_rating);
	    TextView url = (TextView) rootView.findViewById(R.id.trailer_link);
	    
	    //Get selected trailer from the list
	    final Trailer currentTrailer = getItem(position);
	    
	    //Assign ImageView
	    trailerImg.setImageBitmap(currentTrailer.getThumb());
	    
	    //Assign TextViews
	    titleView.setText(currentTrailer.getName());
	    descView.setText(currentTrailer.getDescription());
	    
	    //Assign Rating
	    ratingBar.setRating(currentTrailer.getRating());
	    
	    //Assign "URL TextView"
	    url.setText(currentTrailer.getLink());
	    
		OnClickListener viewClicked = new OnClickListener()
	    {

			@Override
			public void onClick(View v) 
			{
//				AlertDialog.Builder alert;
//				alert = new AlertDialog.Builder(context);
//				alert.setMessage("You have clicked something");
//				alert.show();
				
				String trailerLink;
				trailerLink = currentTrailer.getLink();
				
				//Create a new bundle and add the score
				Bundle bundle = new Bundle();
				bundle.putString("url", trailerLink);
				
				//Create a new intent
				Intent trailerActivity = new Intent(context, TrailerActivity.class);
				
				//Add the bundle to the next intent
				trailerActivity.putExtras(bundle);
				
				//start the activity
				context.startActivity(trailerActivity);	
			}
	    	
	    };
		
	    return rootView;
    }
    

	
}
