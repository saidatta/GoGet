package app.request.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import app.request.RequestItem;
/**
 * To use the SQL db you need to inherit the sqlitehelper
 * @author Venkata
 *
 */
public class GoGetSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_REQUESTS = "requests";
	public static final String COLUMN_ID = "RequestID";
	public static final String COLUMN_ITEMNAME = "itemname";
	public static final String COLUMN_ITEMPRICE = "itemPrice";
	public static final String COLUMN_OWNER = "owner";
	public static final String COLUMN_ADDR = "address";
	private static final String LOGCAT = null;

	private static final String VIEW_REQUEST = "ViewReqs";
	private static final String DATABASE_NAME = "gogetDB";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "CREATE TABLE "
			+ TABLE_REQUESTS + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEMNAME
			+ " TEXT NOT NULL, "+ COLUMN_ITEMPRICE+ " INTEGER, "+ COLUMN_OWNER+" TEXT NOT NULL, "+COLUMN_ADDR+" TEXT NOT NULL );";
	
	public GoGetSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(LOGCAT,"Created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE); //Executes SQL based on Custom made SQL
		Log.d(LOGCAT, "REQUEST TABLE IS CREATED");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(GoGetSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);
		onCreate(db);
		Log.d(LOGCAT,"Database Upgraded.");
	}
	
	public void AddRequest(RequestItem ri)
	{
		 SQLiteDatabase db= this.getWritableDatabase();
		ContentValues cv=new ContentValues();
			
			cv.put(COLUMN_ITEMNAME, ri.getItem());
			cv.put(COLUMN_ITEMPRICE, ri.getPrice());
			cv.put(COLUMN_OWNER, ri.getOwner());
			cv.put(COLUMN_ADDR,ri.getAddress());
			//cv.put(colDept,2);
			
			db.insert(TABLE_REQUESTS, COLUMN_ITEMNAME, cv);
			db.close();
			
		}
	 
	public int getRequestsCount()
	 {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+TABLE_REQUESTS, null);
		int x= cur.getCount();
		cur.close();
		return x;
	 }
	 
	public Cursor getAllRequests()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 
		 Cursor cur= db.rawQuery("SELECT * FROM "+VIEW_REQUEST,null);
		 return cur;
	 }
	 
	 public int UpdateEmp(RequestItem ri) //check this method
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(COLUMN_ITEMNAME, ri.getItem());
			cv.put(COLUMN_ITEMPRICE, ri.getPrice());
			cv.put(COLUMN_OWNER, ri.getOwner());
		 return db.update(TABLE_REQUESTS, cv, COLUMN_ID+"=?", new String []{String.valueOf(ri.getItem())});
	 }
	
	 public void DeleteEmp(RequestItem ri) // check this item
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.delete(TABLE_REQUESTS,COLUMN_ID+"=?", new String [] {String.valueOf(ri.getItem())});
		 db.close();		
	 }
     
      public Cursor getRequestByOwner(String ownr)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 String [] columns=new String[]{"_id",COLUMN_ITEMNAME,COLUMN_ITEMPRICE,COLUMN_OWNER,COLUMN_ADDR};
		 Cursor c=db.query(VIEW_REQUEST, columns, COLUMN_OWNER+"=?", new String[]{ownr}, null, null, null);
		 return c;
	 }

}