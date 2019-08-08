package Frame;
import model.Investor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerSavingsFrame {

	Investor investor;
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
					//CustomerSavingsFrame window = new CustomerSavingsFrame();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerSavingsFrame(Investor investor) {
		this.investor = investor;
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
		
		JLabel lblWelcomeUserCurrent = new JLabel("");

		System.out.println(investor.getId());

		lblWelcomeUserCurrent.setText("Welcome " + investor.getId() + ". Your balance is " + investor.getSavingAccount().getBalance());



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

		btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(textField.getText());
				investor.getSavingAccount().deposit(amount);
				lblWelcomeUserCurrent.setText("Welcome " + investor.getId() + ". Your balance is " + investor.getSavingAccount().getBalance());
				textField.setText("");
			}
		});
		
		JButton button = new JButton("Withdraw");
		button.setBounds(438, 303, 171, 41);
		frame.getContentPane().add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(textField_1.getText());
				if (investor.getSavingAccount().withdraw(amount)){
					lblWelcomeUserCurrent.setText("Welcome " + investor.getId() + ". Your balance is " + investor.getSavingAccount().getBalance());
					textField_1.setText("");
				}
			}
		});
		
		JButton btnLogOff = new JButton("Back");
		btnLogOff.setBounds(571, 24, 171, 41);

		btnLogOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CustomerFrame(investor);
			}
		});

		frame.setVisible(true);
		frame.getContentPane().add(btnLogOff);
	}
}