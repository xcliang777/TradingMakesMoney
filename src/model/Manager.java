package model;

import Database.DBHelper;

import java.sql.SQLException;

/**
 * @description: Manager extends Person. Manager has the authority to see all transactions.
 **/
public class Manager extends Person {

    public Manager() {super();}

    public Manager(String id, String passWord) {
        super(id, passWord);
    }


    /**
     * Manager can see all transactions in the market.
     * @return
     */
    public String seeAllStockTransaction() throws SQLException {
        DBHelper helper = new DBHelper();
        String str = helper.getAllStockTransaction();
        //System.out.println(str);
        return str;
    }

    public String seeAllBondTransaction() throws SQLException {
        DBHelper helper = new DBHelper();
        String str = helper.showAllBondTransaction();
        return str;
    }


}
