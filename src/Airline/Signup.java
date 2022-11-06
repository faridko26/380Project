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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField username1;
	private JPasswordField password1;
	private JPasswordField confirm;

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

	//Create the frame
	public Signup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 307, 282);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 63, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 113, 57, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmpassword");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 157, 92, 14);
		panel.add(lblNewLabel_2);
		
		username1 = new JTextField();
		username1.setBounds(121, 67, 145, 20);
		panel.add(username1);
		username1.setColumns(10);
		
		//Signup button creates new user in DB using given username and password
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = null;
			    Statement stmt = null;
				String username = username1.getText();
				String password = String.valueOf(password1.getPassword());
				String confirmpassword = String.valueOf(confirm.getPassword());
				
				try {
					c=getConnection();
					stmt=c.createStatement();
					if(password.equals(confirmpassword)) {
						String query="insert into users (username,password) values('"+username+"', '"+password+"')";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Successfully Signed up");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "password does not match");
					}
					
					stmt.close();
			        c.close();
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
			}
			
			
		});
		btnNewButton.setBounds(116, 221, 89, 23);
		panel.add(btnNewButton);
		
		password1 = new JPasswordField();
		password1.setBounds(121, 111, 145, 20);
		panel.add(password1);
		
		confirm = new JPasswordField();
		confirm.setBounds(121, 155, 145, 20);
		panel.add(confirm);
	}
}
