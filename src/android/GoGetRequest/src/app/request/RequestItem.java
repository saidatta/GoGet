package app.request;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestItem {

  String task;
  Date created;

  public String getTask() {
    return task;
  }

  public Date getCreated() {
    return created;
  }

  public RequestItem(String _task) {
    this(_task, new Date(java.lang.System.currentTimeMillis()));
  }

  public RequestItem(String _task, Date _created) {
    task = _task;
    created = _created;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dateString = sdf.format(created); 
    return "(" + dateString + ") " + task;
  }
}