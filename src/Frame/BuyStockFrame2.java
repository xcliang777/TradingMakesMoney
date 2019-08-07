package Frame;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class BuyStockFrame2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyStockFrame2 window = new BuyStockFrame2();
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
	public BuyStockFrame2() {
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
		frame.getContentPane().setLayout(null);
		
		
		JButton button = new JButton("Log Off");
		button.setBounds(571, 24, 171, 41);
		frame.getContentPane().add(button);
		
		
		JButton button_1 = new JButton("Go Back");
		button_1.setBounds(571, 86, 171, 41);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(61, 241, 149, 157);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAppl = new JButton("APPL");
		panel.add(btnAppl, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\rsz_appl.png"));
		panel.add(btnNewButton, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(237, 241, 149, 157);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnFb = new JButton("FB");
		panel_1.add(btnFb, BorderLayout.SOUTH);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\rsz_fb.png"));
		panel_1.add(button_2, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_2.setBounds(413, 241, 149, 157);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton btnGoogl = new JButton("GOOGL");
		panel_2.add(btnGoogl, BorderLayout.SOUTH);
		
		JButton button_3 = new JButton("");
		button_3.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\rsz_google200.jpg"));
		panel_2.add(button_3, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_3.setBounds(592, 242, 149, 157);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnBu = new JButton("BU");
		panel_3.add(btnBu, BorderLayout.SOUTH);
		
		JButton button_4 = new JButton("");
		button_4.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\rsz_bu.png"));
		panel_3.add(button_4, BorderLayout.CENTER);
		
		JLabel lblTodayIs = new JLabel("Today is: ");
		lblTodayIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTodayIs.setBounds(61, 166, 287, 33);
		frame.getContentPane().add(lblTodayIs);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Stock Market");
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWelcomeToThe.setBounds(45, 28, 303, 110);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JLabel label = new JLabel("Your Balance is:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(61, 202, 287, 33);
		frame.getContentPane().add(label);
	}
}
