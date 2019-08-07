package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author plutowang
 **/
public class ManagerFrame extends JFrame implements ActionListener {

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();

    JLabel jLabel1 = new JLabel("All Investors");
    JLabel jLabel2 = new JLabel("Stocks");
    JLabel jLabel3 = new JLabel("Bonds");

    JTextArea jTextArea1 = new JTextArea("All Investors");
    JTextArea jTextArea2 = new JTextArea("All Transactions about Stocks");
    JTextArea jTextArea3 = new JTextArea("All Transactions about Bonds");

    JButton jButton = new JButton("Back");



    ManagerFrame(){

        this.setLayout(null);

        jPanel1.setBounds(0,0,800,540);
        jPanel1.setLayout(new GridLayout(3,1,0,0));
        jPanel1.add(jPanel2);
        jPanel1.add(jPanel3);
        jPanel1.add(jPanel4);

        jPanel2.setLayout(null);
        jLabel1.setBounds(50,0,100,200);
        jTextArea1.setBounds(150,10,600,180);
        jPanel2.add(jLabel1);
        jPanel2.add(jTextArea1);

        jPanel3.setLayout(null);
        jLabel2.setBounds(50,0,100,200);
        jTextArea2.setBounds(150,10,600,180);
        jPanel3.add(jLabel2);
        jPanel3.add(jTextArea2);

        jPanel4.setLayout(null);
        jLabel3.setBounds(50,0,100,200);
        jTextArea3.setBounds(150,10,600,180);
        jPanel4.add(jLabel3);
        jPanel4.add(jTextArea3);


        jPanel5.setBounds(0,540,800,60);
        jPanel5.setLayout(null);
        jButton.setBounds(400,0,100,30);
        jPanel5.add(jButton);

        this.add(jPanel1);
        this.add(jPanel5);

        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args){

        ManagerFrame managerFrame = new ManagerFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton){
            System.out.println("back");
        }
    }
}
