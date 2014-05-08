package app.request.db;


public class Request {
	private long id;
	private String request;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String req) {
		this.request = req;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return request;
	}
}