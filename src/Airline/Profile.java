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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Profile extends JFrame {

	private JPanel contentPane;
	JTextField name;
	JTextField lastname;
	JTextField age;
	JTextField phonenumber;
	JTextField email;
	JTextField user;
	private JPasswordField password;
	private JPasswordField confirmNewpass;
	JRadioButton rdbtnMale;
	JRadioButton rdbtnFemale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Profile() {
		setResizable(false);
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(46, 39, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(46, 64, 89, 14);
		contentPane.add(lblLastName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(46, 89, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(46, 114, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(46, 139, 110, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(46, 164, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(46, 189, 89, 14);
		contentPane.add(lblUserName);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(Login.customer_id);
				
				PreparedStatement pst4 = null;
				DatabaseConnection connect3 = new DatabaseConnection();
		        Connection connectprofile = connect3.getConnection();
		        
		        String firstname1 = name.getText();
			    String lastname1 = lastname.getText();
			    String gender;
			  
			    
				if(rdbtnMale.isSelected()) {
			    	gender = "Male";
			    	rdbtnFemale.disable();
			    	
			    }
			    else {
			    	gender = "Female";
			    	rdbtnMale.setSelected(false);
			    }
			    
			    
			    
			    
			    String age1 = age.getText();
			    String phonenumber1 = phonenumber.getText();
			    String email1 = email.getText();
				String username1 = user.getText();
				String password1 = String.valueOf(password.getPassword());
				String confirmpassword1 = String.valueOf(confirmNewpass.getPassword());
		        
				
				try {
					
				if(password1.equals(confirmpassword1)) {
					String query4="update customers set firstname=?, lastname=?,gender=?,age=?,phonenumber=?,email=?,username=?,password=? where cus_id =?";
					pst4= connectprofile.prepareStatement(query4);
					//pst4.setInt(1, id);
				
		
					pst4.setString(1,firstname1);
					pst4.setString(2,lastname1);
					pst4.setString(3,gender);
					pst4.setString(4,age1);
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
		});
		btnNewButton.setBounds(67, 291, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(229, 291, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblPassword = new JLabel("New Password");
		lblPassword.setBounds(46, 214, 110, 14);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm New Password");
		lblConfirmPassword.setBounds(46, 239, 143, 14);
		contentPane.add(lblConfirmPassword);
		
		name = new JTextField();
		name.setBounds(216, 36, 125, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(216, 61, 125, 20);
		contentPane.add(lastname);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(216, 111, 125, 20);
		contentPane.add(age);
		
		phonenumber = new JTextField();
		phonenumber.setColumns(10);
		phonenumber.setBounds(216, 136, 125, 20);
		contentPane.add(phonenumber);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(216, 161, 125, 20);
		contentPane.add(email);
		
		user = new JTextField();
		user.setColumns(10);
		user.setBounds(216, 186, 125, 20);
		contentPane.add(user);
		
		password = new JPasswordField();
		password.setBounds(216, 211, 125, 20);
		contentPane.add(password);
		
		confirmNewpass = new JPasswordField();
		confirmNewpass.setBounds(216, 236, 125, 20);
		contentPane.add(confirmNewpass);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(226, 85, 66, 23);
		contentPane.add(rdbtnMale);
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(296, 85, 78, 23);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup btnBg = new ButtonGroup();  
	    btnBg.add(rdbtnMale);
	    btnBg.add(rdbtnFemale); 
	}
}
