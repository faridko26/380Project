package Airline;

import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

public class ViewBookedFlights extends JFrame{
	
	private JPanel contentPane;
	 Connection con;
	 PreparedStatement pst1;
	 private JTable table;
	
	 public static Connection getConnection() throws URISyntaxException, SQLException {
		    
		 URI dbUri = new URI("postgres://sddbvrkvkbkbcz:61bdd3cfd6dcad474f70747d694116ca58f7cef4cff3986bdba0e7fa15a54317@ec2-44-209-158-64.compute-1.amazonaws.com:5432/dovqiu3kter09");
		    
		    Connection conn;
		    
		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		    conn = DriverManager.getConnection(dbUrl, username, password);

		    return conn;
		}
	
	public ViewBookedFlights(Session s) {
		
		setSize(383,364);
		setVisible(true);
		setTitle(s.getCus_id());
		
        getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 351, 306);
        getContentPane().add(scrollPane);
        
        JPanel panel = new JPanel();
        scrollPane.setRowHeaderView(panel);
        panel.setLayout(null);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"book_id", "cus_id", "flight_id", "price", "seats", "date" 
        	}
        ));
        scrollPane.setViewportView(table);
        
        table = new JTable() {
        	
        	@Override
        	   public boolean isCellEditable(int row, int column) {       
        	       return false; // or a condition at your choice with row and column
        	   }
        };
        table.setRowSelectionAllowed(true);
        table.setCellSelectionEnabled(false);
        table.setShowHorizontalLines(true);
        table.setForeground(new Color(0, 0, 0));
		
		try {
			
    		con = getConnection();	    		
    		//Selects data from users table
    		String query1 = "select * from booking where cus_id =?";
			pst1= con.prepareStatement(query1);
			pst1.setString(1,  s.getCus_id());
			ResultSet rs1 = pst1.executeQuery();
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			while(rs1.next()) {
				
				String d = rs1.getString("book_id");
				String cid = rs1.getString("cus_id");
				String fid = rs1.getString("flight_id");
				String price = rs1.getString("price");
				String seats = rs1.getString("seats");
				String date = rs1.getString("date");
				
				model.addRow(new Object[]{d, cid, fid, price, seats, date});
			}
			
    	} catch(Exception e1) {
    		
			JOptionPane.showMessageDialog(null, "NO FLIGHT FOUND");
		}
	}
}
