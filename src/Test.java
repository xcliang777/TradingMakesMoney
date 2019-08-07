import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.SecurityAccount;


public class Test {
    public static void main(String[] args) throws SQLException, ParseException {
        SecurityAccount acc = new SecurityAccount(5000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = dateFormat.parse("2019-11-15");
        Date d2 = dateFormat.parse("2019-11-17");
        //acc.buyStock("aapl", 20, d1);
        //double a = acc.sellStock("aapl", 10, d2);
        //String strAllStock = acc.getAllStock();
//        int days = (int) (d1.getTime() - d2.getTime()) / 86400000;
//        System.out.println(days);
        //acc.getAllTransaction();
        //double unrealizedBenefit = acc.getUnrealizedBenefit(d2);
        //System.out.println(unrealizedBenefit);




        ///How to deal with date
//        Date d1 = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date d2 = dateFormat.parse("1995-09-26");
//        System.out.println(dateFormat.format(d2));
//        System.out.println(d2);
//        System.out.println(dateFormat.format(d1));
    }
}
