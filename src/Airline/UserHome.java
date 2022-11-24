package Airline;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public UserHome(Session s) {
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
				

				//Session flightSession = new Session(s.getUsername(),s.getName(),s.getLastname(),s.getEmail(), s.getCus_id());
				Flights book = new Flights(s);
				book.setVisible(true);
				
			}
		});
		Flights_btn.setBounds(84, 99, 113, 41);
		panel.add(Flights_btn);
		
		
		
		JButton Profile_btn = new JButton("Profile");
		Profile_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Session profileSession = new Session(s.getUsername(),s.getName(),s.getLastname(),s.getEmail(), s.getCus_id());
				Profile myprofile = new Profile(s);
				myprofile.setVisible(true);
				  

			}
		});
		Profile_btn.setBounds(259, 99, 113, 41);
		panel.add(Profile_btn);
		
		JButton Mybooking_btn = new JButton("Mybooking");
		Mybooking_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Session bookingSession = new Session(s.getUsername(),s.getName(),s.getLastname(),s.getEmail(), s.getCus_id());
				MyBooking frame = new MyBooking(s);
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
		
	
	}
}
