package Airline;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewCustomers extends JFrame {

	private JPanel contentPane;
	private JTable table;



	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public ViewCustomers() {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setResizable(false);
		setTitle("View Customers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 64, 730, 341);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "First Name", "Last Name", "Gender", "Age", "Phone Number", "Email"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		DatabaseConnection connect5 = new DatabaseConnection();
        Connection connectDB = connect5.getConnection();
        PreparedStatement pst5;
		
		try {
			

		String query5="select * from customers";
		pst5= connectDB.prepareStatement(query5);
	
		ResultSet rs5 = pst5.executeQuery();
		
		ResultSetMetaData rs5m = rs5.getMetaData();
		
		int c;
		c = rs5m.getColumnCount();
		
		DefaultTableModel DT = (DefaultTableModel)table.getModel();
		DT.setRowCount(0);
		
		while(rs5.next()) {
			@SuppressWarnings("rawtypes")
			Vector v2 = new Vector();
			
			for (int i = 1; i<=c; i++) {
				
				v2.add(rs5.getString("cus_id"));
				v2.add(rs5.getString("firstname"));
				v2.add(rs5.getString("lastname"));
				v2.add(rs5.getString("gender"));
				v2.add(rs5.getString("age"));
				v2.add(rs5.getString("phonenumber"));
				v2.add(rs5.getString("email"));
				
				
			}
			
			DT.addRow(v2);
		}
		
		
		
		
		pst5.close();
		connectDB.close();
	}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "error");
		}
		
		
	}
}
