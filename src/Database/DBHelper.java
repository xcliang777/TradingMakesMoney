package Database;

import java.sql.*;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import model.*;

/**
 * This is a helper class writig data into database.
 * GUI don't need to have read this class.
 */
public class DBHelper {
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void initStockMarket() throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql1 = "insert into stockMarket values(?,?,?,?)";
            PreparedStatement ptmt1 = conn.prepareStatement(sql1);

            ptmt1.execute();

            //System.out.println("succeed");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

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

    public static void addMarketStock(Date date, String companyName, String ticker, double price) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into stockMarket values(?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setDate(1, date);
            ptmt.setString(2, companyName);
            ptmt.setString(3, ticker);
            ptmt.setDouble(4, price);
            ptmt.execute();

            //System.out.println("succeed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMarketBond(String companyName, String type, double yield, double price, String bondID) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into bondMarket values(?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, companyName);
            ptmt.setString(2, type);
            ptmt.setDouble(3, yield);
            ptmt.setDouble(4, price);
            ptmt.setString(5, bondID);
            ptmt.execute();

            //System.out.println("succeed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeMarketStock(String ticker) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            String sql = "delete from stockMarket where Ticker = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, ticker);

            ptmt.execute();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeMarketBond(String bondID) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            String sql = "delete from bondMarket where bondID = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);

            ptmt.execute();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Will return a copy of the bond that represents the bondID
     * If the market doesn't contain that bond, it will return an empty bond.
     */
    public static Bond getMarketBond(String bondID) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from bondMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                String toComp = rs.getString("bondID");
                double yield = rs.getDouble("yield");
                double price = rs.getDouble("price");
                String name = rs.getString("companyName");
                String type = rs.getString("type");


                if (toComp.equals(bondID)) {
                    if(type.equalsIgnoreCase("OneWkBond")) {
                        return new model.OneWkBond(name, bondID, price, yield);
                    }
                    else if (type.equalsIgnoreCase("OneMonthBond")) {
                        return new model.OneMonthBond(name, bondID, price, yield);
                    }
                    else {
                        return new model.ThreeMonthBond(name, bondID, price, yield);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new model.OneWkBond("empty", "empty", 0.0, 0.0);
    }

    public static Stock getMarketStock(String ticker) throws SQLException{
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                String toComp = rs.getString("Ticker");
                double price = rs.getDouble("Price");
                String name = rs.getString("CompanyName");
                Date date = rs.getDate("Date");


                if (toComp.equals(ticker)) {
                    return new model.Stock(ticker, name, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new model.Stock("empty", "empty", 0.0);
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

    public static boolean marketHasStock(String toCompTicker) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            //Needs to be fixed.
            while(rs.next()){
                //get fields
                String ticker = rs.getString("Ticker");

                if(ticker.equals(toCompTicker)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean marketHasBond(String toCompBondID) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from bondMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            //Needs to be fixed.
            while(rs.next()){
                //get fields
                String bondID = rs.getString("bondID");

                if(bondID.equals(toCompBondID)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateStockMarket(Date newDate) throws SQLException {
        StringBuilder sb = new StringBuilder();
        Date date;
        String companyName;
        String ticker;
        double price;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                //get fields
                date = rs.getDate("Date");
                companyName = rs.getString("CompanyName");
                ticker = rs.getString("Ticker");
                price = rs.getDouble("Price");

                //calculate the newPrice
                double randPercent = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
                randPercent = Math.round(randPercent * 10000.0)/ 10000.0;
                double newPrice = (price * randPercent) + price;
                newPrice = (Math.round(newPrice) * 100) / 100;

                try {
                    Connection conn1 = DB.getConnection();
                    statement = conn1.createStatement();

                    String sql1 = "update stockMarket set price=? where ticker=?";
                    PreparedStatement ptmt1 = conn1.prepareStatement(sql1);
                    ptmt1.setDouble(1, newPrice);
                    ptmt1.setString(2, ticker);
                    ptmt1.execute();

                    //System.out.println("succeed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    Connection conn2 = DB.getConnection();
                    statement = conn2.createStatement();

                    String sql2 = "update stockMarket set date=? where ticker=?";
                    PreparedStatement ptmt2 = conn2.prepareStatement(sql2);
                    ptmt2.setDate(1, newDate);
                    ptmt2.setString(2, ticker);
                    ptmt2.execute();

                    //System.out.println("succeed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBondMarket() throws SQLException {
        StringBuilder sb = new StringBuilder();
        Date newDate;
        String bondID;
        String companyName;
        double price;
        String type;
        double yield;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from bondMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            //Needs to be fixed.
            while(rs.next()){
                //get fields
                companyName = rs.getString("companyName");
                type = rs.getString("type");
                yield = rs.getDouble("yield");
                price = rs.getDouble("price");
                bondID = rs.getString("bondID");

                //calculate new price and yield
                double randPercent = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
                randPercent = Math.round(randPercent * 10000.0)/ 10000.0;
                double newPrice = (price * randPercent) + price;
                newPrice = Math.round(newPrice * 100) / 100;
                double newYield = yield;

                //if the percent change in price is negative, yield should go up and vice versa
                if ((newYield - 0.01) <= 0) {
                    newYield = 0.01;
                }
                else if ((newYield + 0.01) >= 1) {
                    newYield = 0.99;
                }
                else if (randPercent <= 0){
                    newYield += 0.01;
                }
                else {
                    newYield -= 0.01;
                }



                //update table
                try {
                    Connection conn1 = DB.getConnection();
                    statement = conn1.createStatement();

                    String sql1 = "update bondMarket set price=? where bondID=?";
                    PreparedStatement ptmt1 = conn1.prepareStatement(sql1);
                    ptmt1.setDouble(1,newPrice);
                    ptmt1.setString(2, bondID);
                    ptmt1.execute();

                    //System.out.println("succeed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    Connection conn2 = DB.getConnection();
                    statement = conn2.createStatement();

                    String sql2 = "update bondMarket set yield=? where bondID=?";
                    PreparedStatement ptmt2 = conn2.prepareStatement(sql2);
                    ptmt2.setDouble(1,newYield);
                    ptmt2.setString(2, bondID);
                    ptmt2.execute();

                    //System.out.println("succeed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAllMarketStock() throws SQLException{
        StringBuilder sb = new StringBuilder();
        Date date;
        String companyName;
        String ticker;
        double price;

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from stockMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                date = rs.getDate("Date");
                companyName = rs.getString("CompanyName");
                ticker = rs.getString("Ticker");
                price = rs.getDouble("Price");
                sb.append("date: " + date + ", companyName: " + companyName + ",\n    ticker: " + ticker +
                        ", price: " + price + ".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getAllMarketBond() throws SQLException{
        StringBuilder sb = new StringBuilder();
        String companyName;
        String type;
        double yield;
        double price;
        String bondID;

        DBHelper helper = new DBHelper();
        helper.updateBondMarket();

        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from bondMarket";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                companyName = rs.getString("companyName");
                type = rs.getString("type");
                yield = rs.getDouble("yield");
                price = rs.getDouble("price");
                bondID = rs.getString("bondID");
                sb.append("companyName: " + companyName + ", type: " + type +
                        ",\n    yield: " + yield + ", price: " + price + ", bondID: " + bondID + ".\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
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

    public static String getAllStock() throws SQLException{
        StringBuilder sb = new StringBuilder();
        String ticker;
        String companyName;
        int numShare;
        double buyPrice;
        Date date = new java.sql.Date(Market.curDate.getTime());
        DBHelper helper = new DBHelper();
        helper.updateStockMarket((java.sql.Date)date);
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
                sb.append("companyName: " + companyName + ", ticker: " + ticker + ",\n    numShare: " + numShare +
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
                        ",\n    price: "+price+", date: "+date+", benefit: "+benefit+".\n");
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



    public static double addIntoInvestorBond(String bondID, Date buyDate) throws SQLException {
        String companyName="";
        String type="";
        double amount=0;
        double yield=0;
        java.sql.Date dueDate = buyDate;
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
                amount = rs.getDouble("price");
            }

            ///get due date
            if (type.equals("OneWkBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.WEEK_OF_MONTH, 1);
                java.util.Date utilStartDate =rightNow.getTime();
                dueDate = new java.sql.Date(utilStartDate.getTime());
            }
            if (type.equals("OneMonthBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.MONTH, 1);
                java.util.Date utilStartDate =rightNow.getTime();
                dueDate = new java.sql.Date(utilStartDate.getTime());
            }
            if (type.equals("ThreeMonthBond")) {
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(buyDate);
                rightNow.add(Calendar.MONTH, 3);
                java.util.Date utilStartDate =rightNow.getTime();
                dueDate = new java.sql.Date(utilStartDate.getTime());
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
        return amount;
    }

    public static Date getBondDueDate(String bondID, Date date) {
        Date dueDate=date;
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorBond where bondID=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
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

    public static void sellBond(String bondID, Date sellDate, double sellAmount) throws SQLException{


        ///delete line in investorBond
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "delete from investorBond where bondID=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, bondID);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///get companyname,type
        String companyName="";
        String type="";
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();
            String sql = "select * from investorBond";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while(rs.next()){
                companyName = rs.getString("companName");
                type = rs.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///add line in bondTransaction
        try {
            Connection conn = DB.getConnection();
            statement = conn.createStatement();

            String sql = "insert into bondTransaction values(?,?,?,?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, "sell");
            ptmt.setString(2, bondID);
            ptmt.setString(3, companyName);
            ptmt.setString(4, type);
            ptmt.setDouble(5, sellAmount);
            ptmt.setDate(6, sellDate);

            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                companName = rs.getString("companyName");
                type = rs.getString("type");
                yield = rs.getDouble("yield");
                price = rs.getDouble("price");
                buyDate = rs.getDate("buyDate");
                dueDate = rs.getDate("dueDate");;
                sb.append("bondID: "+bondID+", companName: "+companName+", type: "+type+
                        ",\n    price: "+price+", buyDate: "+buyDate+", dueDate: "+dueDate+".\n");
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
                date = rs.getDate("date");
                sb.append("Transaction: "+buyOrSell+", bondID: "+bondID+", companyName: "+companyName+
                        ",\n    type: "+type+", price: "+price+", date: "+date+".\n");
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
