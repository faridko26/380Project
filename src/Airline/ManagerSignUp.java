package Airline;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ManagerSignUp extends JFrame {
	private JPanel contentPane;
	private JTextField username1;
	private JPasswordField password1;
	private JPasswordField confirm;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	//Create the frame
	public ManagerSignUp() {
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
		lblNewLabel.setBounds(20, 11, 57, 26);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(20, 144, 57, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(20, 178, 111, 14);
		panel.add(lblNewLabel_2);
		
		username1 = new JTextField();
		username1.setBounds(131, 15, 145, 20);
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
				String fname = textField.getText();
				String lname = textField_1.getText();
				String email = textField_2.getText();
				String confirmpassword = String.valueOf(confirm.getPassword());
				DatabaseConnection connectNow = new DatabaseConnection();
		        Connection connectDB = connectNow.getConnection();
				
				try {
					
					stmt=connectDB.createStatement();
					if(password.equals(confirmpassword)) {
						String query="insert into admin (admin_username,admin_password,firstname,lastname,admin_email) "
								+ "values('"+username+"', '"+password+"', '"+fname+"', '"+lname+"', '"+email+"')";
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
		password1.setBounds(131, 142, 145, 20);
		panel.add(password1);
		
		confirm = new JPasswordField();
		confirm.setBounds(131, 176, 145, 20);
		panel.add(confirm);
		
		JLabel lblNewLabel_3 = new JLabel("First");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(20, 48, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Last");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(20, 80, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(20, 114, 46, 14);
		panel.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(131, 46, 145, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(131, 77, 145, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(131, 111, 145, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
	}
}
