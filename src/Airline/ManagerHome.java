package Airline;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public class ManagerHome extends JFrame {

	private JPanel contentPane;
	
	Connection con;
	PreparedStatement pst1;
	PreparedStatement pst2;



	
	//Create Frame
	public ManagerHome(Session s) {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 590);
		setTitle(s.getUsername() + " - Manager Home");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Customer Menu
		JMenu mnNewMenu = new JMenu("Customer");
		menuBar.add(mnNewMenu);
		
		JMenuItem MenuAddCustomer = new JMenuItem("Add Customer");
		mnNewMenu.add(MenuAddCustomer);
		MenuAddCustomer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Signup frame = new Signup();
				frame.setVisible(true);
			}
			
		});
		
		JMenuItem MenuSearchCustomer = new JMenuItem("Search Customer");
		mnNewMenu.add(MenuSearchCustomer);
		MenuSearchCustomer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				SearchCustomers frame = new SearchCustomers(s);
				frame.setVisible(true);
			
			}
		});
		
		JMenuItem MenuViewCustomers = new JMenuItem("View Customers");
		mnNewMenu.add(MenuViewCustomers);
		MenuViewCustomers.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				ViewCustomers frame = new ViewCustomers();
				frame.setVisible(true);
			}
		});
		
		//Flight Menu
		JMenu mnNewMenu_1 = new JMenu("Flight");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add Flight");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addflight add = new Addflight();
				add.setVisible(true);
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Edit Flight");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditFlight edit = new EditFlight(s);
				edit.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		//User Menu
		JMenu mnNewMenu_2 = new JMenu("User");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem createMgmt = new JMenuItem("Create management user");
		mnNewMenu_2.add(createMgmt);
		createMgmt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				ManagerSignUp newMgmt = new ManagerSignUp();
				newMgmt.setVisible(true);
			}
			
			
		});
		
		JMenuItem searchMgmt = new JMenuItem("Search management users");
		mnNewMenu_2.add(searchMgmt);
		searchMgmt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchManagers search = new SearchManagers(s);
				search.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("Logout");
		menuBar.add(mnNewMenu_3);
		JMenuItem exit = new JMenuItem("Exit");
		mnNewMenu_3.add(exit);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel();
		label.setBounds(0, 0, 980, 551);
		contentPane.add(label);
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