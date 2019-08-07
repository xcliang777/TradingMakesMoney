package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class InitializeCollateralFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitializeCollateralFrame window = new InitializeCollateralFrame();
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
	public InitializeCollateralFrame() {
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
		textField.setBounds(275, 273, 236, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("Go Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(571, 86, 171, 41);
		frame.getContentPane().add(button_1);
		
		JLabel lblEnterAmount = new JLabel("Enter Initial Collateral Amount:");
		lblEnterAmount.setBounds(213, 215, 370, 41);
		frame.getContentPane().add(lblEnterAmount);
	}
}
