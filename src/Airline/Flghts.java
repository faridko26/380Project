package Airline;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class Flghts extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flghts frame = new Flghts();
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
	public Flghts() {
		setBounds(100, 100, 703, 404);

	}

}
