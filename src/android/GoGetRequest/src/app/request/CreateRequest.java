package app.request;

import android.app.Activity;
import android.app.Dialog;
//import android.content.Intent;
//import android.database.Cursor;
import android.content.Context;
import android.location.*;
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

import java.util.List;
import java.util.Locale;

public class CreateRequest extends Activity {
	// Initializing variables
	EditText inputName;
	EditText inputAddr;
	EditText inputiName;
	EditText inputPrice;
    EditText inputStore;
    EditText inputComments;
	TextView numberOfRequests;
	RequestsDataSource datasource;
	GoGetSQLiteHelper dbHelper;
    LocationManager locManager;
    List<Address> addrs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newreq);		
		
		inputName = (EditText) findViewById(R.id.name);
		inputAddr = (EditText) findViewById(R.id.addr);
		inputiName = (EditText) findViewById(R.id.iname);
		inputPrice = (EditText) findViewById(R.id.price);
        inputStore = (EditText) findViewById(R.id.store_name);
        inputComments = (EditText) findViewById(R.id.comments);
		//Button btnNextScreen = (Button) findViewById(R.id.submit);
		
	
	} // End of onCreate()
	
	public void onStart()
	{
		try
		{
		super.onStart();
		dbHelper=new GoGetSQLiteHelper(this);
//		numberOfRequests.setText(numberOfRequests.getText()+String.valueOf(dbHelper.getRequestsCount()));
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
				RequestMessages.ShowRequestAddedAlert(this);
                //clear all EditTexts:
                inputName.setText("");
                //leave addr set
                //inputAddr.setText("");
                inputiName.setText("");
                inputPrice.setText("");
                inputStore.setText("");
                inputComments.setText("");
			//	numberOfRequests.setText("Number of Requests "+String.valueOf(dbHelper.getRequestsCount()));
			}
		}
	}

    public void btn_GetAddr_Click(View view)
    {
        String text;
        Address add;
        Geocoder geocoder = new Geocoder(this,Locale.ENGLISH);
        Location loc = getLoc();
        try {
            add = geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(),1).get(0);
            text = add.getAddressLine(0) + ", " + add.getAddressLine(1) + ", " + add.getAddressLine(2);
            //text = add.toString();
            inputAddr.setText(text);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private Location getLoc() {
        boolean gpsOn = false;
        try {
            locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,10,locListener);
            gpsOn = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsOn) {
                return locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private final LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderEnabled(String provider) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onProviderDisabled(String provider) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };

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
