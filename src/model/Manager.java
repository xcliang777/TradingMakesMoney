package model;

/**
 * @description: Manager extends Person. Manager has the authority to see all transactions.
 **/
public class Manager extends Person {

    public Manager() {super();}

    public Manager(String id, String passWord) {
        super(id, passWord);
    }

    /**
     * @Description:  When manager wants to see all the transactions
     * @Param:
     * @return: java.lang.String
     */
    public void seeAllTransaction() {

    }




}
