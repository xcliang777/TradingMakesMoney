package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyBondFrame extends JFrame implements ActionListener {

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JLabel jLabel0 = new JLabel("Information of All Bonds");

    JLabel jLabel1 = new JLabel("Bond ID");
    JTextField jTextField1 = new JTextField();
    JButton jButton1 = new JButton("Cancel");
    JButton jButton2 = new JButton("Buy");


    JTextArea jTextArea = new JTextArea("The information of All Bonds: ");


    BuyBondFrame(){

        this.setLayout(new GridLayout(1,2,0,0));

        jPanel1.setLayout(null);
        jTextArea.setBounds(50,100,300,400);
        jLabel0.setBounds(50,10,300,100);
        jPanel1.add(jTextArea);
        jPanel1.add(jLabel0);

        jPanel2.setLayout(null);
        jLabel1.setBounds(100,275,50,30);
        jTextField1.setBounds(200,275,50,30);
        jButton1.setBounds(50,375,100,50);
        jButton2.setBounds(250,375,100,50);
        jPanel2.add(jLabel1);
        jPanel2.add(jTextField1);
        jPanel2.add(jButton1);
        jPanel2.add(jButton2);

        jButton1.addActionListener(this);
        jButton2.addActionListener(this);



        this.add(jPanel1);
        this.add(jPanel2);
        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){

        BuyBondFrame buyBondFrame = new BuyBondFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==jButton1){
            System.out.println("cancel");
        } else if (e.getSource()==jButton2){
            System.out.println("buy");
        }

    }
}
