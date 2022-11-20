package Airline;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class UserHome extends JFrame {

	private JPanel contentPane;

	public UserHome(Session s) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 610);
		setTitle(s.getUsername() + " - Customer Home");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Flight");
		menuBar.add(menu);
		
		JMenuItem bookFlight = new JMenuItem("Book flight");
		menu.add(bookFlight);
		bookFlight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JMenu mnNewMenu = new JMenu("View");
		menuBar.add(mnNewMenu);
		
		JMenuItem viewBooked = new JMenuItem("Booked flights");
		mnNewMenu.add(viewBooked);
		viewBooked.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ViewBookedFlights frame = new ViewBookedFlights(s);
				frame.setVisible(true);
			}
		});
		
		JMenuItem viewDetails = new JMenuItem("View/edit details");
		mnNewMenu.add(viewDetails);
		
		JMenu mnNewMenu_1 = new JMenu("Logout");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem exit = new JMenuItem("Exit");
		mnNewMenu_1.add(exit);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
	}
}
