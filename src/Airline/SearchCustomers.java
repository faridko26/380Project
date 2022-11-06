package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class SearchCustomers extends JFrame{
	private JTextField tfUser;
	private final JButton btnSearch = new JButton("Search");
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
	public SearchCustomers() {
		
		setBounds(100,150,350,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Search User");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		btnSearch.setBounds(10, 5, 119, 23);
		panel_1.add(btnSearch);
		
		tfUser = new JTextField();
		tfUser.setBounds(139, 6, 187, 20);
		panel_1.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel DisplayResult = new JLabel("");
		DisplayResult.setHorizontalAlignment(SwingConstants.CENTER);
		DisplayResult.setBounds(10, 39, 316, 56);
		panel_1.add(DisplayResult);
		
		//Search button compares given username with each username in DB
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUser.getText();
				
				try {
					con=getConnection();
					
					String query1="select * from users where username =?";
					pst1= con.prepareStatement(query1);
					pst1.setString(1,  username);
					ResultSet rs1 = pst1.executeQuery();
					
					if(rs1.next()) { //user found
						DisplayResult.setText("User '" + username + "' found");
					}
					else { //user not found
						DisplayResult.setText("User not found");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "error");
				}
			}
		});
		
	}
}