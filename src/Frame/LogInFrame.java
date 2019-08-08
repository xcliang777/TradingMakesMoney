package Frame;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class LogInFrame {

	private JFrame frame;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		new LogInFrame();
	}

	/**
	 * Create the application.
	 */
	public LogInFrame() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException {



		frame = new JFrame();

//		ImageIcon img = new ImageIcon("src/icon.jpg");
//		JLabel background = new JLabel("",img,JLabel.CENTER);
//		background.setBounds(0,0,800,600);
//		background.isBackgroundSet();
//		frame.add(background);

		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		//panel.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblWelcomeToBankmanager = new JLabel("Welcome to our Trading System!");
		//lblWelcomeToBankmanager.setForeground(Color.WHITE);
		lblWelcomeToBankmanager.setFont(new Font("dialog",1,20));
		lblWelcomeToBankmanager.setBounds(250, 50, 800, 100);
		panel.add(lblWelcomeToBankmanager);
		
		JTextField textPane = new JTextField();
		textPane.setForeground(Color.BLACK);
		textPane.setBounds(367, 191, 233, 31);
		panel.add(textPane);

		passwordField = new JPasswordField();
		passwordField.setBounds(367, 232, 233, 31);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		//lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(213, 191, 147, 33);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		//lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(213, 233, 128, 33);
		panel.add(lblNewLabel_1);



//		JLabel lblDontHaveAn = new JLabel("Don't have an account? Register here");
//		lblDontHaveAn.setForeground(Color.CYAN);
//		lblDontHaveAn.setFont(new Font("Times New Roman", Font.ITALIC, 12));
//		lblDontHaveAn.setBounds(163, 331, 352, 19);
//		panel.add(lblDontHaveAn);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(348, 450, 102, 31);
		panel.add(btnEnter);
		
		
//		JLabel lblTodayIs = new JLabel("");
//		lblTodayIs.setText("Today is " + String.valueOf(Market.today));
//		lblTodayIs.setForeground(Color.WHITE);
//		lblTodayIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		lblTodayIs.setBounds(338, 348, 128, 33);
//		panel.add(lblTodayIs);
		
		JLabel lblEnterCurrentDate = new JLabel("Enter Current Date");
		//lblEnterCurrentDate.setForeground(Color.WHITE);
		lblEnterCurrentDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterCurrentDate.setBounds(163, 378, 128, 33);
		panel.add(lblEnterCurrentDate);
		
//		JButton btnNextDay = new JButton("Next Day");
//		btnNextDay.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		btnNextDay.setBounds(514, 383, 128, 38);
//		panel.add(btnNextDay);
		
		JLabel lblWarningSignTriggers = new JLabel("");
		lblWarningSignTriggers.setForeground(Color.RED);
		lblWarningSignTriggers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWarningSignTriggers.setBounds(338, 197, 167, 33);
		panel.add(lblWarningSignTriggers);
		
		
		JTextField textPane_1 = new JTextField();
		textPane_1.setBounds(319, 378, 36, 25);
		panel.add(textPane_1);

		JTextField textPane_2 = new JTextField();
		textPane_2.setBounds(367, 378, 36, 25);
		panel.add(textPane_2);

		JTextField textPane_3 = new JTextField();
		textPane_3.setBounds(416, 378, 82, 25);
		panel.add(textPane_3);

		JButton btnRegisterHere = new JButton("Register Here");
		btnRegisterHere.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegisterHere.setBounds(173, 327, 171, 23);
		panel.add(btnRegisterHere);
		
		frame.setVisible(true);




		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textPane.getText();
				String password = String.valueOf(passwordField.getPassword());

				try {
					if(dateInput()) {

						new ManagerList();
						Investor investor = new Investor("qwe","123");
						InvestorList.investorList.add(investor);

						if(ManagerList.checkManagerIdentity(username, password) != null) {
							//OPEN NEW JFRAME FOR MANAGER
							ManagerList.checkManagerIdentity(username, password);
							try {
								new ManagerFrame();
							} catch (SQLException ex) {
								ex.printStackTrace();
							}
							frame.dispose();
						}
						else if(InvestorList.checkInvestorIdentity(username, password) != null) {
							//OPEN NEW JFRAME FOR INVESTOR

							new CustomerFrame(InvestorList.checkInvestorIdentity(username, password));

							StockMarket.updatePrices(Market.curDate);

							frame.dispose();
						}
						else
							lblWarningSignTriggers.setText("This Account does not Exist");
					}
				} catch (ParseException | SQLException ex) {
					ex.printStackTrace();
				}

			}

			private boolean dateInput() throws ParseException {
				//Makes sure the month is between 0 and 13
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				int year = Integer.parseInt(textPane_3.getText());
				int month = Integer.parseInt(textPane_1.getText());
				int day = Integer.parseInt(textPane_2.getText());
				String str = year+"-"+month+"-"+day;
				Market.curDate = dateFormat.parse(str);

				if(Market.curDate == null) {
					return false;
				}
				else if(Market.marketDate.compareTo(Market.curDate) > 0) {
					lblWarningSignTriggers.setText("Enter a Date after the date shown in  the parameter");
					return false;
				}
				return true;
			}
			
		});


		btnRegisterHere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new RegisterFrame();
			}
		});
		
	}
}
