package app.request;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestItem {

  private String owner;
  private Date created;
  private String itemname;
  private int item_price;
  private String address;

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
  
  public String getAddress(){
	  return address;
  }
  
  public void setItem(String i){
	  itemname = i;
  }
  
  
  public void setPrice(int i){
	  item_price = i;
  }
  public void setOwner(String i){
	  owner = i;
  }
  
  public void setAddress(String i){
	  address = i;
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
  
  public RequestItem(String Name, String addr, String iname,int price) {
	  owner = Name;
	  address = addr;
	  itemname = iname;
	  item_price = price;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dateString = sdf.format(created); 
    return "(" + dateString + ") " + owner;
  }
}