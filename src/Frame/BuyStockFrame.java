package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;

public class BuyStockFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyStockFrame window = new BuyStockFrame();
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
	public BuyStockFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(40, 149, 700, 300);
		frame.getContentPane().add(panel);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Stock Market");
		lblWelcomeToThe.setBounds(40, 0, 376, 115);
		frame.getContentPane().add(lblWelcomeToThe);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		panel.add(new JButton("a"));
		panel.add(new JButton("a"));
		panel.add(new JButton("a"));
		panel.add(new JButton("a"));
		panel.add(new JButton("a"));
		panel.add(new JButton("a"));
		
		JButton button = new JButton("Log Off");
		button.setBounds(571, 37, 171, 41);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Go Back");
		button_1.setBounds(571, 91, 171, 41);
		frame.getContentPane().add(button_1);

//		frame.getContentPane().add();
	}
	
		
	
}