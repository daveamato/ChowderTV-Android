package com.example.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.item.ItemHome;

public class DatabaseHandler extends SQLiteOpenHelper{
	
private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "AddtoFav";
	
	private static final String TABLE_NAME = "Favorite";
	private static final String KEY_ID = "id";
	private static final String IMAGE = "image";
	private static final String CHANNEL_ID = "channelid";
	private static final String CHANNELNAME = "channelname";
	private static final String CHANNELDESC = "channeldesc";
	
	

	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
		@Override
		public void onCreate(SQLiteDatabase db) 
		{
			String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
					+ KEY_ID + " INTEGER PRIMARY KEY," 
					+ IMAGE + " TEXT," 
					+ CHANNEL_ID + " TEXT,"
					+ CHANNELNAME + " TEXT,"
					+ CHANNELDESC + " TEXT"
					+ ")";
			db.execSQL(CREATE_CONTACTS_TABLE);		
			
		}
		
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

				// Create tables again
				onCreate(db);
	}
	
	//Adding Record in Database
	
	public	void AddtoFavorite(ItemHome pj)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(IMAGE, pj.getImage());
		values.put(CHANNEL_ID, pj.getId());
		values.put(CHANNELNAME, pj.getChannelName());
		values.put(CHANNELDESC, pj.getDescription());
		
		// Inserting Row
		db.insert(TABLE_NAME, null, values);
		db.close(); // Closing database connection
		
	}
	
	// Getting All Data
		public List<ItemHome> getAllData() 
		{
			List<ItemHome> dataList = new ArrayList<ItemHome>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_NAME;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) 
			{
				do {
					ItemHome contact = new ItemHome();
					contact.setId(Integer.parseInt(cursor.getString(2)));
					contact.setImage(cursor.getString(1));
					contact.setChannelName(cursor.getString(3));
					contact.setDescription(cursor.getString(4));
				 
					// Adding contact to list
					dataList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			return dataList;
		}
		
	//getting single row
		
		public List<ItemHome> getFavRow(String id) 
		{
			List<ItemHome> dataList = new ArrayList<ItemHome>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_NAME +" WHERE channelid="+id;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) 
			{
				do {
					ItemHome contact = new ItemHome();
					contact.setId(Integer.parseInt(cursor.getString(2)));
					contact.setImage(cursor.getString(1));
					contact.setChannelName(cursor.getString(3));
					contact.setDescription(cursor.getString(4));
					// Adding contact to list
					dataList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			return dataList;
		}
		
	//for remove favorite
		
		public void RemoveFav(ItemHome contact)
		{
		    SQLiteDatabase db = this.getWritableDatabase();
		    db.delete(TABLE_NAME, CHANNEL_ID + " = ?",
		            new String[] { String.valueOf(contact.getId()) });
		  
		    db.close();
		}
		
		public enum DatabaseManager {
			INSTANCE;
			private SQLiteDatabase db;
			private boolean isDbClosed =true;
			DatabaseHandler dbHelper;
			public void init(Context context) {
				dbHelper = new DatabaseHandler(context);
			if(isDbClosed){
			isDbClosed =false;
			this.db = dbHelper.getWritableDatabase();
			}

			}


			public boolean isDatabaseClosed(){
			return isDbClosed;
			}

			public void closeDatabase(){
			if(!isDbClosed && db != null){
			isDbClosed =true;
			db.close();
			dbHelper.close();
			}
			}
		}
}
