package com.example.assignment3_trailerama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
	//Columns
    public static final String KEY_ROWID = "trailerId";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC = "description";
    public static final String KEY_THUMB = "thumb";
    public static final String KEY_LINK = "link";
    public static final String KEY_RATING = "rating";
    public static final String[] KEY_ARRAY = 
    	{
    		KEY_ROWID,
    		KEY_NAME,
    		KEY_DESC,
    		KEY_THUMB,
    		KEY_LINK,
    		KEY_RATING
    	};
    
    //???
    private static final String TAG = "DBAdapter";
    
    //Database Info
    private static final String DATABASE_NAME = "TrailerDB";
    private static final String DATABASE_TABLE = "trailers";
    private static final int DATABASE_VERSION = 1;
    
    //DB Create String
    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE + " (" 
            + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_NAME +" text not null, " 
            + KEY_DESC + " text not null, "
            + KEY_THUMB +" text not null, "
            + KEY_LINK + " text not null, " 
            + KEY_RATING +" integer not null);";
    
    
    //Database Helper Class
    private DatabaseHelper DBHelper;
    
    //SQLite Database
    private SQLiteDatabase db;
    
    //Public Constructor
    public DBAdapter(Context context) 
    {
        DBHelper = new DatabaseHelper(context);
    }
    
    //DBHelper Inner Class
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	try 
        	{
        		db.execSQL(DATABASE_CREATE);	
        	} 
        	catch (SQLException e) 
        	{
        		e.printStackTrace();
        	}
        }

        @Override
        //handles updating the database versions
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME + ";");
            onCreate(db);
        }
    }
    // INNER CLASS END
    
    //Open Database Connection
    public DBAdapter Open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Close Database Connection
    public void Close() 
    {
        DBHelper.close();
    }
    
    public Cursor ExecuteSelect(String where, String[] whereArgs)
    {
    	this.Open();
    	//Execute Select Query On Database
    	Cursor resultCursor = db.query(DATABASE_TABLE, KEY_ARRAY, where, whereArgs, null, null, null, null);
        
    	//Check if cursor is null, if it is, move to starting position
    	if (resultCursor != null) 
    	{
            resultCursor.moveToFirst();
        }
    	this.Close();
    	
        return resultCursor;
    }
    
    public boolean ExecuteDelete(String where, String[] whereArgs)
    {
    	this.Open();
    	if(db.delete(DATABASE_TABLE, where, whereArgs) != 0)
    	{
    		this.Close();
    		return true;
    	}
    	else
    	{
    		this.Close();
    		return false;
    	}
    }
    
    public boolean ExecuteUpdate(ContentValues values, String where, String[] whereArgs)
    {
    	this.Open();
    	if(db.update(DATABASE_TABLE, values, where, whereArgs) != 0)
    	{
    		this.Close();
    		return true;
    	}
    	else
    	{
    		this.Close();
    		return false;
    	}
    }
    
    public boolean ExecuteInsert(ContentValues values)
    {
    	this.Open();
    	if(db.insert(DATABASE_TABLE, null, values) != -1)
    	{
    		this.Close();
    		return true;
    	}
    	else
    	{
    		this.Close();
    		return false;
    	}
    	
    	
    	
    }
}
//END OF DBAdapter
