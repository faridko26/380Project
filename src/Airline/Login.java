package Airline;
import java.awt.EventQueue;





import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField usernameField;
	private JPasswordField passwordField;
	private PreparedStatement pst1;
	private PreparedStatement pst2;
	
	//Launch the Application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
					Login frame = new Login();
					frame.setVisible(true);
				
			}
		});
	}
	

	
	//Create the frame
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(72, 84, 79, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(72, 120, 67, 14);
		panel.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setBounds(161, 82, 167, 20);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(161, 118, 167, 20);
		panel.add(passwordField);
		
		//Login Button checks for user or admin login
		JButton Login_Button = new JButton("Login");
		Login_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				DatabaseConnection connect1 = new DatabaseConnection();
		        Connection connectDB = connect1.getConnection();
				
				try {
					

				String query1="select * from admin where admin_username =? and admin_password =?";
				pst1= connectDB.prepareStatement(query1);
				pst1.setString(1,  username);
				pst1.setString(2, password);
				ResultSet rs1 = pst1.executeQuery();
				
				String query2="select * from customers where username =? and password =?";
				pst2= connectDB.prepareStatement(query2);
				pst2.setString(1,  username);
				pst2.setString(2, password);
				ResultSet rs2 = pst2.executeQuery();
				
				
				
				
				
				if(rs1.next()) { //admin login
					
					Session adminSession = new Session(rs1.getString("admin_username"),rs1.getString("firstname"),rs1.getString("lastname"),rs1.getString("admin_email"), rs1.getString("admin_id"));
					dispose();
					JOptionPane.showMessageDialog(null, "Login Successfully");
					ManagerHome frame = new ManagerHome(adminSession);
					frame.setVisible(true);
				}
				else if(rs2.next()) { //user login
					
					Session userSession = new Session(rs2.getString("username"), rs2.getString("firstname"), rs2.getString("lastname"), rs2.getString("email"), rs2.getString("cus_id"));
					dispose();
					JOptionPane.showMessageDialog(null, "Login Successfully");
					UserHome frame = new UserHome(userSession);
					
					
					frame.setVisible(true);
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Login failed");
				}
				
				pst1.close();
				pst2.close();
				connectDB.close();
			}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "error");
				}
			}
		});
		Login_Button.setBounds(106, 186, 89, 23);
		panel.add(Login_Button);
		
		//Signup button opens new frame
		JButton Signup_Button = new JButton("Signup");
		Signup_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup frame = new Signup();
				frame.setVisible(true);
				
			}
		});
		
		Signup_Button.setBounds(256, 186, 89, 23);
		panel.add(Signup_Button);
	}
}
