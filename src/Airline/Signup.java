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
import javax.swing.ImageIcon;
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
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JPasswordField confirm;
	private JTextField first;
	private JTextField last;
	private JTextField phoneNum;
	private JTextField emailAddress;
	//private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JDateChooser dateOfBirth;

	

	//Create the frame
	public Signup() {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
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
		lblNewLabel.setBounds(21, 258, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 298, 92, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmpassword");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(21, 338, 92, 26);
		panel.add(lblNewLabel_2);
		
		user = new JTextField();
		user.setBounds(131, 258, 145, 28);
		panel.add(user);
		user.setColumns(10);
		
		//Signup button creates new user in DB using given username and password
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyField()) {
					JOptionPane.showMessageDialog(null, "Error: Empty field");
				}
				else {
					attemptSignup();
				}
			}
		});
		btnNewButton.setBounds(110, 381, 89, 23);
		panel.add(btnNewButton);
		
		pass = new JPasswordField();
		pass.setBounds(131, 298, 145, 28);
		panel.add(pass);
		
		confirm = new JPasswordField();
		confirm.setBounds(131, 338, 145, 28);
		panel.add(confirm);
		
		JLabel lblName = new JLabel("First Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(21, 28, 57, 26);
		panel.add(lblName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLastName.setBounds(21, 68, 81, 26);
		panel.add(lblLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGender.setBounds(21, 108, 57, 26);
		panel.add(lblGender);
		
		JLabel lblDOB = new JLabel("Date of birth");
		lblDOB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDOB.setBounds(21, 138, 81, 26);
		panel.add(lblDOB);
		
		JLabel lblPhoneNumbe = new JLabel("Phone Number");
		lblPhoneNumbe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNumbe.setBounds(21, 178, 92, 26);
		panel.add(lblPhoneNumbe);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(21, 218, 57, 26);
		panel.add(lblEmail);
		
		first = new JTextField();
		first.setColumns(10);
		first.setBounds(131, 28, 145, 28);
		panel.add(first);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(131, 68, 145, 28);
		panel.add(last);
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(131, 178, 145, 28);
		panel.add(phoneNum);
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		emailAddress.setBounds(131, 218, 145, 28);
		panel.add(emailAddress);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnMale.setBounds(131, 108, 63, 23);
		panel.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rdbtnFemale.setBounds(216, 108, 63, 23);
		panel.add(rdbtnFemale);
		
		ButtonGroup btnBg = new ButtonGroup();  
	    btnBg.add(rdbtnMale);
	    btnBg.add(rdbtnFemale);
	    btnBg.setSelected(rdbtnMale.getModel(), true);
	    
	    
	    dateOfBirth = new JDateChooser();
	    dateOfBirth.setDateFormatString("MM-dd-yyyy");
	    dateOfBirth.setBounds(131, 138, 145, 28);
	    panel.add(dateOfBirth);
	    JTextFieldDateEditor editor = (JTextFieldDateEditor) dateOfBirth.getDateEditor();
	    editor.setEditable(false);
	}
	
	/*
	 * Formats strings into proper noun format:
	 * First letter capitalized, all other letters lowercase, all whitespace removed
	 */
	public String formatName(String n) {
		if(n == null || n.isEmpty()) {
			return n;
		}
		String name = n.replaceAll("\\s", "");
		name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}
	
	/*
	 * Returns true if any editable fields are blank otherwise returns false
	 */
	public boolean checkEmptyField() {
		if(first.getText().isBlank() || 
		   last.getText().isBlank()  ||
		   ((JTextField)dateOfBirth.getDateEditor().getUiComponent()).getText().isBlank() ||
		   phoneNum.getText().isBlank() ||
		   emailAddress.getText().isBlank() || 
		   user.getText().isBlank() ||
		   String.valueOf(pass.getPassword()).isBlank()) {
			return true;
		}
		return false;
	}
	
	/*
	 * Triggered when Signup button is pressed and no field is empty
	 */
	public void attemptSignup() {
		PreparedStatement pst4 = null;
		String firstname = formatName(first.getText());
		String lastname = formatName(last.getText());
		String gender;
		
		if(rdbtnMale.isSelected()) {
			gender = "Male";
		}
		else {
			gender = "Female";
		}
		
		String DOB = ((JTextField)dateOfBirth.getDateEditor().getUiComponent()).getText();
		String phonenumber = phoneNum.getText();
		String email = emailAddress.getText();
		String username = user.getText();
		String password = String.valueOf(pass.getPassword());
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
				pst4.setString(4, DOB);
				pst4.setString(5, phonenumber);
				pst4.setString(6,  email);
				pst4.setString(7,  username);
				pst4.setString(8,  password);
				pst4.executeUpdate();

				pst4.close();
				connectDB.close();
				JOptionPane.showMessageDialog(null, "Successfully Signed up");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "password does not match");
			}	
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "error");
			}
	}
}