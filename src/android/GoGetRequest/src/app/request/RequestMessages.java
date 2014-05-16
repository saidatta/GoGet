package app.request;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import app.request.db.GoGetSQLiteHelper;

public class RequestMessages {
public static void ShowRequestAddedAlert(Context con)
{
	AlertDialog.Builder builder=new AlertDialog.Builder(con);
	builder.setTitle("Add new Request");
	builder.setIcon(android.R.drawable.ic_dialog_info);
	DialogListener listner=new DialogListener();
	builder.setMessage("Request Added successfully");
	builder.setPositiveButton("ok", listner);
	
	AlertDialog diag=builder.create();
	diag.show();
}

public static AlertDialog ShowEditDialog(final Context con,final RequestItem ri)
{
	AlertDialog.Builder b=new AlertDialog.Builder(con);
	b.setTitle("Request Details");
	LayoutInflater li=LayoutInflater.from(con);
	View v=li.inflate(R.layout.editdialog, null);
	
	b.setIcon(android.R.drawable.ic_input_get);
	
	b.setView(v);
	final TextView txtName=(TextView)v.findViewById(R.id.txtDelName);
	final TextView txtPrice=(TextView)v.findViewById(R.id.txtDelPrice);
	final TextView txtAddr =(TextView)v.findViewById(R.id.txtDelAddr);
	final TextView txtOwner = (TextView)v.findViewById(R.id.txtDelOwner);
	//final Spinner spin=(Spinner)v.findViewById(R.id.spinDiagDept);
//	Utilities.ManageDeptSpinner(con, spin);

	
	txtName.setText(ri.getItem());
	txtPrice.setText(String.valueOf(ri.getPrice()));
	
	b.setPositiveButton("Modify", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			ri.setItem(txtName.getText().toString());
			ri.setPrice(Integer.valueOf(txtPrice.getText().toString()));
			ri.setAddress(txtAddr.getText().toString());
			ri.setOwner(txtOwner.getText().toString());
			try
			{
				GoGetSQLiteHelper db=new GoGetSQLiteHelper(con);
				db.UpdateEmp(ri);
			}
			catch(Exception ex)
			{
				CatchError(con, ex.toString());
			}
		}
	});
	
	b.setNeutralButton("Delete", new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			GoGetSQLiteHelper db=new GoGetSQLiteHelper(con);
			db.DeleteEmp(ri);
		}
	});
	b.setNegativeButton("Cancel", null);
	
	return b.create();
	//diag.show();
	
}

static public void CatchError(Context con, String Exception)
{
	Dialog diag=new Dialog(con);
	diag.setTitle("Error");
	TextView txt=new TextView(con);
	txt.setText(Exception);
	diag.setContentView(txt);
	diag.show();
}


}
