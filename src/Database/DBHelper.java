package Database;

import java.sql.*;
import java.util.Calendar;

/**
 * This is a helper class writig data into database.
 * GUI don't need to have read this class.
 */
public class DBHelper {
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void addInvestorStock(String ticker, String companyName, int numShare, double buyPrice) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into investorStock values(?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,ticker);
            ptmt.setString(2, companyName);
            ptmt.setInt(3, numShare);
            ptmt.setDouble(4, buyPrice);
            ptmt.execute();

            //System.out.println("succeed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addInvestorTransaction(String buyOrSell, String ticker, String companyName, double price, int numShare, Date date, double benefit) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into stockTransaction values(?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, buyOrSell);
            ptmt.setString(2, ticker);
            ptmt.setString(3, companyName);
            ptmt.setDouble(4, price);
            ptmt.setInt(5, numShare);
            ptmt.setDate(6, date);
            ptmt.setDouble(7, benefit);
            ptmt.execute();

            //System.out.println("succeed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the investor has that much numShare to sell.
     * @param ticker
     * @param numShare
     * @return
     * @throws SQLException
     */
    public static boolean checkSellShare(String ticker, int numShare) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorStock where ticker=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ResultSet rs = ptmt.executeQuery();
            int testNumSum = 0;
            while(rs.next()){
                testNumSum += rs.getInt("numShare");
            }
            if (numShare > testNumSum){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * A helper method for update database when sell a stock.
     * @param ticker
     * @param numShare
     * @param date
     * @return: benefit
     * @throws SQLException
     */
    public static double investorSellStock(String ticker, int numShare, Date date) throws SQLException {

        ///1. get market price and companyName by ticker and Date
        String companyName = "";
        double sellPrice = 0;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket where ticker=? and Date=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ptmt.setDate(2, date);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                companyName = rs.getString("CompanyName");
                sellPrice = rs.getDouble("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ///2.get buy price and calculate benefit.
        double benefit = 0;
        int shareHas =0;
        double buyPrice =0;
        int shareLeftToSell=numShare;
        int shareLeft = 0;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorStock where ticker=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                shareHas = rs.getInt("numShare");
                buyPrice = rs.getDouble("buyPrice");
                benefit += shareLeftToSell * (sellPrice - buyPrice);
                shareLeftToSell -= shareHas;
                if (shareLeftToSell >= 0) {
                    ///delete that line in DB
                    deleteStockLine(ticker, shareHas, buyPrice);
                }
                if (shareLeftToSell==0) {
                    break;
                }
                if (shareLeftToSell < 0) {
                    ///update numShare in investorStock
                    shareLeft = -shareLeftToSell;
                    //System.out.println(shareLeftToSell);
                    updateStockShare(ticker, buyPrice, shareLeft);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ///3.update trasactionn table
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "insert into stockTransaction values(?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "sell");
            ptmt.setString(2, ticker);
            ptmt.setString(3, companyName);
            ptmt.setDouble(4, sellPrice);
            ptmt.setInt(5, numShare);
            ptmt.setDate(6, date);
            ptmt.setDouble(7, benefit);
            ptmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///2.update investorStock table.

        return benefit;
    }

    public static String getAllStock(){
        StringBuilder sb = new StringBuilder();
        String ticker;
        String companyName;
        int numShare;
        double buyPrice;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorStock";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                ticker = rs.getString("ticker");
                companyName = rs.getString("companyName");
                numShare = rs.getInt("numShare");
                buyPrice = rs.getDouble("buyPrice");
                sb.append("companyName: " + companyName + ", ticker: " + ticker + ", numShare: " + numShare +
                        ", buyPrice: " + buyPrice + ".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getAllStockTransaction() {
        StringBuilder sb = new StringBuilder();
        String buyOrSell;
        String ticker;
        String companyName;
        double price;
        Date date;
        double benefit;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockTransaction";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                buyOrSell = rs.getString("buyOrSell");
                ticker = rs.getString("ticker");
                companyName = rs.getString("companyName");
                price = rs.getDouble("price");
                date = rs.getDate("date");
                benefit = rs.getDouble("benefit");
                sb.append("Transaction: "+buyOrSell+", ticker: "+ticker+", companyName: "+companyName+
                        ", price: "+price+", date: "+date+", benefit: "+benefit+".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static Double getUnrealizedBenefit(Date date) throws SQLException {
        double unrealizedBenefit=0;
        double buyPrice;
        String ticker;
        int numShare;
        double marketPrice;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorStock";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                ticker = rs.getString("ticker");
                buyPrice = rs.getDouble("buyPrice");
                numShare = rs.getInt("numShare");
                marketPrice = getMarketPrice(ticker, date);

                unrealizedBenefit += numShare * (marketPrice - buyPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unrealizedBenefit;
    }


    public static double getMarketPrice(String ticker, Date date) {
        double marketPrice = 0;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket where ticker=? and Date=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ptmt.setDate(2, date);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                marketPrice = rs.getDouble("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marketPrice;
    }

    public static String getCompanyName(String ticker) {
        String companyName="";
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket where ticker=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                companyName = rs.getString("CompanyName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyName;
    }



    public static void addIntoInvestorBond(String bondID, double amount, Date buyDate) throws SQLException {
        String companyName="";
        String type="";
        double yield=0;
        Date dueDate=buyDate;
        /////get three attributes by bondID
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "select * from bondMarket where bondID=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                companyName = rs.getString("CompanyName");
                type = rs.getString("type");
                yield = rs.getDouble("yield");
            }

            ///get due date
            if (type.equals("OneWkBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.WEEK_OF_MONTH, 1);
                dueDate = (java.sql.Date)rightNow.getTime();
            }
            if (type.equals("OneMonthBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.MONTH, 1);
                dueDate = (java.sql.Date)rightNow.getTime();
            }
            if (type.equals("ThreeMonthBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.MONTH, 3);
                dueDate = (java.sql.Date)rightNow.getTime();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        ///write in investorBond
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into investorBond values(?,?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
            ptmt.setString(2, companyName);
            ptmt.setString(3, type);
            ptmt.setDouble(4, yield);
            ptmt.setDouble(5, amount);
            ptmt.setDate(6, buyDate);
            ptmt.setDate(7, dueDate);
            ptmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        ///write in bondTransaction
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into bondTransaction values(?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "buy");
            ptmt.setString(2, bondID);
            ptmt.setString(3, companyName);
            ptmt.setString(4, type);
            ptmt.setDouble(5, amount);
            ptmt.setDate(6, buyDate);

            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Date getBondDueDate(String bondID, Date date) {
        Date dueDate=date;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorBond where bondID=? and ";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
            ptmt.setDate(2, date);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                dueDate = rs.getDate("dueDate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dueDate;
    }

    public static double getBondAmount(String bondID, boolean includingInterest) {
        double amount = 0;
        double yield = 0;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorBond where bondID=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()) {
                amount = rs.getDouble("price");
                yield = rs.getDouble("yield");
            }

            if (includingInterest) {
                amount = amount *(1 + yield);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public static void sellBond(String bondID, Date sellDate) {


    }


    public static String showAllInvestorBonds() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String bondID;
        String companName;
        String type;
        double yield;
        double price;
        Date buyDate;
        Date dueDate;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorBond";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                bondID = rs.getString("bondID");
                companName = rs.getString("companName");
                type = rs.getString("type");
                yield = rs.getDouble("yield");
                price = rs.getDouble("price");
                buyDate = rs.getDate("buyDate");
                dueDate = rs.getDate("dueDate");;
                sb.append("bondID: "+bondID+", companName: "+companName+", type: "+type+
                        ", price: "+price+", buyDate: "+buyDate+", dueDate: "+dueDate+".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String showAllBondTransaction() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String buyOrSell;
        String bondID;
        String companyName;
        String type;
        double price;
        Date date;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockTransaction";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                buyOrSell = rs.getString("buyOrSell");
                bondID = rs.getString("bondID");
                companyName = rs.getString("companyName");
                type = rs.getString("type");
                price = rs.getDouble("price");
                if (buyOrSell.equals("buy")){
                    date = rs.getDate("buyDate");
                } else {
                    date = rs.getDate("sellDate");
                }
                sb.append("Transaction: "+buyOrSell+", bondID: "+bondID+", companyName: "+companyName+
                        ", type: "+type+", price: "+price+", date: "+date+".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }



    public static void deleteDatabaseInfo() {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql1 = "delete from investorStock";
            PreparedStatement ptmt1 = conn.prepareStatement(sql1);
            ptmt1.execute();

            String sql2 = "delete from investorBond";
            PreparedStatement ptmt2 = conn.prepareStatement(sql2);
            ptmt2.execute();

            String sql3 = "delete from stockTrasaction";
            PreparedStatement ptmt3 = conn.prepareStatement(sql3);
            ptmt3.execute();

            String sql4 = "delete from bondTrasaction";
            PreparedStatement ptmt4 = conn.prepareStatement(sql4);
            ptmt4.execute();

            String sql5 = "delete from stockTransaction";
            PreparedStatement ptmt5 = conn.prepareStatement(sql5);
            ptmt5.execute();

            String sql6 = "delete from bondMarket";
            PreparedStatement ptmt6 = conn.prepareStatement(sql6);
            ptmt6.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * used for method investorSellStockin this class
     * @param ticker
     * @param numShare
     * @param buyPrice
     * @throws SQLException
     */
    private static void deleteStockLine(String ticker, int numShare, double buyPrice) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "delete from investorStock where ticker=? and numShare=? and buyPrice=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);
            ptmt.setInt(2,numShare);
            ptmt.setDouble(3, buyPrice);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * used for method investorSellStock in this class
     * @param ticker
     * @param buyPrice
     * @param shareLeft
     * @throws SQLException
     */
    private static void updateStockShare(String ticker, double buyPrice, int shareLeft) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "update investorStock set numShare =? where ticker=? and buyPrice=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, shareLeft);
            ptmt.setString(2,ticker);
            ptmt.setDouble(3, buyPrice);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
