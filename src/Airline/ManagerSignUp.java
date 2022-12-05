package Airline;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;


public class ManagerSignUp extends JFrame {
	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JPasswordField confirm;
	private JTextField first;
	private JTextField last;
	private JTextField emailAddress;

	//Create the frame
	public ManagerSignUp() {
		setResizable(false);
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setTitle("Manager Sign Up");
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
		lblNewLabel.setBounds(20, 15, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 139, 57, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(20, 170, 111, 26);
		panel.add(lblNewLabel_2);
		
		user = new JTextField();
		user.setBounds(131, 15, 145, 28);
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
		
		btnNewButton.setBounds(99, 225, 89, 23);
		panel.add(btnNewButton);
		
		pass = new JPasswordField();
		pass.setBounds(131, 139, 145, 28);
		panel.add(pass);
		
		confirm = new JPasswordField();
		confirm.setBounds(131, 170, 145, 28);
		panel.add(confirm);
		
		JLabel lblNewLabel_3 = new JLabel("First");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(20, 46, 46, 26);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(20, 77, 46, 26);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(20, 108, 46, 26);
		panel.add(lblNewLabel_5);
		
		first = new JTextField();
		first.setBounds(131, 46, 145, 28);
		panel.add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setBounds(131, 77, 145, 28);
		panel.add(last);
		last.setColumns(10);
		
		emailAddress = new JTextField();
		emailAddress.setBounds(131, 108, 145, 28);
		panel.add(emailAddress);
		emailAddress.setColumns(10);
	}

	public void attemptSignup() {
		PreparedStatement stmt = null;
		String username = user.getText();
		String password = String.valueOf(pass.getPassword());
		String fname = formatName(first.getText());
		String lname = formatName(last.getText());
		String email = emailAddress.getText();
		String confirmpassword = String.valueOf(confirm.getPassword());
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		try {
			if(password.equals(confirmpassword)) {
				String query="insert into admin (admin_username,admin_password,firstname,lastname,admin_email) "
						+ "values(?, ?, ?, ?, ?)";
				stmt= connectDB.prepareStatement(query);
				stmt.setString(1,  username);
				stmt.setString(2, password);
				stmt.setString(3,  fname);
				stmt.setString(4, lname);
				stmt.setString(5, email);
			
				stmt.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Successfully Signed up");
				dispose();
				stmt.close();
				connectDB.close();
			}
			else {
				JOptionPane.showMessageDialog(null, "password does not match");
			}
		}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "error");
			}
	}
	
	public boolean checkEmptyField() {
		if(first.getText().isBlank() || 
		   last.getText().isBlank()  ||
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