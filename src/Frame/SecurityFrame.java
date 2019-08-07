package Frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author plutowang
 **/
public class SecurityFrame extends JFrame {

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();

    JLabel jLabel1 = new JLabel("Security Account");
    JLabel jLabel2 = new JLabel("You current have 500$");
    JLabel jLabel3 = new JLabel("Unrealized benefit");
    JLabel jLabel4 = new JLabel("Realized benefit");

    JTextField jTextField = new JTextField();

    JButton jButton1 = new JButton("Check Stock");
    JButton jButton2 = new JButton("Check Bonds");
    JButton jButton3 = new JButton("Buy Stock");
    JButton jButton4 = new JButton("Buy Bonds");;
    JButton jButton5 = new JButton("Transfer");
    JButton jButton6 = new JButton("Log out");




    SecurityFrame(){

        this.setLayout(null);

        jPanel1.setBounds(0,0,800,150);
        jPanel2.setBounds(0,150,800,150);
        jPanel3.setBounds(0,300,800,150);
        jPanel4.setBounds(0,450,800,150);

        jPanel1.setLayout(null);
        jLabel1.setBounds(50,0,400,150);
        jLabel1.setFont(new Font("Dialog",1,30));
        jButton6.setBounds(500,37,200,75);
        jPanel1.add(jLabel1);
        jPanel1.add(jButton6);

        jPanel2.setLayout(null);
        jLabel2.setBounds(50,0,300,150);
        jLabel2.setFont(new Font("Dialog",0,15));

        jTextField.setBounds(250,65,200,20);

        jButton5.setBounds(500,37,200,75);
        jPanel2.add(jLabel2);
        jPanel2.add(jTextField);
        jPanel2.add(jButton5);

        jPanel3.setLayout(new GridLayout(1,4,100,50));
        jPanel3.add(jButton1);
        jPanel3.add(jButton2);
        jPanel3.add(jButton3);
        jPanel3.add(jButton4);

        jPanel4.setLayout(null);
        jLabel3.setBounds(50,0,400,150);
        jLabel4.setBounds(450,0,400,150);
        jPanel4.add(jLabel3);
        jPanel4.add(jLabel4);


        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);
        this.add(jPanel4);


        this.setLayout(null);
        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args){

        SecurityFrame securityFrame = new SecurityFrame();

    }


}
