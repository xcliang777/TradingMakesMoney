package Frame;

import javax.swing.*;

import model.Investor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


/**
 * @author plutowang
 **/

public class CustomerFrame extends JFrame implements ActionListener {

    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();

    JButton jButton1 = new JButton("Log Out");
    JButton jButton2 = new JButton("Next Day");
    JButton jButton3 = new JButton("Checking Account");
    JButton jButton4 = new JButton("Saving Account");
    JButton jButton5 = new JButton("Security Account");
    JButton jButton6 = new JButton("Loans");

    JLabel jLabel1 = new JLabel("Today is, welcome");
    JLabel jLabel2 = new JLabel("Change Date:");

    SimpleDateFormat model = new SimpleDateFormat("MM.dd.yy");
    JSpinner spinner  = new JSpinner(new SpinnerDateModel());



    CustomerFrame(Investor a){

        jPanel1.setBounds(0,0,800,120);
        jPanel2.setBounds(0,120,800,120);
        jPanel3.setBounds(0,240,800,120);
        jPanel4.setBounds(0,360,800,240);

//        jPanel1.setBackground(Color.BLUE);
//        jPanel2.setBackground(Color.YELLOW);
//        jPanel3.setBackground(Color.RED);
//        jPanel4.setBackground(Color.GREEN);

        jPanel1.setLayout(null);
        jButton1.setBounds(500,40,200,40);
        jPanel1.add(jButton1);

        jPanel2.setLayout(null);
        jLabel1.setBounds(200,0,800,120);
        jLabel1.setFont(new Font("Dialog",1,20));
        jPanel2.add(jLabel1);

        jPanel3.setLayout(null);
        jLabel2.setBounds(250,0,100,120);
        jLabel1.setFont(new Font("Dialog",0,20));
        spinner.setEditor(new JSpinner.DateEditor(spinner, model.toPattern()));
        spinner.setBounds(350, 40, 120, 40);
        jButton2.setBounds(500,40,150,40);
        jPanel3.add(jLabel2);
        jPanel3.add(spinner);
        jPanel3.add(jButton2);

        jPanel4.setLayout(null);
        jButton3.setBounds(40,80,150,80);
        jButton4.setBounds(230,80,150,80);
        jButton5.setBounds(420,80,150,80);
        jButton6.setBounds(610,80,150,80);
        jPanel4.add(jButton3);
        jPanel4.add(jButton4);
        jPanel4.add(jButton5);
        jPanel4.add(jButton6);



        this.add(jPanel1);
        this.add(jPanel2);
        this.add(jPanel3);
        this.add(jPanel4);

        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        jButton4.addActionListener(this);
        jButton5.addActionListener(this);
        jButton6.addActionListener(this);

        this.setLayout(null);
        this.setBounds(300,100,800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){

        CustomerFrame customerFrame = new CustomerFrame();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jButton1){
            System.out.println("Log out");
        }else if (e.getSource()==jButton2){
            System.out.println("Next day");
        }else if (e.getSource()==jButton3){
            System.out.println("Checking Account");
        }else if (e.getSource()==jButton4){
            System.out.println("Saving Account");
        }else if (e.getSource()==jButton5){
            System.out.println("Security Account");
        }else if (e.getSource()==jButton6){
            System.out.println("Loans");
        }
    }
}
