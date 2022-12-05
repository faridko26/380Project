package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class SearchCustomers extends JFrame {
	
	private JTextField tfUser;
	private final JButton btnSearch = new JButton("Search");
	
	private JTable table;
	private JTextField tlUser;
	
	
		
	//create frame
	public SearchCustomers(Session s) {
		setTitle("Search Customers");
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setBounds(100,100,830,590);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 816, 26);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 26, 816, 563);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		btnSearch.setBounds(534, 50, 119, 28);
		panel_1.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 106, 722, 400);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"username", "email", "phonenumber", "age", "gender", "lastname", "firstname", "Cus_id"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(119);
		table.getColumnModel().getColumn(3).setPreferredWidth(63);
		scrollPane.setViewportView(table);
		
		tfUser = new JTextField();
		tfUser.setBounds(281, 10, 187, 28);
		panel_1.add(tfUser);
		tfUser.setColumns(10);
		
		tlUser = new JTextField();
		tlUser.setColumns(10);
		tlUser.setBounds(281, 50, 187, 28);
		panel_1.add(tlUser);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(188, 10, 83, 26);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(188, 50, 83, 26);
		panel_1.add(lblNewLabel_1_1);
		
		//Search button compares given username with each username in DB
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String firstname = tfUser.getText();
				String lasttname = tlUser.getText();
				DatabaseConnection connect1 = new DatabaseConnection();
		        Connection connectDB = connect1.getConnection();
		        PreparedStatement pst1;
				
				try {
					
					String query1="select * from customers where firstname =? and lastname =?";
					pst1= connectDB.prepareStatement(query1);
					pst1.setString(1,  firstname);
					pst1.setString(2,  lasttname);
					ResultSet rs1 = pst1.executeQuery();
					
					ResultSetMetaData rs5m = rs1.getMetaData();
					
					int c;
					c = rs5m.getColumnCount();
					
					
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					
					
						while(rs1.next()) {
							
							String email = rs1.getString("email");
							String phonenumber = rs1.getString("phonenumber");
							String age = rs1.getString("age");
							String gender = rs1.getString("gender");
							String lastname = rs1.getString("lastname");
							String fname = rs1.getString("firstname");
							String username = rs1.getString("username");
							String cus_id = rs1.getString("cus_id");
							
							
							model.addRow(new Object[]{username, email, phonenumber, age, gender, lastname, fname,cus_id});
						}
					
					
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, "error");
				}
			
			}
		});
		
	}
}