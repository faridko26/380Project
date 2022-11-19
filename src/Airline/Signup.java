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
import javax.swing.JRadioButton;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField username1;
	private JPasswordField password1;
	private JPasswordField confirm;
<<<<<<< Updated upstream
	private JTextField firstname1;
	private JTextField lastname1;
	private JTextField age1;
	private JTextField phonenumber1;
	private JTextField email1;
	//private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
=======
	private JTextField firstnametb;
	private JTextField lastnametb;
	private JTextField emailtb;
	private JTextField pntb;
	private JTextField agetb;
	private JTextField gendertb;
>>>>>>> Stashed changes

	

	//Create the frame
	public Signup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
<<<<<<< Updated upstream
		setBounds(100, 100, 324, 463);
=======
		setBounds(100, 100, 298, 444);
>>>>>>> Stashed changes
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
<<<<<<< Updated upstream
		panel.setBounds(0, 0, 307, 431);
=======
		panel.setBounds(0, 0, 282, 405);
>>>>>>> Stashed changes
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 250, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
<<<<<<< Updated upstream
		lblNewLabel_1.setBounds(21, 287, 57, 14);
=======
		lblNewLabel_1.setBounds(10, 287, 57, 14);
>>>>>>> Stashed changes
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmpassword");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
<<<<<<< Updated upstream
		lblNewLabel_2.setBounds(21, 320, 92, 14);
=======
		lblNewLabel_2.setBounds(10, 312, 92, 14);
>>>>>>> Stashed changes
		panel.add(lblNewLabel_2);
		
		username1 = new JTextField();
		username1.setBounds(131, 254, 145, 20);
		panel.add(username1);
		username1.setColumns(10);
		
		//Signup button creates new user in DB using given username and password
		JButton signupbutton = new JButton("Signup");
		signupbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    Statement stmt = null;
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
				String email = emailtb.getText();
				String phonenumber = pntb.getText();
				String age = agetb.getText();
				String firstname = firstnametb.getText();
				String lastname = lastnametb.getText();
				String gender = gendertb.getText();
				String confirmpassword = String.valueOf(confirm.getPassword());
				DatabaseConnection connectNow = new DatabaseConnection();
		        Connection connectDB = connectNow.getConnection();
				
				try {
					
					stmt=connectDB.createStatement();
					if(password.equals(confirmpassword)) {
<<<<<<< Updated upstream
						String query="insert into customers (firstname,lastname,gender,age,phonenumber,email,username,password) values('"+firstname+"','"+lastname+"','"+gender+"','"+age+"','"+phonenumber+"','"+email+"','"+username+"', '"+password+"')";
=======
						String query="insert into customers (username,password,email,phonenumber,age,gender,firstname,lastname) "
								+ "values('"+username+"', '"+password+"', "
										+ "'"+email+"', '"+phonenumber+"', "
												+ "'"+age+"', '"+gender+"', "
														+ "'"+firstname+"', '"+lastname+"')";
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
		btnNewButton.setBounds(99, 384, 89, 23);
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
=======
		signupbutton.setBounds(83, 337, 89, 23);
		panel.add(signupbutton);
		
		password1 = new JPasswordField();
		password1.setBounds(121, 285, 145, 20);
		panel.add(password1);
		
		confirm = new JPasswordField();
		confirm.setBounds(121, 310, 145, 20);
		panel.add(confirm);
		
		JLabel firstname = new JLabel("First Name");
		firstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstname.setBounds(10, 100, 92, 14);
		panel.add(firstname);
		
		firstnametb = new JTextField();
		firstnametb.setBounds(121, 98, 145, 20);
		panel.add(firstnametb);
		firstnametb.setColumns(10);
		
		lastnametb = new JTextField();
		lastnametb.setBounds(121, 129, 145, 20);
		panel.add(lastnametb);
		lastnametb.setColumns(10);
		
		emailtb = new JTextField();
		emailtb.setBounds(121, 160, 145, 20);
		panel.add(emailtb);
		emailtb.setColumns(10);
		
		pntb = new JTextField();
		pntb.setBounds(121, 191, 145, 20);
		panel.add(pntb);
		pntb.setColumns(10);
		
		agetb = new JTextField();
		agetb.setBounds(121, 222, 145, 20);
		panel.add(agetb);
		agetb.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 132, 92, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 163, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("phone number");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 194, 92, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 225, 46, 14);
		panel.add(lblNewLabel_6);
		
		gendertb = new JTextField();
		gendertb.setBounds(121, 254, 145, 20);
		panel.add(gendertb);
		gendertb.setColumns(10);
		
		JLabel Gender = new JLabel("Gender");
		Gender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Gender.setBounds(10, 257, 73, 19);
		panel.add(Gender);
>>>>>>> Stashed changes
	}
}
