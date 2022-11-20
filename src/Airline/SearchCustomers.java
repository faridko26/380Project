package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SearchCustomers extends JFrame {
	
	private JTextField tfUser;
	private final JButton btnSearch = new JButton("Search");
	Connection con;
	PreparedStatement pst1;
	private JTable table;
	String username;
	
	//Establish connection to DB
	public static Connection getConnection() throws URISyntaxException, SQLException {
	    
		URI dbUri = new URI("postgres://sddbvrkvkbkbcz:61bdd3cfd6dcad474f70747d694116ca58f7cef4cff3986bdba0e7fa15a54317@ec2-44-209-158-64.compute-1.amazonaws.com:5432/dovqiu3kter09");
	    
	    Connection conn;
	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
	    conn=DriverManager.getConnection(dbUrl, username, password);

	    return conn;
	}
	
	//create frame
	public SearchCustomers(Session s) {
		
		setBounds(100,150,830,626);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Search Customer");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		btnSearch.setBounds(351, 61, 119, 23);
		panel_1.add(btnSearch);
		
		JLabel DisplayResult = new JLabel("");
		DisplayResult.setHorizontalAlignment(SwingConstants.CENTER);
		DisplayResult.setBounds(10, 39, 316, 56);
		panel_1.add(DisplayResult);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 106, 548, 383);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"username", "email", "phonenumber", "age", "gender", "lastname", "firstname", "cus_id"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		tfUser = new JTextField();
		tfUser.setBounds(313, 30, 187, 20);
		panel_1.add(tfUser);
		tfUser.setColumns(10);
		
		//Search button compares given username with each username in DB
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String firstname = tfUser.getText();
				
				
				
				try {
					
					con =  getConnection();
					
					String query1="select * from customers where firstname =?";
					pst1= con.prepareStatement(query1);
					pst1.setString(1,  firstname);
					ResultSet rs1 = pst1.executeQuery();
					DefaultTableModel model = (DefaultTableModel) table.getModel();

						while(rs1.next()) {
							
							String email = rs1.getString("email");
							String phonenumber = rs1.getString("phonenumber");
							String age = rs1.getString("age");
							String gender = rs1.getString("gender");
							String lastname = rs1.getString("lastname");
							String fname = rs1.getString("firstname");
							String username = rs1.getString("username");
							String cusid = rs1.getString("cus_id");
							
							model.addRow(new Object[]{username, email, phonenumber, age, gender, lastname, fname, cusid});
						}
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, "error");
				}
			
			}
		});
		
	}
}