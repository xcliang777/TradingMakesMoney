package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @description:
 * loan : current date,
 * calculate the loan + interest
 * borrow new loan must have paid back the old one
 **/

///Still have to push the data into database in setLoan and repayment method.


public class Loan {
    ArrayList<Loan> loanList = new ArrayList<Loan>();

    private double collateral;
    private double loan;
    private double arrears;
    private Date borrowDate;


    private double loanRate = 1.01;

    public Loan() {
        this.collateral = 0;
        this.loan = 0;
        this.arrears = 0;
    }

    public double getCollateral() {
        return collateral;
    }

    public void setCollateral(double collateral) {
        this.collateral = collateral;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public double getArrears() {
        return arrears;
    }

    public void setArrears(double arrears) {
        this.arrears = arrears;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

}