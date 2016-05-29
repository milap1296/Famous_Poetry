package com.example.famouspoetry;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final Integer DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "tourDatabase";

	private static final String TABLE_NAME = "famous_poetry";

	private static final String POEM_NAME = "poem_name";
	private static final String POETRY = "poetry";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_NAME
				+ "( id integer primary key autoincrement ," + POEM_NAME
				+ " text , " + POETRY + " text ,category text,auther text);";
		db.execSQL(CREATE_CITY_TABLE);
		// db.execSQL("create table "+TABLE_NAME
		// +
		// " (id integer primary key autoincrement , poet_name text , poetry text , category text )");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void addPoet(String category, String poemname, String auther,
			String poem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		// db.insert("INSERT INTO FamousPoetry (PoetName) VALUES ('Jonathon')",
		// null, null);
		// db.insert("INSERT INTO FamousPoetry (PoetName) VALUES ('Chris')",
		// null, null);
		// db.insert("INSERT INTO FamousPoetry (PoetName) VALUES ('Tom')", null,
		// null);
		// Cursor c = db.rawQuery("select * from " + TABLE_NAME, null);
		//
		// {
		values.put("category", category);
		values.put("poem_name", poemname);
		values.put("auther", auther);
		// values.put("duration", duration);
		values.put("poetry", poem);
		db.insert(TABLE_NAME, null, values);
		// }
			db.close();
//		Log.d("Database", category + "--- -" + poemname + "---" + "---- "
//				+ auther + "----" + poem);

		/*
		 * if(c.getCount()==0) { db.execSQL("INSERT INTO " +TABLE_NAME+
		 * " (poet_name,poetry,category) VALUES ('Jonathon','Shut up and move on to toms poetry','cat1')"
		 * ); db.execSQL("INSERT INTO " +TABLE_NAME+
		 * " (poet_name,poetry,category) VALUES ('tom','This Poetry is not for you Go to john','cat1')"
		 * ); db.execSQL("INSERT INTO " +TABLE_NAME+
		 * " (poet_name,poetry,category) VALUES ('john','this is not a place where you should be Go to Jonathon ','cat2')"
		 * ); db.close();
		 * 
		 * }
		 */

	}

	public Cursor getAllCategories() {

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT DISTINCT category FROM " + TABLE_NAME;

		Cursor mCursor = db.rawQuery(selectQuery, null);

		return mCursor;
	}

	public ArrayList<HashMap<String, String>> getAllData(String category) {
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		
			
		String selectQuery = "SELECT * FROM " + TABLE_NAME
				+ " WHERE CATEGORY = " + "'" + category + "'";

		Cursor mCursor = db.rawQuery(selectQuery, null);
		mCursor.moveToFirst();
		for (int i = 0; i < mCursor.getCount(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("id", mCursor.getString(mCursor.getColumnIndex("id")));
			map.put("poem_name",
					mCursor.getString(mCursor.getColumnIndex("poem_name")));
			map.put("auther",
					mCursor.getString(mCursor.getColumnIndex("auther")));

			data.add(map);
			mCursor.moveToNext();
		}
		Log.d("asufauifa",data+"");
		return data;
	}
	public String getPoem(String poem)
	{
		String value=null;
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectquery = "SELECT poetry FROM "+TABLE_NAME+" WHERE id = " + "'"+poem+"'";
		Cursor c = db.rawQuery(selectquery, null);
		c.moveToFirst();
		value = c.getString(c.getColumnIndex("poetry")).toString();
		c.moveToNext();
		c.close();
		return value;
	}


}
