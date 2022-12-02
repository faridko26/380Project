package Airline;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyBooking extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel labelbookid;
	private JLabel lblFlightId;
	private JLabel labelflightid;
	private JLabel lblSeatsNumber;
	private JLabel labelseatno;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public MyBooking(Session s) {
		
		
		DatabaseConnection connect5 = new DatabaseConnection();
        Connection connectDB = connect5.getConnection();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					connectDB.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
        
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setResizable(false);
		setTitle("My Booking");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 710, 244);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				DefaultTableModel DT = (DefaultTableModel)table.getModel();
				
				int selectedIndex = table.getSelectedRow();
				
				
				labelbookid.setText(DT.getValueAt(selectedIndex, 0).toString());
				labelflightid.setText(DT.getValueAt(selectedIndex, 1).toString());
				labelseatno.setText(DT.getValueAt(selectedIndex, 7).toString());
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book id", "Flight id", "Flight No", "From", "To", "Dep_Time", "Dep_Date", "Seats_No", "Price"
			}
		));
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Cancel Selected Flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		        PreparedStatement pst10 = null;
		        PreparedStatement pst11 = null;
		        PreparedStatement pst12 = null;
		        PreparedStatement pst13 = null;
		        String availableseat = null;
		        
		       
		        try {
		        	String query8 = "select * from flight where flight_id =?";
					pst10= connectDB.prepareStatement(query8);
					pst10.setInt(1,  Integer.parseInt(labelflightid.getText()));
					ResultSet rs1 = pst10.executeQuery();
					if(rs1.next()) {
						 
						 availableseat = rs1.getString("availableseats");
					}
						
						String query9 = "DELETE from booking where bookid =?";
						pst11= connectDB.prepareStatement(query9);
						pst11.setInt(1,  Integer.parseInt(labelbookid.getText()));
						
						pst11.executeUpdate();
							
						String query10 = "update flight set availableseats=? where flight_id =?";	
						String UpdatedSeat;
						int updateseat = Integer.parseInt(availableseat) + Integer.parseInt(labelseatno.getText().toString());
						
						UpdatedSeat = Integer.toString(updateseat);
						pst12=connectDB.prepareStatement(query10);
						pst12.setString(1, UpdatedSeat);
						pst12.setInt(2, Integer.parseInt(labelflightid.getText()));
						pst12.executeUpdate();
							
							
							// sending confirmation Email
							
							SendEmail email = new SendEmail();
							String msg="your ticket is cancelled";
							email.send(s.getEmail(),msg);
							
							
						JOptionPane.showMessageDialog(null, "Successfully Cancelled and Confirmation Email was sent");
						
						String query5="select bookid,flightid,flightnumber,origin,destination,departuretime,departuredate,seats_no,totalprice from booking INNER JOIN customers on cusid=cus_id INNER JOIN flight on flightid=flight_id where cusid=?";
						
						pst13 = connectDB.prepareStatement(query5);
						pst13.setInt(1,  Integer.parseInt(s.getCus_id()));
						ResultSet rs5 = pst13.executeQuery();
						
						ResultSetMetaData rs5m = rs5.getMetaData();
						
						int c;
						c = rs5m.getColumnCount();
						
						
						DefaultTableModel DT = (DefaultTableModel)table.getModel();
						DT.setRowCount(0);
						
						while(rs5.next()) {
						
							@SuppressWarnings("rawtypes")
							Vector v2 = new Vector();
							
							for (int i = 1; i<=c; i++) {
								
								v2.add(rs5.getString("bookid"));
								v2.add(rs5.getString("flightid"));
								v2.add(rs5.getString("flightnumber"));
								v2.add(rs5.getString("origin"));
								v2.add(rs5.getString("destination"));
								v2.add(rs5.getString("departuretime"));
								v2.add(rs5.getString("departuredate"));
								v2.add(rs5.getString("seats_no"));
								v2.add(rs5.getString("totalprice"));
								
								
							}
							
							DT.addRow(v2);
							
							
							
							
						}
						
						
						
						
						pst13.close();
						
					
					
					pst10.close();
					pst11.close();
					pst12.close();
					
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(499, 336, 197, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Book ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(59, 280, 76, 28);
		contentPane.add(lblNewLabel);
		
		labelbookid = new JLabel("");
		labelbookid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelbookid.setBounds(145, 280, 76, 28);
		contentPane.add(labelbookid);
		
		lblFlightId = new JLabel("Flight ID");
		lblFlightId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFlightId.setBounds(59, 319, 76, 28);
		contentPane.add(lblFlightId);
		
		labelflightid = new JLabel("");
		labelflightid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelflightid.setBounds(145, 319, 76, 28);
		contentPane.add(labelflightid);
		
		lblSeatsNumber = new JLabel("Seats No");
		lblSeatsNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeatsNumber.setBounds(59, 358, 76, 28);
		contentPane.add(lblSeatsNumber);
		
		labelseatno = new JLabel("");
		labelseatno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelseatno.setBounds(145, 358, 76, 28);
		contentPane.add(labelseatno);
	
		PreparedStatement pst5;
       
		
		try {
			

		String query5="select bookid,flightid,flightnumber,origin,destination,departuretime,departuredate,seats_no,totalprice from booking INNER JOIN customers on cusid=cus_id INNER JOIN flight on flightid=flight_id where cusid=?";
		pst5= connectDB.prepareStatement(query5);
		pst5.setInt(1,  Integer.parseInt(s.getCus_id()));
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
				
				v2.add(rs5.getString("bookid"));
				v2.add(rs5.getString("flightid"));
				v2.add(rs5.getString("flightnumber"));
				v2.add(rs5.getString("origin"));
				v2.add(rs5.getString("destination"));
				v2.add(rs5.getString("departuretime"));
				v2.add(rs5.getString("departuredate"));
				v2.add(rs5.getString("seats_no"));
				v2.add(rs5.getString("totalprice"));
				
				
			}
			
			DT.addRow(v2);
			
			
			
			
		}
		
		
		
		
		pst5.close();
		
	}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "error");
		}
		
		
	}
}
