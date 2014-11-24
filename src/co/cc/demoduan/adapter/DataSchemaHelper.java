package co.cc.demoduan.adapter;

import java.util.HashSet;
import java.util.Set;

import co.cc.edict.data.table.WordTable;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataSchemaHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "dictionary.db";
	// TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
	private static final int DATABASE_VERSION = 1;

	DataSchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// CREATE STUDENTS TABLE
		db.execSQL("CREATE TABLE " + WordTable.TABLE_NAME + " ("
				+ WordTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ WordTable.WORD_KEY + " TEXT," + WordTable.WORD_MEANING + " TEXT);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		// KILL PREVIOUS TABLES IF UPGRADED
		db.execSQL("DROP TABLE IF EXISTS " + WordTable.TABLE_NAME);
		
		// CREATE NEW INSTANCE OF SCHEMA
		onCreate(db);
	}

	// WRAPPER METHOD FOR ADDING A STUDENT
	public long addStudent(String key, String meaning) {
		// CREATE A CONTENTVALUE OBJECT
		ContentValues cv = new ContentValues();
		cv.put(WordTable.WORD_KEY, key);
		cv.put(WordTable.WORD_MEANING, meaning);
		
		// RETRIEVE WRITEABLE DATABASE AND INSERT
		SQLiteDatabase sd = getWritableDatabase();
		long result = sd.insert(WordTable.TABLE_NAME, WordTable.WORD_KEY, cv);
		return result;
	}



}
