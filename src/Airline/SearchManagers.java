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

public class SearchManagers extends JFrame {
	
	private JTextField tfUser;
	private final JButton btnSearch = new JButton("Search");
	
	private JTable table;
	private JTextField tlUser;
	
	
		
	//create frame
	public SearchManagers(Session s) {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setBounds(100,150,830,626);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Search Customer");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		btnSearch.setBounds(534, 72, 119, 23);
		panel_1.add(btnSearch);
		
		JLabel DisplayResult = new JLabel("");
		DisplayResult.setHorizontalAlignment(SwingConstants.CENTER);
		DisplayResult.setBounds(10, 39, 316, 56);
		panel_1.add(DisplayResult);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 106, 722, 383);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"First Name", "Last Name", "Email", "User Name", "Admin ID"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(131);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(2).setPreferredWidth(139);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(92);
		scrollPane.setViewportView(table);
		
		tfUser = new JTextField();
		tfUser.setBounds(281, 11, 187, 20);
		panel_1.add(tfUser);
		tfUser.setColumns(10);
		
		tlUser = new JTextField();
		tlUser.setColumns(10);
		tlUser.setBounds(281, 39, 187, 20);
		panel_1.add(tlUser);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(188, 14, 83, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(188, 42, 83, 14);
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
					
					String query1="select * from admin where firstname =? and lastname =?";
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
							
							String fname = rs1.getString("firstname");
							String lname = rs1.getString("lastname");
							String email = rs1.getString("admin_email");
							String user = rs1.getString("admin_username");
							String admin_id = rs1.getString("admin_id");
							
							
							model.addRow(new Object[]{fname, lname, email, user,admin_id});
						}
					
					
					
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, "error");
				}
			
			}
		});
		
	}
}