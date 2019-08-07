package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ManagerHomeFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerHomeFrame window = new ManagerHomeFrame();
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
	public ManagerHomeFrame() {
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
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 28, 742, 42);
		frame.getContentPane().add(panel);
		
		JLabel lblWelcomeManager = new JLabel("Welcome Manager");
		lblWelcomeManager.setFont(new Font("Arial", Font.PLAIN, 27));
		panel.add(lblWelcomeManager);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(95, 237, 165, 135);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnInvestorList = new JButton("Investor List");
		btnInvestorList.setBounds(0, 72, 165, 63);
		btnInvestorList.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(btnInvestorList);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\InvestorGUI\\rsz_list_icon.png"));
		button.setBounds(46, 0, 74, 73);
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(317, 237, 165, 135);
		frame.getContentPane().add(panel_2);
		
		JButton button_1 = new JButton("Report");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(0, 72, 165, 63);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\InvestorGUI\\rsz_report.png"));
		button_2.setBounds(46, 0, 74, 73);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(539, 237, 165, 135);
		frame.getContentPane().add(panel_3);
		
		JButton button_3 = new JButton("Trade Stock");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(0, 72, 165, 63);
		panel_3.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\InvestorGUI\\rsz_trade_icon.png"));
		button_4.setBounds(46, 0, 74, 73);
		panel_3.add(button_4);
	}
}