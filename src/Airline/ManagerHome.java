package Airline;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.border.BevelBorder;

public class ManagerHome extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	Connection con;
	PreparedStatement pst1;
	PreparedStatement pst2;



	
	//Create Frame
	public ManagerHome(Session s) {
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