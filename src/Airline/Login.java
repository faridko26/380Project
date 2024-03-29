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
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends JFrame {

	private JPanel contentPane;
	private static JTextField usernameField;
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
<<<<<<< Updated upstream
	static String cus_id; 
	
=======
	private String name;
>>>>>>> Stashed changes
	
	PreparedStatement pst1;
	PreparedStatement pst2;
	
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
<<<<<<< Updated upstream
		setResizable(false);
=======
		
>>>>>>> Stashed changes
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
		
		JLabel lblNewLabel = new JLabel("User Name");
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
				name = username;
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
				
				String query3="select * from customers where username =? and password =?";
				pst2= connectDB.prepareStatement(query3);
				pst2.setString(1,  username);
				pst2.setString(2, password);
				ResultSet rs3 = pst2.executeQuery();
				
				if(rs3.next()) {
				cus_id = rs3.getString("id");
				}
				
				if(rs1.next()) { //admin login
					
					Session adminSession = new Session(username, rs1.getString("admin_id"));
					dispose();
					JOptionPane.showMessageDialog(null, "Login Successfully");
					ManagerHome frame = new ManagerHome(adminSession);
					frame.setVisible(true);
				}
				else if(rs2.next()) { //user login
					
					Session userSession = new Session(username, rs2.getString("cus_id"));
					dispose();
					JOptionPane.showMessageDialog(null, "Login Successfully");
<<<<<<< Updated upstream
					UserHome frame = new UserHome();
					
					
=======
					UserHome frame = new UserHome(userSession);
>>>>>>> Stashed changes
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
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
