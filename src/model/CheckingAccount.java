package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @description:
 **/
public class CheckingAccount extends Account {

    ArrayList<Loan> loanList = new ArrayList<Loan>();

    public CheckingAccount() {
    }

    public CheckingAccount(double balance) {
        super(balance);
    }

    /**
     * @Description:  Request a loan.
     *               Return true if they have enough collateral.
     *               Return false if they don't have enough collateral to borrow loan.
     */
    public boolean borrowLoan(double collateral, double amount, Date date) {
        //if (loanList.size() > 0) return false;
        Loan loan = new Loan();
        loan.setCollateral(collateral);
        if (amount > collateral) return false;
        loan.setLoan(amount);
        setBalance(getBalance() + amount);
        loan.setBorrowDate(date);

        loanList.add(loan);
        return true;
    }

    /**
     * return a double of the amount investor has to pay back that day
     */
    public double amountToPayBack(Date repaymentDate) {
        Loan loan;
        try{
            loan = loanList.get(0);
        }catch (Exception e){
            return 0;
        }

        Date borrowDate = loan.getBorrowDate();
        double loanRate = loan.getLoanRate();

        int days = (int) (repaymentDate.getTime() - borrowDate.getTime()) / 86400000;
        double arrears = loan.getLoan() * Math.pow(loanRate, days);

        System.out.println(arrears);

        loan.setArrears(arrears);

        return arrears;
    }

    /**
     * pay back, update balance of checking account
     */
    public void repayment(Date repaymentDate) {
        Loan loan = loanList.get(0);

        Date borrowDate = loan.getBorrowDate();
        double loanRate = loan.getLoanRate();

        int days = (int) (repaymentDate.getTime() - borrowDate.getTime()) / 86400000;
        double arrears = loan.getLoan() * Math.pow(loanRate, days);

        setBalance(getBalance() - arrears);
        loanList.remove(0);

        loan.setLoan(0);
        loan.setArrears(0);
    }



}
