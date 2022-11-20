package Airline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Addflight extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_7;
	private JTextField textField_5;

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
		
		setTitle("Add Flight");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 354, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 338, 358);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(44, 309, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(202, 309, 89, 23);
		panel.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Flight Number");
		lblNewLabel.setBounds(44, 63, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(44, 88, 89, 14);
		panel.add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(44, 113, 89, 14);
		panel.add(lblTo);
		
		JLabel lblDepartureTime = new JLabel("Departure Time");
		lblDepartureTime.setBounds(44, 138, 89, 14);
		panel.add(lblDepartureTime);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time");
		lblArrivalTime.setBounds(44, 163, 89, 14);
		panel.add(lblArrivalTime);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setBounds(44, 188, 89, 14);
		panel.add(lblDepartureDate);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setBounds(44, 213, 89, 14);
		panel.add(lblArrivalDate);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(44, 238, 89, 14);
		panel.add(lblPrice);
		
		textField = new JTextField();
		textField.setBounds(166, 60, 125, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(166, 85, 125, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(166, 110, 125, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(166, 135, 125, 20);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(166, 160, 125, 20);
		panel.add(textField_4);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(166, 235, 125, 20);
		panel.add(textField_7);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(166, 182, 125, 20);
		panel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(166, 207, 125, 20);
		panel.add(dateChooser_1);
		
		JLabel lblNewLabel_1 = new JLabel("Seat");
		lblNewLabel_1.setBounds(44, 263, 46, 14);
		panel.add(lblNewLabel_1);
		
		textField_5 = new JTextField();
		textField_5.setBounds(166, 260, 125, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
	}
}
