package com.example.assignment3_trailerama;

import android.graphics.Bitmap;

public class Trailer 
{
	private int trailerId;
	private String name;
	private String description;
	private Bitmap thumb;
	private String link;
	private int rating;
	
	public Trailer(int trailerId, String name, String description, Bitmap thumb, String link, int rating )
	{
		this.trailerId = trailerId;
		this.name = name;
		this.description = description;
		this.thumb = thumb;
		this.link = link;
		this.rating = rating;
	}
	
	
	public int getTrailerId()
	{
		return trailerId;
	}
	public void setTrailerId(int trailerId)
	{
		this.trailerId = trailerId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Bitmap getThumb()
	{
		return thumb;
	}
	public void setThumb(Bitmap thumb)
	{
		this.thumb = thumb;
	}
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}
	public int getRating()
	{
		return rating;
	}
	public void setRating(int rating)
	{
		this.rating = rating;
	}
	
	
}
