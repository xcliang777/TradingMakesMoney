package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerSavingsFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSavingsFrame window = new CustomerSavingsFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerSavingsFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCheckings = new JLabel("Savings");
		lblCheckings.setBounds(323, 28, 139, 33);
		frame.getContentPane().add(lblCheckings);
		
		JLabel lblWelcomeUserCurrent = new JLabel("Welcome User, current balance is");
		lblWelcomeUserCurrent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWelcomeUserCurrent.setBounds(37, 85, 259, 33);
		frame.getContentPane().add(lblWelcomeUserCurrent);
		
		textField = new JTextField();
		textField.setBounds(142, 200, 236, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 304, 236, 39);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(438, 199, 171, 41);
		frame.getContentPane().add(btnDeposit);
		
		JButton button = new JButton("Withdraw");
		button.setBounds(438, 303, 171, 41);
		frame.getContentPane().add(button);
		
		JButton btnLogOff = new JButton("Log Off");
		btnLogOff.setBounds(571, 24, 171, 41);
		frame.getContentPane().add(btnLogOff);
	}
}