package Airline;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Profile extends JFrame {

	private JPanel contentPane;
	JTextField first;
	JTextField last;
	JTextField dateOfBirth;
	JTextField phoneNum;
	JTextField emailAddress;
	JTextField user;
	private JPasswordField pass;
	private JPasswordField confirmNewpass;
	JRadioButton rdbtnMale;
	JRadioButton rdbtnFemale;



	/**
	 * Create the frame.
	 */
	public Profile(Session s) {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(46, 40, 66, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(46, 70, 89, 26);
		contentPane.add(lblLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(46, 100, 46, 26);
		contentPane.add(lblGender);
		
		JLabel lblDOB = new JLabel("Date of birth");
		lblDOB.setBounds(46, 130, 78, 26);
		contentPane.add(lblDOB);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(46, 160, 110, 26);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(46, 190, 46, 26);
		contentPane.add(lblEmail);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setBounds(46, 220, 89, 26);
		contentPane.add(lblUserName);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyField()) {
					JOptionPane.showMessageDialog(null, "Error: Empty field");
				}
				else {
					attemptUpdate(s);
				}
			}
		});
		btnNewButton.setBounds(67, 320, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(229, 320, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblPassword = new JLabel("New Password");
		lblPassword.setBounds(46, 250, 110, 26);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm New Password");
		lblConfirmPassword.setBounds(46, 281, 143, 26);
		contentPane.add(lblConfirmPassword);
		
		first = new JTextField();
		first.setBounds(198, 40, 143, 28);
		contentPane.add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBounds(198, 70, 143, 28);
		contentPane.add(last);
		
		dateOfBirth = new JTextField();
		dateOfBirth.setEditable(false);
		dateOfBirth.setColumns(10);
		dateOfBirth.setBounds(198, 130, 143, 28);
		contentPane.add(dateOfBirth);
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(198, 160, 143, 28);
		contentPane.add(phoneNum);
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		emailAddress.setBounds(198, 190, 143, 28);
		contentPane.add(emailAddress);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(198, 220, 143, 28);
		contentPane.add(user);
		
		pass = new JPasswordField();
		pass.setBounds(198, 250, 143, 28);
		contentPane.add(pass);
		
		confirmNewpass = new JPasswordField();
		confirmNewpass.setBounds(198, 280, 143, 28);
		contentPane.add(confirmNewpass);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(216, 100, 66, 28);
		contentPane.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(284, 100, 78, 28);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup btnBg = new ButtonGroup();  
	    btnBg.add(rdbtnMale);
	    btnBg.add(rdbtnFemale); 
	}

	/*
	 * Triggered when Update button is pressed and no field is empty
	 */
	public void attemptUpdate(Session s) {
		int id = Integer.parseInt(s.getCus_id());
		PreparedStatement pst4 = null;
		DatabaseConnection connect3 = new DatabaseConnection();
		Connection connectprofile = connect3.getConnection();
		
		String firstname1 = formatName(first.getText());
		String lastname1 = formatName(last.getText());
		String gender;
  
		if(rdbtnMale.isSelected()) {
			gender = "Male";
		}
		else {
			gender = "Female";
		}
		
		String DOB = dateOfBirth.getText();
		String phonenumber1 = phoneNum.getText();
		String email1 = emailAddress.getText();
		String username1 = user.getText();
		String password1 = String.valueOf(pass.getPassword());
		String confirmpassword1 = String.valueOf(confirmNewpass.getPassword());
		
		
		try {
			if(password1.equals(confirmpassword1)) {
				String query4="update customers set firstname=?, lastname=?,gender=?,age=?,phonenumber=?,email=?,username=?,password=? where cus_id =?";
				pst4= connectprofile.prepareStatement(query4);
				//pst4.setInt(1, id);

				pst4.setString(1,firstname1);
				pst4.setString(2,lastname1);
				pst4.setString(3,gender);
				pst4.setString(4,DOB);
				pst4.setString(5,phonenumber1);
				pst4.setString(6,email1);
				pst4.setString(7,username1);
				pst4.setString(8,password1);
				pst4.setInt(9,id);
				
				pst4.executeUpdate();
				JOptionPane.showMessageDialog(null, "Successfully updated");
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "password does not match");
			}
		pst4.close();
		connectprofile.close();
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "error");
		}
	}
	
	/*
	 * Returns true if any editable fields are blank otherwise returns false
	 */
	public boolean checkEmptyField() {
		if(first.getText().isBlank() || 
		   last.getText().isBlank()  ||
		   phoneNum.getText().isBlank() ||
		   emailAddress.getText().isBlank() || 
		   user.getText().isBlank() ||
		   String.valueOf(pass.getPassword()).isBlank()) {
			return true;
		}
		return false;
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
}
