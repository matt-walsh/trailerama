package com.example.assignment3_trailerama;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TrailerModel 
{
	private DBAdapter db;
	private AssetManager assets;
	
	public TrailerModel(AssetManager assetManager, Context context)
	{
		db = new DBAdapter(context);
		assets = assetManager;
	}
	
	public ArrayList<Trailer> GetAllTrailers() throws IOException
	{
		//Get data from database
		Cursor trailerCursor = db.ExecuteSelect(null, null);
		
		//Check to make sure cursor is not Null
		if(trailerCursor != null)
		{
			ArrayList<Trailer> trailerList = new ArrayList<Trailer>();
			if(trailerCursor.moveToFirst())
			{
				do
				{
					Trailer trailer = new Trailer(trailerCursor.getInt(0),
												  trailerCursor.getString(1),
												  trailerCursor.getString(2),
												  GetImage(trailerCursor.getString(3)),
												  trailerCursor.getString(4),
												  trailerCursor.getInt(5));
					trailerList.add(trailer);
					
				}while(trailerCursor.moveToNext());
				
				return trailerList;
			}
		}
		
		return null;
	}
	
    public Bitmap GetImage(String assetName) throws IOException
    {   
        //Open asset and decode the stream
        InputStream imageStream = assets.open(assetName);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        
        return bitmap;
    }

    public boolean InsertTrailer(String name, String description, String thumbnail, String link, int rating)
    {
    	//Create ContentValues object to store data
    	ContentValues values = new ContentValues();
    	values.put(DBAdapter.KEY_NAME, name);
    	values.put(DBAdapter.KEY_DESC, description);
    	values.put(DBAdapter.KEY_THUMB, thumbnail);
    	values.put(DBAdapter.KEY_LINK, link);
    	values.put(DBAdapter.KEY_RATING, rating);
    	
    	//Execute Insert
    	if(db.ExecuteInsert(values))
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    public int GetRowCount()
    {
    	//Get data from database
		Cursor trailerCursor = db.ExecuteSelect(null,null);
		
		//Check to make sure cursor is not Null
		int count = 0;
		if(trailerCursor != null)
		{
			do
			{
				count ++;
			}while(trailerCursor.moveToNext());
		}
    	
    	return count;
    }
}
