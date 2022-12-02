package Airline;

public class Session {
	
	private String username;
	private String name;
	private String lastname;
	private String email;
	private String cus_id;
	
	public Session() {
		
	}
	
	public Session(String username,String name,String lastname,String email,  String cus_id) {
		
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.cus_id = cus_id;
	}
	
	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}

	
	
	public String getLastname() {
		
		return lastname;
	}
	
	public void setLastame(String lastname) {
		
		this.lastname = lastname;
	}

	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}

	public String getCus_id() {
		
		return cus_id;
	}

	public void setCus_id(String cus_id) {
		
		this.cus_id = cus_id;
	}
}
