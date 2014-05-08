package app.request.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import app.request.RequestItem;

public class RequestsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private GoGetSQLiteHelper dbHelper;
	private String[] allColumns = { GoGetSQLiteHelper.COLUMN_ID,
			GoGetSQLiteHelper.COLUMN_REQUEST };

	private static final String TAG = "DBDEMO";

	public RequestsDataSource(Context context) {
		dbHelper = new GoGetSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Request createRequest(RequestItem ri) {
		ContentValues values = new ContentValues();
		values.put(GoGetSQLiteHelper.COLUMN_REQUEST, ri.getItem());
		long insertId = database.insert(GoGetSQLiteHelper.TABLE_REQUESTS, null,
				values);
		Cursor cursor = database.query(GoGetSQLiteHelper.TABLE_REQUESTS,
				allColumns, GoGetSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Request newRequest = cursorToRequest(cursor);

		// Log the request stored
		Log.d(TAG, "request = " + cursorToRequest(cursor).toString()
				+ " insert ID = " + insertId);

		cursor.close();
		return newRequest;
	}

	public void deleteRequest(Request req) {
		long id = req.getId();
		Log.d(TAG, "delete request = " + id);
		System.out.println("Request deleted with id: " + id);
		database.delete(GoGetSQLiteHelper.TABLE_REQUESTS, GoGetSQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public void deleteAllRequests() {
		System.out.println("Requests deleted all");
		Log.d(TAG, "delete all = ");
		database.delete(GoGetSQLiteHelper.TABLE_REQUESTS, null, null);
	}
	
	public List<Request> getAllRequests() {
		List<Request> requests = new ArrayList<Request>();

		Cursor cursor = database.query(GoGetSQLiteHelper.TABLE_REQUESTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Request req = cursorToRequest(cursor);
			Log.d(TAG, "get request = " + cursorToRequest(cursor).toString());
			requests.add(req);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return requests;
	}

	private Request cursorToRequest(Cursor cursor) {
		Request req = new Request();
		req.setId(cursor.getLong(0));
		req.setRequest(cursor.getString(1));
		return req;
	}
}