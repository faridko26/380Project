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
				DatabaseConnection connectNow = new DatabaseConnection();
		        Connection connectDB = connectNow.getConnection();
				
				try {
					
					stmt=connectDB.createStatement();
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
					connectDB.close();
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
