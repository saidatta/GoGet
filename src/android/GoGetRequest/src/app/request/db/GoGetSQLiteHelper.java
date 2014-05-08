package app.request.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GoGetSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_REQUESTS = "requests";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_REQUEST = "request";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_REQUESTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_REQUEST
			+ " text not null);";

	public GoGetSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(GoGetSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);
		onCreate(db);
	}

}