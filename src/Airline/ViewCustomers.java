package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewCustomers extends JFrame{
	
	 private JList<String> userList;
	 Connection con;
	 PreparedStatement pst1;
	 
	 //Establish connection to DB
	 public static Connection getConnection() throws URISyntaxException, SQLException {
		    URI dbUri = new URI("postgres://sddbvrkvkbkbcz:61bdd3cfd6dcad474f70747d694116ca58f7cef4cff3986bdba0e7fa15a54317@ec2-44-209-158-64.compute-1.amazonaws.com:5432/dovqiu3kter09");
		    
		    Connection conn;
		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		    conn=DriverManager.getConnection(dbUrl, username, password);

		    return conn;
		}
	 //create frame
	    public ViewCustomers() {
	    	setResizable(false);
	    	
	    	try {
	    		con=getConnection();
	    		
	    		//Selects data from users table
		    	String query1="select * from users"; 
				pst1= con.prepareStatement(query1);
				ResultSet rs1 = pst1.executeQuery();
				
				//Inserts each name in 'username' column into list
				DefaultListModel<String> listModel = new DefaultListModel<>();
				while(rs1.next()) {
					listModel.addElement(rs1.getString("username"));
				}
				userList = new JList<>(listModel);
				
	    	}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "error");
			}
	       
	        getContentPane().add(new JScrollPane(userList));
	 
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setTitle("Customers");
	        setBounds(100, 150, 200, 540);
	        setVisible(true);
	    }
}