package app.request;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestItem {

  private String owner;
  private Date created;
  private String itemname;
  private int item_price;

  public String getOwner() {
    return owner;
  }
  
  public int getPrice() {
	    return item_price;
  }
  
  public String getItem() {
	  return itemname;
  }
  
  public Date getCreated() {
    return created;
  }

  public RequestItem(String owner) {
    this(owner, new Date(java.lang.System.currentTimeMillis()));
  }

  public RequestItem(String creator, Date item_created) {
    owner = creator;
    created = item_created;
  }

  public RequestItem(String currtask, String item, int price, Date currcreated) {
	owner = currtask;
    created = currcreated;
    itemname = item;
    item_price = price;
  }
  

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dateString = sdf.format(created); 
    return "(" + dateString + ") " + owner;
  }
}