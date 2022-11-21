package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Addflight extends JFrame {

	private JPanel contentPane;
	private JTextField Flight_No;
	private JTextField Departure_time;
	private JTextField Price;
	private JTextField Seat;
	private JTextField Arrival_time;
	private JComboBox Fromtxt;
	private JComboBox Totxt;
	private JDateChooser Departure_date;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addflight frame = new Addflight();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Addflight() {
		setResizable(false);
		setTitle("Add Flight");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 354, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 338, 444);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String FlightNo = Flight_No.getText();
				String From = Fromtxt.getSelectedItem().toString();
				String To = Totxt.getSelectedItem().toString();
				String Dep_time = Departure_time.getText();
				String Arr_time = Arrival_time.getText();
				
				
				String Dep_date = ((JTextField)Departure_date.getDateEditor().getUiComponent()).getText();
				String Cost = Price.getText();
				String seats = Seat.getText();
				
				DatabaseConnection connectNow1 = new DatabaseConnection();
		        Connection connectDB1 = connectNow1.getConnection();
		        PreparedStatement pst5 = null;
		        
		       
		        try {
					
						
						
						String query6 ="insert into flight(flightnumber,origin,destination,departuretime,arrivaltime,departuredate,price,seats,availableseats) values(?,?,?,?,?,?,?,?,?)";
								
						
						pst5= connectDB1.prepareStatement(query6);
						pst5.setString(1,  FlightNo);
						pst5.setString(2, From);
						pst5.setString(3,  To);
						pst5.setString(4, Dep_time);
						pst5.setString(5, Arr_time);
						pst5.setString(6,  Dep_date);
						pst5.setString(7,  Cost);
						pst5.setString(8,  seats);
						pst5.setString(9,  seats);
						pst5.executeUpdate();
						
						
						JOptionPane.showMessageDialog(null, "Successfully Added");
						dispose();
					
					
					pst5.close();
					connectDB1.close();
				}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "error");
					}
				
				
			}
		});
		btnNewButton.setBounds(44, 397, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(202, 397, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Flight Number");
		lblNewLabel.setBounds(44, 45, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(44, 83, 89, 14);
		panel.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(44, 127, 89, 14);
		panel.add(lblTo);
		
		JLabel lblDepartureTime = new JLabel("Departure Time");
		lblDepartureTime.setBounds(44, 170, 89, 14);
		panel.add(lblDepartureTime);
		
		JLabel lblDepartureDate = new JLabel("Arrival Time");
		lblDepartureDate.setBounds(44, 212, 89, 14);
		panel.add(lblDepartureDate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(44, 306, 89, 14);
		panel.add(lblPrice);
		
		Flight_No = new JTextField();
		Flight_No.setBounds(166, 42, 125, 20);
		panel.add(Flight_No);
		Flight_No.setColumns(10);
		
		Departure_time = new JTextField();
		Departure_time.setColumns(10);
		Departure_time.setBounds(166, 167, 125, 20);
		panel.add(Departure_time);
		
		Price = new JTextField();
		Price.setColumns(10);
		Price.setBounds(166, 303, 125, 20);
		panel.add(Price);
		
		Departure_date = new JDateChooser();
		Departure_date.setDateFormatString("MM-dd-yyyy");
		Departure_date.setBounds(166, 250, 125, 20);
		panel.add(Departure_date);
		
		JLabel lblNewLabel_1 = new JLabel("Seat");
		lblNewLabel_1.setBounds(44, 355, 46, 14);
		panel.add(lblNewLabel_1);
		
		Seat = new JTextField();
		Seat.setBounds(166, 352, 125, 20);
		panel.add(Seat);
		Seat.setColumns(10);
		
		Fromtxt = new JComboBox();
		Fromtxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		Fromtxt.setBounds(166, 79, 125, 22);
		panel.add(Fromtxt);
		
		Totxt = new JComboBox();
		Totxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diago"}));
		Totxt.setBounds(166, 123, 125, 22);
		panel.add(Totxt);
		
		JLabel lblDepartureDate_1 = new JLabel("Departure Date");
		lblDepartureDate_1.setBounds(44, 256, 89, 14);
		panel.add(lblDepartureDate_1);
		
		Arrival_time = new JTextField();
		Arrival_time.setColumns(10);
		Arrival_time.setBounds(166, 209, 125, 20);
		panel.add(Arrival_time);
	}
}
