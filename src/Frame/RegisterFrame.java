package Frame;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();

    JLabel jLabel1 = new JLabel("Username");
    JLabel jLabel2 = new JLabel("Password");

    JTextField jTextField1 = new JTextField();
    JPasswordField jPasswordField1 = new JPasswordField();

    JButton jButton1 = new JButton("Cancel");
    JButton jButton2 = new JButton("Confirm");

    RegisterFrame(){

        jPanel1.setBounds(0,0,800,200);
        jPanel2.setBounds(0,200,800,200);
        jPanel3.setBounds(0,400,800,200);

//        jPanel1.setBackground(Color.BLUE);
//        jPanel2.setBackground(Color.GREEN);
//        jPanel3.setBackground(Color.YELLOW);

        jPanel1.setLayout(null);
        jLabel1.setBounds(200,66,200,66);
        jTextField1.setBounds(400,85,200,30);
        jPanel1.add(jLabel1);
        jPanel1.add(jTextField1);

        jPanel2.setLayout(null);
        jLabel2.setBounds(200,66,200,66);
        jPasswordField1.setBounds(400,85,200,30);
        jPanel2.add(jLabel2);
        jPanel2.add(jPasswordField1);

        jPanel3.setLayout(null);
        jButton1.setBounds(100,75,200,50);
        jButton2.setBounds(500,75,200,50);
        jPanel3.add(jButton1);
        jPanel3.add(jButton2);


        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);

        this.setLayout(null);
        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){

        RegisterFrame registerFrame = new RegisterFrame();

    }


}
