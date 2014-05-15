package app.request;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{
	 private ArrayList<RequestItem> requestItems = new ArrayList<RequestItem>(); // requested Items
	 //private RequestItemAdapter aa;

	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        Button newRequest = (Button)findViewById(R.id.bNewRequest);
	        Button exit = (Button)findViewById(R.id.bExit);
	    
	        newRequest.setOnClickListener(this);
	        exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View myview) {
		// TODO Auto-generated method stub
		switch(myview.getId()){
		case R.id.bNewRequest:
		{ 	Intent i = new Intent(getApplicationContext(), CreateRequest.class);
			startActivity(i);   } break;
		case R.id.bViewRequest: 
				{
					
				}break;
		case R.id.bExit: finish(); break;
		}
		
	}

}
