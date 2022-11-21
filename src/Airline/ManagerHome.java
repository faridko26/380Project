package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.BevelBorder;

public class ManagerHome extends JFrame {

	private JPanel contentPane;
	
	Connection con;
	PreparedStatement pst1;
	PreparedStatement pst2;


	public static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI("postgres://sddbvrkvkbkbcz:61bdd3cfd6dcad474f70747d694116ca58f7cef4cff3986bdba0e7fa15a54317@ec2-44-209-158-64.compute-1.amazonaws.com:5432/dovqiu3kter09");
	    
	    Connection conn;
	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
	    conn=DriverManager.getConnection(dbUrl, username, password);

	    return conn;
	}
	
	//Create Frame
	public ManagerHome() {
		setTitle("Manager Home Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 590);
		
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
			SearchCustomers frame = new SearchCustomers();
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Booked Flights");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		//User Menu
		JMenu mnNewMenu_2 = new JMenu("User");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Create Management user");
		mnNewMenu_2.add(mntmNewMenuItem_4);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(new Color(128, 128, 255));
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 984, 529);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setForeground(new Color(128, 128, 255));
		desktopPane.setBackground(new Color(128, 128, 255));
		desktopPane.setBounds(0, 523, 974, -522);
		panel.add(desktopPane);
		
	}
}
