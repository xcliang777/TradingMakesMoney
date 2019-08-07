package Frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class TestFrame {

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
					TestFrame window = new TestFrame();
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
	public TestFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblWelcomeToBankmanager = new JLabel("Welcome!");
		lblWelcomeToBankmanager.setForeground(Color.WHITE);
		lblWelcomeToBankmanager.setBounds(338, 136, 134, 44);
		panel.add(lblWelcomeToBankmanager);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setBounds(317, 241, 233, 31);
		panel.add(textPane);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(163, 241, 147, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(163, 283, 128, 33);
		panel.add(lblNewLabel_1);
		

		
		JLabel lblDontHaveAn = new JLabel("Don't have an account? Register here");
		lblDontHaveAn.setForeground(Color.CYAN);
		lblDontHaveAn.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblDontHaveAn.setBounds(163, 331, 352, 19);
		panel.add(lblDontHaveAn);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(348, 450, 102, 31);
		panel.add(btnEnter);
		
		
		JLabel lblTodayIs = new JLabel("Today is 12/12/2012");
		lblTodayIs.setForeground(Color.WHITE);
		lblTodayIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTodayIs.setBounds(338, 348, 128, 33);
		panel.add(lblTodayIs);
		
		JLabel lblEnterCurrentDate = new JLabel("Enter Current Date");
		lblEnterCurrentDate.setForeground(Color.WHITE);
		lblEnterCurrentDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterCurrentDate.setBounds(163, 378, 128, 33);
		panel.add(lblEnterCurrentDate);
		
		
		JLabel lblWarningSignTriggers = new JLabel("Warning Sign triggers");
		lblWarningSignTriggers.setForeground(Color.RED);
		lblWarningSignTriggers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWarningSignTriggers.setBounds(338, 197, 167, 33);
		panel.add(lblWarningSignTriggers);
		
		SimpleDateFormat model = new SimpleDateFormat("dd.MM.yy");
		JSpinner spinner  = new JSpinner(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, model.toPattern()));
		spinner.setBounds(328, 378, 144, 44);
		panel.add(spinner);

		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(spinner.getComponents().toString());
			}
		});
		
		
	}
}
