package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoanFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanFrame window = new LoanFrame();
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
	public LoanFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("Log Off");
		button.setBounds(571, 24, 171, 41);
		frame.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setBounds(182, 227, 236, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("Go Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(571, 86, 171, 41);
		frame.getContentPane().add(button_1);
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setBounds(434, 226, 171, 41);
		frame.getContentPane().add(btnBorrow);
		
		JButton button_2 = new JButton("Pay Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(309, 296, 171, 41);
		frame.getContentPane().add(button_2);

		JLabel lblNewLabel = new JLabel("You currently owe:) + lblNewLabel");
		lblNewLabel.setBounds(182, 168, 331, 41);
		frame.getContentPane().add(lblNewLabel);
		
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double change = Double.parseDouble(textField.getText());
			}
		});
	}
}
