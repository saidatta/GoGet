package app.request;

import java.util.ArrayList;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class RequestListActivity extends Activity implements NewItemFragment.OnNewItemAddedListener {
  
  private ArrayList<RequestItem> requestItems;
  private RequestItemAdapter aa;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate your view
    setContentView(R.layout.main);
      
    // Get references to the Fragments
    FragmentManager fm = getFragmentManager();
  //  RequestListFragment rListFragment =     (RequestListFragment)fm.findFragmentById(R.id.RequestListFragment);
     
    // Create the array list of to do items
    requestItems = new ArrayList<RequestItem>();
     
    // Create the array adapter to bind the array to the ListView
    int resID = R.layout.todolist_item;
    aa = new RequestItemAdapter(this, resID, requestItems);
     
    // Bind the array adapter to the ListView.
//    rListFragment.setListAdapter(aa);
    
  }
  
  public void onNewItemAdded(String newItem) {
	  RequestItem newTodoItem = new RequestItem(newItem);
	  requestItems.add(0, newTodoItem);
    aa.notifyDataSetChanged();
  }

}