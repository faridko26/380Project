package Airline;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class UserHome extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserHome(Session s) {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setTitle(s.getUsername() + " - User Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(44, 108, 148));
		panel.setBounds(0, 0, 794, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(0, 128, 255));
		desktopPane.setBounds(0, 570, 924, -567);
		panel.add(desktopPane);
		
		JButton Flights_btn = new JButton("Flights");
		Flights_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Session flightSession = new Session(s.getUsername(),s.getName(),s.getLastname(),s.getEmail(), s.getCus_id());
				Flights book = new Flights(flightSession);
				book.setVisible(true);
				
			}
		});
		Flights_btn.setBounds(84, 99, 113, 41);
		panel.add(Flights_btn);
		
		
		
		JButton Profile_btn = new JButton("Profile");
		Profile_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Profile myprofile = new Profile(s);
				

				int id = Integer.parseInt(s.getCus_id());
				
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
					
					myprofile.first.setText( rs4.getString("firstname"));
					myprofile.last.setText( rs4.getString("lastname"));
					
					String gender = rs4.getString("gender");
					if(gender.equals("Male")) {
						myprofile.rdbtnMale.setSelected(true);
						myprofile.rdbtnFemale.setSelected(false);
					}
					else {
						myprofile.rdbtnMale.setSelected(false);
						myprofile.rdbtnFemale.setSelected(true);
					}
					
					
					myprofile.dateOfBirth.setText( rs4.getString("age"));
					myprofile.phoneNum.setText( rs4.getString("phonenumber"));
					myprofile.emailAddress.setText( rs4.getString("email"));
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
		Profile_btn.setBounds(259, 99, 113, 41);
		panel.add(Profile_btn);
		
		JButton Mybooking_btn = new JButton("Mybooking");
		Mybooking_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Session bookingSession = new Session(s.getUsername(),s.getName(),s.getLastname(),s.getEmail(), s.getCus_id());
				MyBooking frame = new MyBooking(bookingSession);
				frame.setVisible(true);
				
				
				
			}
		});
		Mybooking_btn.setBounds(436, 99, 113, 41);
		panel.add(Mybooking_btn);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(557, 254, 89, 23);
		panel.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 680, 383);
		panel.add(label);
		BufferedImage img = null;
		img = (BufferedImage) getImage("background2.jpg");
		
		Image resizedImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(resizedImage);
		
		label.setIcon(imageIcon);
	
	}
	public Image getImage(String filename) {
		try {
	        return ImageIO.read(getClass().getResourceAsStream(
	                "/" + filename));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
