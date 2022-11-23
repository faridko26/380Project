package Airline;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class EditFlight extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JTextField textFlightno;
	private JTextField textDep_time;
	private JTextField textArr_time;
	private JTextField textPrice;
	private JTextField textSeats;
	private JLabel lblFlightId;
	private JLabel labelFlightid;
	private JComboBox Totxt;
	private JComboBox  Fromtxt;
	private JDateChooser textdateChooser;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public EditFlight(Session s) {
		
		DatabaseConnection connect5 = new DatabaseConnection();
        Connection connectDB = connect5.getConnection();
        PreparedStatement pst5;
        
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
		
		
		
       
       
		setResizable(false);
		setTitle("Edit Flight");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 710, 154);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				DefaultTableModel DT = (DefaultTableModel)table.getModel();
				
				int selectedIndex = table.getSelectedRow();
				
				
				labelFlightid.setText(DT.getValueAt(selectedIndex, 0).toString());
				textFlightno.setText(DT.getValueAt(selectedIndex, 1).toString());
				//Fromtxt.setName(DT.getValueAt(selectedIndex, 2).toString());
				//Totxt.setName(DT.getValueAt(selectedIndex, 3).toString());
				textDep_time.setText(DT.getValueAt(selectedIndex, 4).toString());
				textArr_time.setText(DT.getValueAt(selectedIndex, 5).toString());
				
				
				
				textPrice.setText(DT.getValueAt(selectedIndex, 7).toString());
				textSeats.setText(DT.getValueAt(selectedIndex, 8).toString());
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Flight ID", "Flight No", "From", "To", "Dep_Time", "Arr_Time", "Dep_Date", "Price", "Seats"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Delete Selected Flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		       
		        PreparedStatement pst11 = null;
		        PreparedStatement pst5 = null;
		        
		        
		       
		        try {
		        	
						
						String query9 = "DELETE from flight where flight_id =?";
						pst11= connectDB.prepareStatement(query9);
						pst11.setInt(1,  Integer.parseInt(labelFlightid.getText()));
						
						pst11.executeUpdate();
							
						
							
							
						JOptionPane.showMessageDialog(null, "Successfully Deleted the selected flight");
						
						String query5="select * from flight";
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
								
								v2.add(rs5.getString("flight_id"));
								v2.add(rs5.getString("flightnumber"));
								v2.add(rs5.getString("origin"));
								v2.add(rs5.getString("destination"));
								v2.add(rs5.getString("departuretime"));
								v2.add(rs5.getString("arrivaltime"));
								v2.add(rs5.getString("departuredate"));
								v2.add(rs5.getString("price"));
								v2.add(rs5.getString("seats"));
								
								
							}
							
							DT.addRow(v2);
						
						}
						
					
					pst5.close();
					pst11.close();
					
					
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(499, 336, 197, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Flight No");
		lblNewLabel.setBounds(36, 231, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(36, 256, 69, 14);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(36, 281, 69, 14);
		contentPane.add(lblTo);
		
		JLabel lblDeptime = new JLabel("Dep_Time");
		lblDeptime.setBounds(36, 306, 69, 14);
		contentPane.add(lblDeptime);
		
		JLabel lblArrtime = new JLabel("Arr_Time");
		lblArrtime.setBounds(36, 331, 69, 14);
		contentPane.add(lblArrtime);
		
		JLabel lblDepdate = new JLabel("Dep_Date");
		lblDepdate.setBounds(36, 356, 69, 14);
		contentPane.add(lblDepdate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(36, 381, 69, 14);
		contentPane.add(lblPrice);
		
		JLabel lblSeats = new JLabel("Seats");
		lblSeats.setBounds(36, 406, 69, 14);
		contentPane.add(lblSeats);
		
		textFlightno = new JTextField();
		textFlightno.setBounds(115, 228, 113, 20);
		contentPane.add(textFlightno);
		textFlightno.setColumns(10);
		
		textDep_time = new JTextField();
		textDep_time.setColumns(10);
		textDep_time.setBounds(115, 303, 113, 20);
		contentPane.add(textDep_time);
		
		textArr_time = new JTextField();
		textArr_time.setColumns(10);
		textArr_time.setBounds(115, 328, 113, 20);
		contentPane.add(textArr_time);
		
		textPrice = new JTextField();
		textPrice.setColumns(10);
		textPrice.setBounds(115, 378, 113, 20);
		contentPane.add(textPrice);
		
		textSeats = new JTextField();
		textSeats.setColumns(10);
		textSeats.setBounds(115, 403, 113, 20);
		contentPane.add(textSeats);
		
		JButton btnUpdateSelectedFlight = new JButton("Update Selected Flight");
		btnUpdateSelectedFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement pst = null;
				PreparedStatement pstupdate = null;
				String depDate = ((JTextField)textdateChooser.getDateEditor().getUiComponent()).getText();
				 try {
			        	
						
					 	String query="update flight set flightnumber=?, origin=?,destination=?,departuretime=?,arrivaltime=?,departuredate=?,price=?,seats=?, availableseats =? where flight_id =?";
						pst= connectDB.prepareStatement(query);
						
						pst.setString(1,textFlightno.getText());
						pst.setString(2,Fromtxt.getSelectedItem().toString());
						pst.setString(3,Totxt.getSelectedItem().toString());
						pst.setString(4,textDep_time.getText());
						pst.setString(5,textArr_time.getText());
						pst.setString(6,depDate);
						pst.setString(7,textPrice.getText());
						pst.setString(8,textSeats.getText());
						pst.setString(9,textSeats.getText());
						pst.setInt(10,Integer.parseInt(labelFlightid.getText()));
						
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Successfully updated");
						//dispose();	
						
							
							
						
						
						String query5="select * from flight";
						pstupdate= connectDB.prepareStatement(query5);
						
						ResultSet rs5 = pstupdate.executeQuery();
						
						ResultSetMetaData rs5m = rs5.getMetaData();
						
						int c;
						c = rs5m.getColumnCount();
						
						
						DefaultTableModel DT = (DefaultTableModel)table.getModel();
						DT.setRowCount(0);
						
						while(rs5.next()) {
						
							@SuppressWarnings("rawtypes")
							Vector v2 = new Vector();
							
							for (int i = 1; i<=c; i++) {
								
								v2.add(rs5.getString("flight_id"));
								v2.add(rs5.getString("flightnumber"));
								v2.add(rs5.getString("origin"));
								v2.add(rs5.getString("destination"));
								v2.add(rs5.getString("departuretime"));
								v2.add(rs5.getString("arrivaltime"));
								v2.add(rs5.getString("departuredate"));
								v2.add(rs5.getString("price"));
								v2.add(rs5.getString("seats"));
								
								
							}
							
							DT.addRow(v2);
						
						}
						
					
					pstupdate.close();
					pst.close();
					
					
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
				
				
				
			}
		});
		btnUpdateSelectedFlight.setBounds(499, 370, 197, 23);
		contentPane.add(btnUpdateSelectedFlight);
		
		lblFlightId = new JLabel("Flight ID");
		lblFlightId.setBounds(36, 206, 69, 14);
		contentPane.add(lblFlightId);
		
		labelFlightid = new JLabel("");
		labelFlightid.setForeground(new Color(255, 0, 0));
		labelFlightid.setBounds(115, 206, 86, 14);
		contentPane.add(labelFlightid);
		
		Fromtxt = new JComboBox();
		Fromtxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		Fromtxt.setBounds(115, 252, 113, 22);
		contentPane.add(Fromtxt);
		
		Totxt = new JComboBox();
		Totxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		Totxt.setBounds(115, 277, 113, 22);
		contentPane.add(Totxt);
		
		textdateChooser = new JDateChooser();
		textdateChooser.setDateFormatString("MM-dd-yyyy");
		textdateChooser.setBounds(115, 356, 113, 20);
		contentPane.add(textdateChooser);
	
		
        
		
		try {
			

		String query5="select * from flight";
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
				
				v2.add(rs5.getString("flight_id"));
				v2.add(rs5.getString("flightnumber"));
				v2.add(rs5.getString("origin"));
				v2.add(rs5.getString("destination"));
				v2.add(rs5.getString("departuretime"));
				v2.add(rs5.getString("arrivaltime"));
				v2.add(rs5.getString("departuredate"));
				v2.add(rs5.getString("price"));
				v2.add(rs5.getString("seats"));
				
				
				
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
