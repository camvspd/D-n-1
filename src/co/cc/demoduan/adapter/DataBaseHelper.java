package co.cc.demoduan.adapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/co.cc.demoduan/databases/";
 
    private static String DB_NAME = "edictionary_db";
    
//    private static String DB_NAME1="edictionary_database"; 
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
 
   
    public DataBaseHelper(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
	public ArrayList<Obj> getAll() {
		ArrayList<Obj> listLop = new ArrayList<Obj>();
		SQLiteDatabase db = this.getWritableDatabase();
		// Select All Query
		String sSQL = "select * from word_list";
		Cursor cursor = db.rawQuery(sSQL, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Obj lop = new Obj();
				lop.setWord_meaning(cursor.getString(2));
				lop.setWord_image(cursor.getBlob(3));
				// Adding Lop to list
				listLop.add(lop);
				Log.d("=============>", "" + listLop);
			} while (cursor.moveToNext());
		}
		return listLop;
	}
	public ArrayList<Obj> getAll2(){
		ArrayList<Obj> List=new ArrayList<Obj>();
		SQLiteDatabase db=this.getWritableDatabase();
		String sSQL="select * from word_list2";
		Cursor cursor=db.rawQuery(sSQL, null);
		if (cursor.moveToFirst()) {
			do {
				Obj lop = new Obj();
				lop.setWord_meaning(cursor.getString(2));
				lop.setWord_image(cursor.getBlob(3));
				// Adding Lop to list
				List.add(lop);
				Log.d("=============>", "" + List);
			} while (cursor.moveToNext());
		}
		return List;
	}
      
 
}