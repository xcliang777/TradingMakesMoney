package Frame;

import model.Investor;
import model.Market;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author plutowang
 **/
public class StockInformationFrame extends JFrame implements ActionListener {

    Investor investor;

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();

    JLabel jLabel0 = new JLabel("Information of Your Stocks");
    JLabel jLabel1 = new JLabel("Ticker");
    JLabel jLabel2 = new JLabel("Amount");
    JLabel jLabel3 = new JLabel("Realized benefit");

    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();

    JButton jButton1 = new JButton("Cancel");
    JButton jButton2 = new JButton("Sell");


    JTextArea jTextArea = new JTextArea("The information of Stocks: ");


    StockInformationFrame(Investor investor) throws SQLException {

        this.investor = investor;
        this.setLayout(new GridLayout(1,2,0,0));


        jPanel1.setLayout(null);
        jLabel0.setBounds(50,10,300,100);
        jTextArea.setBounds(50,100,300,400);
        jLabel3.setBounds(50,510,300,50);
        jPanel1.add(jLabel0);
        jPanel1.add(jTextArea);
        jPanel1.add(jLabel3);

        jPanel3.setLayout(null);
        jLabel1.setBounds(150,66,50,20);
        jTextField1.setBounds(200,66,50,20);
        jPanel3.add(jLabel1);
        jPanel3.add(jTextField1);

        jPanel4.setLayout(null);
        jLabel2.setBounds(150,66,50,20);
        jTextField2.setBounds(200,66,50,20);
        jPanel4.add(jLabel2);
        jPanel4.add(jTextField2);

        jPanel5.setLayout(null);
        jButton1.setBounds(50,75,100,50);
        jButton2.setBounds(250,75,100,50);
        jPanel5.add(jButton1);
        jPanel5.add(jButton2);


        jPanel2.setLayout(new GridLayout(3,1,0,0));
        jPanel2.add(jPanel3);
        jPanel2.add(jPanel4);
        jPanel2.add(jPanel5);

        jButton1.addActionListener(this);
        jButton2.addActionListener(this);

        init();

        this.add(jPanel1);
        this.add(jPanel2);
        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void init() throws SQLException {
        String str = investor.getSecurityAccount().getAllStock();
        jTextArea.setText(str);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton1){
            this.dispose();
            new SecurityFrame(investor);
        }
        if (e.getSource()==jButton2){
            String ticker = jTextField1.getText();
            int numShare = Integer.parseInt(jTextField2.getText());
            try {
                double be = investor.getSecurityAccount().sellStock(ticker,numShare, Market.curDate);
                jLabel3.setText("Realized benefit " + be);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                init();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
}
