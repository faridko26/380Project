package Airline;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	 public Connection databaseLink;

	    public Connection getConnection() {
	        URI dbUri = null;
	        try {
	            dbUri = new URI("postgres://sddbvrkvkbkbcz:61bdd3cfd6dcad474f70747d694116ca58f7cef4cff3986bdba0e7fa15a54317@ec2-44-209-158-64.compute-1.amazonaws.com:5432/dovqiu3kter09");
	        } catch (URISyntaxException e) {
	            throw new RuntimeException(e);
	        }

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

	        try{
	            
	            databaseLink = DriverManager.getConnection(dbUrl, username, password);
	        } catch(Exception e){
	            e.printStackTrace();
	        }

	        return databaseLink;
	    }

}
