package app.request;

import android.app.Activity;
import android.app.Dialog;
//import android.content.Intent;
//import android.database.Cursor;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
import android.widget.EditText;
//import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
//import android.widget.AdapterView.OnItemSelectedListener;
import app.request.db.GoGetSQLiteHelper;
import app.request.db.RequestsDataSource;

public class CreateRequest extends Activity {
	// Initializing variables
	EditText inputName;
	EditText inputAddr;
	EditText inputiName;
	EditText inputPrice;
	TextView numberOfRequests;
	RequestsDataSource datasource;
	GoGetSQLiteHelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newreq);		
		
		inputName = (EditText) findViewById(R.id.name);
		inputAddr = (EditText) findViewById(R.id.addr);
		inputiName = (EditText) findViewById(R.id.iname);
		inputPrice = (EditText) findViewById(R.id.price);
		//Button btnNextScreen = (Button) findViewById(R.id.submit);
		
	
	} // End of onCreate()
	
	public void onStart()
	{
		try
		{
		super.onStart();
		dbHelper=new GoGetSQLiteHelper(this);
		numberOfRequests.setText(numberOfRequests.getText()+String.valueOf(dbHelper.getRequestsCount()));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			CatchError(ex.toString());
		}
	}
	
	public void btn_AddRequest_Click(View view)
	{
		boolean ok=true;
		try
		{
			Spannable spn=inputPrice.getText();
			String name=inputName.getText().toString();
			int price=Integer.valueOf(spn.toString());
		
			String addr = inputAddr.getText().toString();
			String item_name = inputiName.getText().toString();
			RequestItem reqItem = new RequestItem(name,addr,item_name,price);
			
			dbHelper.AddRequest(reqItem);
			
		}
		catch(Exception ex)
		{
			ok=false;
			CatchError(ex.toString());
		}
		finally
		{
			if(ok)
			{
				//NotifyEmpAdded();
				RequestMessages.ShowRequestAddedAlert(this);
				numberOfRequests.setText("Number of Requests "+String.valueOf(dbHelper.getRequestsCount()));
			}
		}
	}
	
	public void CatchError(String Exception)
	{
		Dialog diag=new Dialog(this);
		diag.setTitle("Add new Request Error");
		TextView txt=new TextView(this);
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}
	
	public void NotifyRequestAdded()
	{
		Dialog diag=new Dialog(this);
		diag.setTitle("Add new Request");
		TextView txt=new TextView(this);
		txt.setText("Employee Added Successfully");
		diag.setContentView(txt);
		diag.show();
		try {
			diag.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			CatchError(e.toString());
		}
		diag.notify();
		diag.dismiss();
	}
	
}
