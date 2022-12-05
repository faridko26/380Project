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
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;

public class Addflight extends JFrame {

	private JPanel contentPane;
	private JTextField Flight_No;
	private JComboBox Fromtxt;
	private JComboBox Totxt;
	private JDateChooser Departure_date;
	private JSpinner priceSpinner;
	private JSpinner seatSpinner;
	private JSpinner departSpinner;
	private JSpinner arriveSpinner;

	/**
	 * Create the frame.
	 */
	public Addflight() {
		setIconImage(new ImageIcon(getClass().getResource("plane_icon.png")).getImage());
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
				
				Object depValue = departSpinner.getValue();
				Date depDate = (Date)depValue;
				SimpleDateFormat format1 = new SimpleDateFormat("hh:mm aa");
				String Dep_time = format1.format(depDate);
				
				
				Object arrValue = arriveSpinner.getValue();
				Date arrDate = (Date)arrValue;
				SimpleDateFormat format2 = new SimpleDateFormat("hh:mm aa");
				String Arr_time = format2.format(arrDate);
				
				
				String Dep_date = ((JTextField)Departure_date.getDateEditor().getUiComponent()).getText();
				String Cost = priceSpinner.getValue().toString();
				String seats = seatSpinner.getValue().toString();
				
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
						//dispose();
					
					
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
		lblNewLabel.setBounds(44, 40, 89, 26);
		panel.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(44, 80, 89, 26);
		panel.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(44, 120, 89, 26);
		panel.add(lblTo);
		
		JLabel lblDepartureTime = new JLabel("Departure Time");
		lblDepartureTime.setBounds(44, 160, 89, 26);
		panel.add(lblDepartureTime);
		
		JLabel lblDepartureDate = new JLabel("Arrival Time");
		lblDepartureDate.setBounds(44, 200, 89, 26);
		panel.add(lblDepartureDate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(44, 280, 89, 26);
		panel.add(lblPrice);
		
		Flight_No = new JTextField();
		Flight_No.setBounds(166, 40, 125, 28);
		panel.add(Flight_No);
		Flight_No.setColumns(10);
		
		Departure_date = new JDateChooser();
		Departure_date.setDateFormatString("MM-dd-yyyy");
		Departure_date.setBounds(166, 240, 125, 28);
		panel.add(Departure_date);
		
		JLabel lblNewLabel_1 = new JLabel("Seats");
		lblNewLabel_1.setBounds(44, 320, 46, 26);
		panel.add(lblNewLabel_1);
		
		Fromtxt = new JComboBox();
		Fromtxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diego"}));
		Fromtxt.setBounds(166, 80, 125, 28);
		panel.add(Fromtxt);
		
		Totxt = new JComboBox();
		Totxt.setModel(new DefaultComboBoxModel(new String[] {"Los Angeles", "Las Vegas", "Denver", "Seattle", "San Francisco", "San Diego"}));
		Totxt.setBounds(166, 120, 125, 28);
		panel.add(Totxt);
		
		JLabel lblDepartureDate_1 = new JLabel("Departure Date");
		lblDepartureDate_1.setBounds(44, 240, 89, 26);
		panel.add(lblDepartureDate_1);
		
		JLabel lblNewLabel_2 = new JLabel("$");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(125, 280, 38, 26);
		panel.add(lblNewLabel_2);
		
		priceSpinner = new JSpinner();
		lblNewLabel_2.setLabelFor(priceSpinner);
		priceSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(10)));
		priceSpinner.setBounds(166, 280, 125, 28);
		panel.add(priceSpinner);
		
		seatSpinner = new JSpinner();
		seatSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		seatSpinner.setBounds(166, 320, 125, 28);
		panel.add(seatSpinner);
		
		
		Date date1 = new Date();
		
		SpinnerDateModel sm = new SpinnerDateModel(date1, null, null, Calendar.MINUTE);
		departSpinner = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(departSpinner,"hh:mm aa");
		departSpinner.setEditor(de);
		departSpinner.setBounds(166, 159, 125, 28);
		panel.add(departSpinner);
		
		SpinnerDateModel sm2 = new SpinnerDateModel(date1, null, null, Calendar.MINUTE);
		arriveSpinner = new JSpinner(sm2);
		JSpinner.DateEditor de2 = new JSpinner.DateEditor(arriveSpinner,"hh:mm aa");
		arriveSpinner.setEditor(de2);
		arriveSpinner.setBounds(166, 199, 125, 28);
		panel.add(arriveSpinner);
	}
}
