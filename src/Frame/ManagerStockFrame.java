package Frame;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ManagerStockFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerStockFrame window = new ManagerStockFrame();
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
	public ManagerStockFrame(Manager manager) {
		initialize(manager);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Manager manager) {
		frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Stock Report");
		lblNewLabel.setBounds(303, 28, 192, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JTextArea txtrInformationAboutStock = new JTextArea();
		txtrInformationAboutStock.setText(manager.seeAllStockTransaction);
		txtrInformationAboutStock.setBounds(211, 102, 346, 291);
		frame.getContentPane().add(txtrInformationAboutStock);
		
		JButton button = new JButton("Go Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(300, 421, 171, 41);
		frame.getContentPane().add(button);
	}
}
