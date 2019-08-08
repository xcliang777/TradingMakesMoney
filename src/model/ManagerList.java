package model;

import java.util.ArrayList;

/**
 * Default setting manager's id=123, password=123.
 */
public class ManagerList {

    public static ArrayList<Manager> managerList = new ArrayList<>();

    public ManagerList() {
        Manager manager = new Manager("123", "123");
        managerList.add(manager);
    }


    /**
     * Check manager's id and password. Return the instance of manager. If id and password doesn't match, return null.
     * default setting: manager's id =  123, password = 123.
     * @param id
     * @param password
     * @return
     */
    public static Manager checkManagerIdentity(String id, String password) {

        for (Manager m : managerList) {
            if (m.getId().equals(id) && m.getPassWord().equals(password) ) {
                return m;
            }
        }
        return null;
    }

}
