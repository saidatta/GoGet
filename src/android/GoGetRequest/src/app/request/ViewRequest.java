package app.request;

import java.util.List;
import app.request.db.*;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ViewRequest extends ListActivity {
	private RequestsDataSource datasource; // db instance

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		datasource = new RequestsDataSource(this);
		datasource.open();

		List<Request> values = datasource.getAllRequests();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Request> adapter = new ArrayAdapter<Request>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	
	// Will be called via the onClick attribute
	// of the buttons in main.xml
	public void onClick(View view) {
	
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}