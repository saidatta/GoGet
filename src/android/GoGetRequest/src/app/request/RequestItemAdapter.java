package app.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RequestItemAdapter extends ArrayAdapter<RequestItem> {

  int resource;

  public RequestItemAdapter(Context context,
                         int resource,
                         List<RequestItem> items) {
    super(context, resource, items);
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LinearLayout reqView;

    RequestItem item = getItem(position);

    String taskString = item.getItem();
    Date createdDate = item.getCreated();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dateString = sdf.format(createdDate);

    if (convertView == null) {
    	reqView = new LinearLayout(getContext());
      String inflater = Context.LAYOUT_INFLATER_SERVICE;
      LayoutInflater li;
      li = (LayoutInflater)getContext().getSystemService(inflater);
      li.inflate(resource, reqView, true);
    } else {
    	reqView = (LinearLayout) convertView;
    }

    TextView dateView = (TextView)reqView.findViewById(R.id.rowDate);
    TextView taskView = (TextView)reqView.findViewById(R.id.row);

    dateView.setText(dateString);
    taskView.setText(taskString);

    return reqView;
  }
}