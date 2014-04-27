package app.request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRequest extends Activity {
	// Initializing variables
	EditText inputName;
	EditText inputAddr;
	EditText inputiName;
	EditText inputPrice;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newreq);		
		
		inputName = (EditText) findViewById(R.id.name);
		inputAddr = (EditText) findViewById(R.id.addr);
		inputiName = (EditText) findViewById(R.id.iname);
		inputPrice = (EditText) findViewById(R.id.price);
		Button btnNextScreen = (Button) findViewById(R.id.submit);
		
		//Listening to button event
		btnNextScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				//Starting a new Intent
				finish();
				
			}
		});
	}
}