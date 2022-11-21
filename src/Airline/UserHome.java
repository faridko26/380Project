package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class UserHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHome frame = new UserHome();
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
	public UserHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 108, 148));
		panel.setBounds(0, 0, 924, 571);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 128, 255));
		desktopPane.setBounds(0, 570, 924, -567);
		panel.add(desktopPane);
		
		JButton Flights_btn = new JButton("Flights");
		Flights_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Flights book = new Flights();
				book.setVisible(true);
				
			}
		});
		Flights_btn.setBounds(176, 241, 113, 41);
		panel.add(Flights_btn);
		
		
		
		JButton Profile_btn = new JButton("Profile");
		Profile_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Profile myprofile = new Profile();
				

				int id = Integer.parseInt(Login.customer_id);
				
				PreparedStatement pst3;
				DatabaseConnection connect2 = new DatabaseConnection();
		        Connection connectprofile = connect2.getConnection();
		        
				
				try {
					

				String query3="select * from customers where cus_id = ?";
				pst3= connectprofile.prepareStatement(query3);
				pst3.setInt(1, id);
				
				ResultSet rs4 = pst3.executeQuery();
				
				
				if(rs4.next()==false) {
					JOptionPane.showMessageDialog(null, "Record not found");
					
				}
				
				
				else {
					
					myprofile.name.setText( rs4.getString("firstname"));
					myprofile.lastname.setText( rs4.getString("lastname"));
					
					String gender = rs4.getString("gender");
					if(gender.equals("Male")) {
						myprofile.rdbtnMale.setSelected(true);
						myprofile.rdbtnFemale.setSelected(false);
					}
					else {
						myprofile.rdbtnMale.setSelected(false);
						myprofile.rdbtnFemale.setSelected(true);
					}
					
					
					myprofile.age.setText( rs4.getString("age"));
					myprofile.phonenumber.setText( rs4.getString("phonenumber"));
					myprofile.email.setText( rs4.getString("email"));
					myprofile.user.setText( rs4.getString("username"));
					
				}
				
				pst3.close();
				
				connectprofile.close();
				
			}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "error");
				}
				
			    myprofile.setVisible(true);
			}
		});
		Profile_btn.setBounds(391, 241, 113, 41);
		panel.add(Profile_btn);
		
		JButton Mybooking_btn = new JButton("Mybooking");
		Mybooking_btn.setBounds(597, 241, 113, 41);
		panel.add(Mybooking_btn);
		
	
	}
}
