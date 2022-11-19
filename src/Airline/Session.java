package Airline;

public class Session {

	private String username;
	private String cus_id;
	
	public Session() {
		
	}
	
	public Session(String username, String cus_id) {
		
		this.username = username;
		this.cus_id = cus_id;
	}

	public String getUsername() {
		
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getCus_id() {
		
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		
		this.cus_id = cus_id;
	}
}
