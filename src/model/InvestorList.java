package model;

import java.util.ArrayList;

/**
 * The class using ArrayList to store the customer info
 */
public class InvestorList {
    private ArrayList<Investor> investorList;

    public Investor createInvestor(String id, String password) {
        Investor investor = new Investor(id, password);
        investorList.add(investor);

        return investor;
    }

    /**
     * this method is used for user login
     * @param id
     * @param password
     * @return
     */
    public Investor checkInvestorIdentity(String id, String password) {
        for (Investor investor : investorList) {
            if (investor.getId().equals(id) && investor.getPassWord().equals(password)) {
                return investor;
            }
        }

        return null;
    }



}
