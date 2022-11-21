package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField username1;
	private JPasswordField password1;
	private JPasswordField confirm;
	private JTextField firstname1;
	private JTextField lastname1;
	private JTextField age1;
	private JTextField phonenumber1;
	private JTextField email1;
	//private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;

	

	//Create the frame
	public Signup() {
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 324, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 307, 431);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 250, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 287, 57, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmpassword");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(21, 320, 92, 14);
		panel.add(lblNewLabel_2);
		
		username1 = new JTextField();
		username1.setBounds(131, 254, 145, 20);
		panel.add(username1);
		username1.setColumns(10);
		
		//Signup button creates new user in DB using given username and password
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement pst4 = null;
			    String firstname = firstname1.getText();
			    String lastname = lastname1.getText();
			    String gender;
			  
			    
				if(rdbtnMale.isSelected()) {
			    	gender = "Male";
			    	
			    }
			    else {
			    	gender = "Female";
			    }
			    
			    
			    
			    
			    String age = age1.getText();
			    String phonenumber = phonenumber1.getText();
			    String email = email1.getText();
				String username = username1.getText();
				String password = String.valueOf(password1.getPassword());
				String confirmpassword = String.valueOf(confirm.getPassword());
				DatabaseConnection connectNow = new DatabaseConnection();
		        Connection connectDB = connectNow.getConnection();
				
				try {
					
					
					if(password.equals(confirmpassword)) {
						String query="insert into customers (firstname,lastname,gender,age,phonenumber,email,username,password) values(?,?,?,?,?,?,?,?)";
						
						pst4= connectDB.prepareStatement(query);
						pst4.setString(1,  firstname);
						pst4.setString(2, lastname);
						pst4.setString(3,  gender);
						pst4.setString(4, age);
						pst4.setString(5, phonenumber);
						pst4.setString(6,  email);
						pst4.setString(7,  username);
						pst4.setString(8,  password);
						
						pst4.executeUpdate();
						
						
						
						
						JOptionPane.showMessageDialog(null, "Successfully Signed up");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "password does not match");
					}
					
					pst4.close();
					connectDB.close();
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
			}
			
			
		});
		btnNewButton.setBounds(110, 381, 89, 23);
		panel.add(btnNewButton);
		
		password1 = new JPasswordField();
		password1.setBounds(131, 285, 145, 20);
		panel.add(password1);
		
		confirm = new JPasswordField();
		confirm.setBounds(131, 318, 145, 20);
		panel.add(confirm);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(21, 24, 57, 26);
		panel.add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setBounds(21, 65, 81, 26);
		panel.add(lblLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGender.setBounds(21, 102, 57, 26);
		panel.add(lblGender);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(21, 139, 57, 26);
		panel.add(lblAge);
		
		JLabel lblPhoneNumbe = new JLabel("Phone Number");
		lblPhoneNumbe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNumbe.setBounds(21, 176, 92, 26);
		panel.add(lblPhoneNumbe);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(21, 213, 57, 26);
		panel.add(lblEmail);
		
		firstname1 = new JTextField();
		firstname1.setColumns(10);
		firstname1.setBounds(131, 28, 145, 20);
		panel.add(firstname1);
		
		lastname1 = new JTextField();
		lastname1.setColumns(10);
		lastname1.setBounds(131, 69, 145, 20);
		panel.add(lastname1);
		
		age1 = new JTextField();
		age1.setColumns(10);
		age1.setBounds(131, 143, 145, 20);
		panel.add(age1);
		
		phonenumber1 = new JTextField();
		phonenumber1.setColumns(10);
		phonenumber1.setBounds(131, 180, 145, 20);
		panel.add(phonenumber1);
		
		email1 = new JTextField();
		email1.setColumns(10);
		email1.setBounds(131, 217, 145, 20);
		panel.add(email1);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnMale.setBounds(131, 105, 63, 23);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFemale.setBounds(216, 105, 63, 23);
		panel.add(rdbtnFemale);
		
		ButtonGroup btnBg = new ButtonGroup();  
	    btnBg.add(rdbtnMale);
	    btnBg.add(rdbtnFemale);
	}
}
