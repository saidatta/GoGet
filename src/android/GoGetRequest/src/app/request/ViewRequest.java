package app.request;

import java.util.List;

import app.request.db.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ViewRequest  extends Activity {
	GoGetSQLiteHelper dbHelper;
	static public GridView grid;
	TextView txtTest;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.gridview);
        grid=(GridView)findViewById(R.id.grid);
        txtTest=(TextView)findViewById(R.id.txtTest);
        
  
        final GoGetSQLiteHelper db=new GoGetSQLiteHelper(this);
     
        
       
        try
        {
        grid.setOnItemClickListener(new OnItemClickListener()
        {

        	@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				try
				{
			//String Name, String addr, String iname,int price
				SQLiteCursor cr=(SQLiteCursor)parent.getItemAtPosition(position);
				String iname=cr.getString(cr.getColumnIndex(GoGetSQLiteHelper.COLUMN_ITEMNAME));
				int price=cr.getInt(cr.getColumnIndex(GoGetSQLiteHelper.COLUMN_ITEMPRICE));
				String owner=cr.getString(cr.getColumnIndex(GoGetSQLiteHelper.COLUMN_OWNER));
				String addr = cr.getString(cr.getColumnIndex(GoGetSQLiteHelper.COLUMN_ADDR));
				RequestItem emp=new RequestItem(owner,addr,iname,price);
			//	emp.SetID((int)id);
				AlertDialog diag= RequestMessages.ShowEditDialog(ViewRequest.this,emp);
				diag.setOnDismissListener(new OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						// TODO Auto-generated method stub
						txtTest.setText("dismissed");
						//((SimpleCursorAdapter)grid.getAdapter()).notifyDataSetChanged();
				//		LoadGrid();
					}
				});
				diag.show();
				}
				catch(Exception ex)
				{
					RequestMessages.CatchError(ViewRequest.this, ex.toString());
				}
			}

			
        }
        );
        }
        catch(Exception ex)
        {
        	
        }

    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	//LoadGrid();
    }
   /* 
    public void LoadGrid()
    {
    	dbHelper=new GoGetSQLiteHelper(this);
    	try
    	{
    		View v=spinDept1.getSelectedView();
			TextView txt=(TextView)v.findViewById(R.id.COLUMN_OWNER);
			String ownr=String.valueOf(txt.getText());
    		Cursor c=dbHelper.getRequestByOwner(ownr);
    		startManagingCursor(c);
    		
    		String [] from=new String []{GoGetSQLiteHelper.COLUMN_ITEMNAME,GoGetSQLiteHelper.COLUMN_ITEMPRICE,GoGetSQLiteHelper.COLUMN_OWNER};
    		int [] to=new int [] {R.id.COLUMN_ITEMNAME,R.id.COLUMN_ITEMPRICE,R.id.COLUMN_OWNER};
    		SimpleCursorAdapter sca=new SimpleCursorAdapter(this,R.layout.gridrow,c,from,to);
    		grid.setAdapter(sca);
    		
    		
    		
    	}
    	catch(Exception ex)
    	{
    		AlertDialog.Builder b=new AlertDialog.Builder(this);
    		b.setMessage(ex.toString());
    		b.show();
    	}
    }*/
	
}
