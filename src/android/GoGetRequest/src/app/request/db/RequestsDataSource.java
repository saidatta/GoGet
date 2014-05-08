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
		Request newComment = cursorToRequest(cursor);

		// Log the comment stored
		Log.d(TAG, "request = " + cursorToRequest(cursor).toString()
				+ " insert ID = " + insertId);

		cursor.close();
		return newComment;
	}

	public void deleteRequest(Request req) {
		long id = req.getId();
		Log.d(TAG, "delete comment = " + id);
		System.out.println("Comment deleted with id: " + id);
		database.delete(GoGetSQLiteHelper.TABLE_REQUESTS, GoGetSQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public void deleteAllRequests() {
		System.out.println("Requests deleted all");
		Log.d(TAG, "delete all = ");
		database.delete(GoGetSQLiteHelper.TABLE_REQUESTS, null, null);
	}
	
	public List<Request> getAllRequests() {
		List<Request> comments = new ArrayList<Request>();

		Cursor cursor = database.query(GoGetSQLiteHelper.TABLE_REQUESTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Request comment = cursorToRequest(cursor);
			Log.d(TAG, "get comment = " + cursorToRequest(cursor).toString());
			comments.add(comment);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return comments;
	}

	private Request cursorToRequest(Cursor cursor) {
		Request comment = new Request();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}
}