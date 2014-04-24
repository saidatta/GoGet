package app.request;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{
	 private ArrayList<RequestItem> requestItems; // requested Items
	  private RequestItemAdapter aa;

	protected void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        Button newGame = (Button)findViewById(R.id.bNewRequest);
	        Button exit = (Button)findViewById(R.id.bExit);
	        newGame.setOnClickListener(this);
	        exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View myview) {
		// TODO Auto-generated method stub
		switch(myview.getId()){
		case R.id.bNewRequest:{} break;
		case R.id.bViewRequest:
		case R.id.bExit: finish(); break;
		}
		
	}

}
