package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import model.InvestorList;
import model.ManagerList;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.SystemColor;

public class LogInFrame {

	private JFrame frame;
	private JPasswordField passwordField;
	public static Date currentDate = new Date(2018,12,12);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame window = new LogInFrame();
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
	public LogInFrame() {
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(317, 282, 233, 34);
		panel.add(passwordField);
		
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
		
		JButton btnNextDay = new JButton("Next Day");
		btnNextDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNextDay.setBounds(514, 383, 128, 38);
		panel.add(btnNextDay);
		
		JLabel lblWarningSignTriggers = new JLabel("Warning Sign triggers");
		lblWarningSignTriggers.setForeground(Color.RED);
		lblWarningSignTriggers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWarningSignTriggers.setBounds(338, 197, 167, 33);
		panel.add(lblWarningSignTriggers);
		
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(319, 378, 36, 44);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(367, 378, 36, 44);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(416, 378, 82, 44);
		panel.add(textPane_3);
		
		
		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textPane.getText();
				String password = passwordField.getText();
				
				if(dateInput()) {
					if(ManagerList.checkManagerIdentity(username, password) != null) {
						//OPEN NEW JFRAME FOR MANAGER
						ManagerFrame manager = new ManagerFrame(ManagerList.checkManagerIdentity(username, password));
						frame.dispose();
					}
					else if(InvestorList.checkInvestorIdentity(username, password) != null) {
						//OPEN NEW JFRAME FOR INVESTOR
						CustomerFrame customer = new CustomerFrame(InvestorList.checkInvestorIdentity(username, password));
						frame.dispose();
					}
					else
						lblWarningSignTriggers.setText("This Account does not Exist");
				}
				
				
			
			}

			private boolean dateInput() {
				//Makes sure the month is between 0 and 13
				Date test = new Date(Integer.parseInt(textPane_3.getText()), Integer.parseInt(textPane_2.getText())-1, Integer.parseInt(textPane_2.getText()));
				if(test == null) {
					return false;
				}
				else if(currentDate.compareTo(test) > 0) {
					lblWarningSignTriggers.setText("Enter a Date after the date shown in  the parameter");
					return false;
				}
				return true;
			}
			
		});
		

		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
	}
}
