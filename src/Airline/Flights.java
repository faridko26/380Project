package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.PasswordAuthentication;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;

public class Flights extends JFrame {

	
	private JPanel contentPane;
	private JTable table;


	
	//private JLabel LabelLastname;
	//private JLabel LabelEmail;



	/**
	 * Create the frame.
	 */
	public Flights(Airline.Session flightSession) {
		setTitle("Book Flight");
		
		
        
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 994, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Search Flight", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 457, 148);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Book Flight", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(641, 11, 308, 444);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox comboBoxFrom = new JComboBox();
		comboBoxFrom.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		comboBoxFrom.setBounds(10, 49, 120, 22);
		panel.add(comboBoxFrom);
		
		JComboBox comboBoxTo = new JComboBox();
		comboBoxTo.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		comboBoxTo.setBounds(184, 49, 120, 22);
		panel.add(comboBoxTo);
		
		JLabel flightno = new JLabel("");
		flightno.setBounds(137, 218, 73, 14);
		panel_1.add(flightno);
		
		JLabel labelprice = new JLabel("");
		labelprice.setBounds(154, 258, 73, 14);
		panel_1.add(labelprice);
		
		JLabel labelTotalprice = new JLabel("");
		labelTotalprice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTotalprice.setBounds(165, 343, 73, 14);
		panel_1.add(labelTotalprice);
		
		JLabel lblNewLabel = new JLabel("From:");
		lblNewLabel.setBounds(10, 27, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(185, 27, 46, 14);
		panel.add(lblTo);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				
				DatabaseConnection connect6 = new DatabaseConnection();
		        Connection connectDB = connect6.getConnection();
				String From = comboBoxFrom.getSelectedItem().toString();
				String To = comboBoxTo.getSelectedItem().toString();
				
				
		        PreparedStatement pst6;
				
				try {
					
				DefaultTableModel DT = (DefaultTableModel)table.getModel();
				DT.setRowCount(0);
				
		
				
				String query6="select * from flight where origin =? and destination =?";
				pst6= connectDB.prepareStatement(query6);
				
				pst6.setString(1, From);
				pst6.setString(2, To);
			
				ResultSet rs6 = pst6.executeQuery();
				
				ResultSetMetaData rs6m = rs6.getMetaData();
				
				int c;
				c = rs6m.getColumnCount();
				
				
				
				
				
				while(rs6.next()) {
					
					@SuppressWarnings("rawtypes")
					Vector v2 = new Vector();
					
					for (int i = 1; i<=c; i++) {
						
						v2.add(rs6.getString("flightnumber"));
						v2.add(rs6.getString("origin"));
						v2.add(rs6.getString("destination"));
						v2.add(rs6.getString("departuretime"));
						v2.add(rs6.getString("arrivaltime"));
						v2.add(rs6.getString("departuredate"));
						v2.add(rs6.getString("price"));
						v2.add(rs6.getString("availableseats"));
						
						
					}
					
					DT.addRow(v2);
				}
				
				
				
				
				pst6.close();
				connectDB.close();
			}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "error");
				}
				
				
			}
		});
		btnNewButton.setBounds(346, 114, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setBounds(10, 195, 621, 260);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				DefaultTableModel DT = (DefaultTableModel)table.getModel();
				
				int selectedIndex = table.getSelectedRow();
				
				flightno.setText(DT.getValueAt(selectedIndex, 0).toString());
				labelprice.setText(DT.getValueAt(selectedIndex, 6).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Flight No", "From", "To", "Dep_Time", "Arr_Time", "Dep_Date", "Price", "Available_Seat"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(56);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(84);
		scrollPane_1.setViewportView(table);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("My Information:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(10, 29, 106, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Name :");
		lblNewLabel_2.setBounds(43, 69, 73, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Last Name :");
		lblNewLabel_2_1.setBounds(43, 105, 73, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Email :");
		lblNewLabel_2_1_1.setBounds(43, 142, 73, 14);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel LabelFirstname = new JLabel("New label");
		LabelFirstname.setBounds(135, 69, 117, 14);
		panel_1.add(LabelFirstname);
		LabelFirstname.setText(flightSession.getName());
		
		JLabel LabelLastname = new JLabel("New label");
		LabelLastname.setBounds(135, 105, 117, 14);
		panel_1.add(LabelLastname);
		LabelLastname.setText(flightSession.getLastname());
		
		JLabel LabelEmail = new JLabel("New label");
		LabelEmail.setBounds(135, 142, 117, 14);
		panel_1.add(LabelEmail);
		LabelEmail.setText(flightSession.getEmail());
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Flight:");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 185, 106, 22);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Flight No");
		lblNewLabel_2_2.setBounds(43, 218, 73, 14);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Seats");
		lblNewLabel_2_2_1.setBounds(43, 293, 73, 14);
		panel_1.add(lblNewLabel_2_2_1);
		
		JSpinner seats = new JSpinner();
		seats.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		seats.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				int price = Integer.parseInt(labelprice.getText());
				int seatsNo = Integer.parseInt(seats.getValue().toString());
				
				int Totalprice = price * seatsNo;
				
				labelTotalprice.setText(String.valueOf(Totalprice));
				
				
			}
		});
		seats.setBounds(137, 290, 65, 20);
		panel_1.add(seats);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int flightid = 0;
				String availableSeat = null;
				String Totalprice = labelTotalprice.getText();
				String SeatsNumber = seats.getValue().toString();
				LocalDate Date = java.time.LocalDate.now();
				String Booked_date = Date.toString();
				int cusid = Integer.parseInt(flightSession.getCus_id());
				
				DatabaseConnection connectNow1 = new DatabaseConnection();
		        Connection connectDB1 = connectNow1.getConnection();
		        PreparedStatement pst8 = null;
		        PreparedStatement pst9 = null;
		        PreparedStatement pst10 = null;
		        
		       
		        try {
					
						
						String query8 = "select * from flight where flightnumber =?";
						pst8= connectDB1.prepareStatement(query8);
						pst8.setString(1,  flightno.getText());
						ResultSet rs1 = pst8.executeQuery();
						if(rs1.next()) {
							 flightid = rs1.getInt("flight_id");
							 availableSeat = rs1.getString("availableseats");
						}
						//System.out.print(Integer.parseInt(SeatsNumber));
						//System.out.print(Integer.parseInt(availableSeat));
						if(Integer.parseInt(SeatsNumber) <= Integer.parseInt(availableSeat)) {
							
							String query9 = "insert into booking (cusid,flightid,totalprice,seats_no,date) values(? ,? ,? ,? ,?)";
							
						
							pst9= connectDB1.prepareStatement(query9);
							
							pst9.setInt(1,cusid);
							pst9.setInt(2, flightid);
							pst9.setString(3,  Totalprice);
							pst9.setString(4, SeatsNumber);
							pst9.setString(5, Booked_date);
							System.out.print(cusid);
							System.out.print(flightid);
							System.out.printf(Totalprice);
							System.out.printf(SeatsNumber);
							System.out.printf(Booked_date);
							pst9.executeUpdate();
							
							String query10 = "update flight set availableseats=? where flight_id =?";	
							String UpdatedSeat;
							int updateseat = Integer.parseInt(availableSeat) - Integer.parseInt(SeatsNumber);
							System.out.print(updateseat);
							UpdatedSeat = Integer.toString(updateseat);
							pst10=connectDB1.prepareStatement(query10);
							pst10.setString(1, UpdatedSeat);
							pst10.setInt(2, flightid);
							pst10.executeUpdate();
							
							
							// sending confirmation Email
							
							SendEmail email = new SendEmail();
							String msg="your ticket is booked";
							email.send(flightSession.getEmail(),msg);
							
							
						JOptionPane.showMessageDialog(null, "Successfully Booked and Confirmation Email was sent");
						
						}
						else {
							JOptionPane.showMessageDialog(null, "Sold out");
							
						}
					
					pst8.close();
					pst9.close();
					pst10.close();
					connectDB1.close();
				}
					catch(Exception e1) {
						//JOptionPane.showMessageDialog(null, "error");
					}
				
				
				
				
				
				
				
			}
		});
		btnBook.setBounds(43, 389, 89, 23);
		panel_1.add(btnBook);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(184, 389, 89, 23);
		panel_1.add(btnCancel);
		
		JLabel lblNewLabel_3 = new JLabel("Total Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(35, 343, 73, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Price");
		lblNewLabel_3_1.setBounds(43, 258, 65, 14);
		panel_1.add(lblNewLabel_3_1);
		
		JLabel labelTotalprice_1 = new JLabel("$");
		labelTotalprice_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelTotalprice_1.setBounds(137, 343, 25, 14);
		panel_1.add(labelTotalprice_1);
		
		JLabel labelprice_1 = new JLabel("$");
		labelprice_1.setBounds(137, 258, 20, 14);
		panel_1.add(labelprice_1);
		
		JLabel lblNewLabel_4 = new JLabel("Please select flight");
		lblNewLabel_4.setForeground(new Color(255, 128, 0));
		lblNewLabel_4.setBackground(new Color(255, 128, 0));
		lblNewLabel_4.setBounds(10, 170, 199, 14);
		contentPane.add(lblNewLabel_4);
		
		
		
		
		
		
		//
		
	}
}
