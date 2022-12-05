package Airline;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;

public class EditFlight extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JTextField textFlightno;
	private JLabel lblFlightId;
	private JLabel labelFlightid;
	private JComboBox Totxt;
	private JComboBox  Fromtxt;
	private JDateChooser departDateChooser;
	private JSpinner priceSpinner;
	private JSpinner seatSpinner;
	private Date dep_Time = new Date();
	private JSpinner departSpinner = new JSpinner(new SpinnerDateModel(dep_Time, null, null, Calendar.MINUTE));
	private Date arr_Time = new Date();
	private JSpinner arriveSpinner = new JSpinner(new SpinnerDateModel(arr_Time, null, null, Calendar.MINUTE));

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
		
		
		
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
		setResizable(false);
		setTitle("Edit Flight");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 746, 500);
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
				Fromtxt.setSelectedItem(DT.getValueAt(selectedIndex, 2).toString());
				Totxt.setSelectedItem(DT.getValueAt(selectedIndex, 3).toString());
				
				try {
					dep_Time = new SimpleDateFormat("hh:mm aa").parse(DT.getValueAt(selectedIndex, 4).toString());
					JSpinner.DateEditor de = new JSpinner.DateEditor(departSpinner,"hh:mm aa");
					departSpinner.setEditor(de);
					departSpinner.setValue(dep_Time);
					departSpinner.setBounds(115, 299, 123, 28);
					contentPane.add(departSpinner);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					arr_Time = new SimpleDateFormat("hh:mm aa").parse(DT.getValueAt(selectedIndex, 5).toString());
					JSpinner.DateEditor de = new JSpinner.DateEditor(arriveSpinner,"hh:mm aa");
					arriveSpinner.setEditor(de);
					arriveSpinner.setValue(arr_Time);
					arriveSpinner.setBounds(115, 330, 123, 28);
					contentPane.add(arriveSpinner);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
				try {
					departDateChooser.setDate(new SimpleDateFormat("MM-dd-yyyy").parse(DT.getValueAt(selectedIndex, 6).toString()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				priceSpinner.setValue(Integer.valueOf(DT.getValueAt(selectedIndex, 7).toString()));
				seatSpinner.setValue(Integer.valueOf(DT.getValueAt(selectedIndex, 8).toString()));
				
			
				
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
		lblNewLabel.setBounds(36, 210, 69, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(36, 240, 69, 26);
		contentPane.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(36, 270, 69, 26);
		contentPane.add(lblTo);
		
		JLabel lblDeptime = new JLabel("Dep_Time");
		lblDeptime.setBounds(36, 300, 69, 26);
		contentPane.add(lblDeptime);
		
		JLabel lblArrtime = new JLabel("Arr_Time");
		lblArrtime.setBounds(36, 330, 69, 26);
		contentPane.add(lblArrtime);
		
		JLabel lblDepdate = new JLabel("Dep_Date");
		lblDepdate.setBounds(36, 360, 69, 26);
		contentPane.add(lblDepdate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(36, 390, 69, 26);
		contentPane.add(lblPrice);
		
		JLabel lblSeats = new JLabel("Seats");
		lblSeats.setBounds(36, 420, 69, 26);
		contentPane.add(lblSeats);
		
		textFlightno = new JTextField();
		textFlightno.setBounds(115, 210, 123, 28);
		contentPane.add(textFlightno);
		textFlightno.setColumns(10);
		
		JButton btnUpdateSelectedFlight = new JButton("Update Selected Flight");
		btnUpdateSelectedFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement pst = null;
				PreparedStatement pstupdate = null;
				String depDate = ((JTextField)departDateChooser.getDateEditor().getUiComponent()).getText();
				 try {
			        	
						
					 	String query="update flight set flightnumber=?, origin=?,destination=?,departuretime=?,arrivaltime=?,departuredate=?,price=?,seats=?, availableseats =? where flight_id =?";
						pst= connectDB.prepareStatement(query);
						
						pst.setString(1,textFlightno.getText());
						pst.setString(2,Fromtxt.getSelectedItem().toString());
						pst.setString(3,Totxt.getSelectedItem().toString());
						pst.setString(4, new SimpleDateFormat("hh:mm aa").format(departSpinner.getValue()));
						pst.setString(5, new SimpleDateFormat("hh:mm aa").format(arriveSpinner.getValue()));
						pst.setString(6,depDate);
						pst.setString(7, String.valueOf((Integer)priceSpinner.getValue()));
						pst.setString(8, String.valueOf((Integer)seatSpinner.getValue()));
						pst.setString(9, String.valueOf((Integer)seatSpinner.getValue()));
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
		lblFlightId.setBounds(36, 180, 69, 26);
		contentPane.add(lblFlightId);
		
		labelFlightid = new JLabel("");
		labelFlightid.setForeground(new Color(255, 0, 0));
		labelFlightid.setBounds(115, 180, 86, 28);
		contentPane.add(labelFlightid);
		
		Fromtxt = new JComboBox();
		Fromtxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diego"}));
		Fromtxt.setBounds(115, 240, 123, 28);
		contentPane.add(Fromtxt);
		
		Totxt = new JComboBox();
		Totxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diego"}));
		Totxt.setBounds(115, 270, 123, 28);
		contentPane.add(Totxt);
		
		departDateChooser = new JDateChooser();
		departDateChooser.setDateFormatString("MM-dd-yyyy");
		departDateChooser.setBounds(115, 360, 123, 28);
		contentPane.add(departDateChooser);
		
		priceSpinner = new JSpinner();
		priceSpinner.setBounds(115, 390, 123, 28);
		contentPane.add(priceSpinner);
		
		seatSpinner = new JSpinner();
		seatSpinner.setBounds(115, 419, 123, 28);
		contentPane.add(seatSpinner);
		
		try {
			

		String query5="select * from flight order by flight_id asc";
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
