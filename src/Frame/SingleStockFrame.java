package Frame;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class SingleStockFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingleStockFrame window = new SingleStockFrame();
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
	public SingleStockFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException, IOException {
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
	    BufferedImage img = ImageIO.read(new URL(
	            "https://bcassetcdn.com/asset/logo/f7b0a25c-6196-4f30-92ab-6a18a70f46eb/logo?v=4&text=Logo+Text+Here"));
	        ImageIcon icon = new ImageIcon(img);
	
		JLabel lblStockInformation = new JLabel("Stock Information");
		lblStockInformation.setBounds(292, 54, 218, 33);
		frame.getContentPane().add(lblStockInformation);
		
		JButton button = new JButton("Log Off");
		button.setBounds(571, 37, 171, 41);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Go Back");
		button_1.setBounds(571, 91, 171, 41);
		frame.getContentPane().add(button_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(53, 175, 364, 335);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel.setIcon(icon);
		
		JLabel label = new JLabel("Ticker");
		label.setBounds(443, 210, 115, 33);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Company");
		label_1.setBounds(443, 271, 115, 33);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Price");
		label_2.setBounds(443, 332, 115, 33);
		frame.getContentPane().add(label_2);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(431, 428, 171, 41);
		frame.getContentPane().add(btnBuy);
		
		JLabel lblTodayIs = new JLabel("Today is: ");
		lblTodayIs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTodayIs.setBounds(53, 157, 115, 33);
		frame.getContentPane().add(lblTodayIs);
		
		JLabel label_3 = new JLabel("Your Balance in Securities is:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(196, 157, 210, 33);
		frame.getContentPane().add(label_3);
		
		
	}
}
