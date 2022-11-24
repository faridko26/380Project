package Airline;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class SearchCustomers extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUser;
	private final JButton btnSearch = new JButton("Search");
	private JTable table;
	private JTextField tlUser;
	
	
		
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
				"username", "email", "phonenumber", "age", "gender", "lastname", "firstname", "Cus_id"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(119);
		table.getColumnModel().getColumn(3).setPreferredWidth(63);
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
					
					String query1="select * from customers where firstname =? and lastname =?";
					pst1= connectDB.prepareStatement(query1);
					pst1.setString(1,  firstname);
					pst1.setString(2,  lasttname);
					ResultSet rs1 = pst1.executeQuery();
					
					
					
					
					
					
					
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