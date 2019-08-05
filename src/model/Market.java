package model;

import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Market {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static final double TRADE_FEE = 5.0;
	public static final double BOND_TRANS_FEE = 3.0;
	public static Date today = new Date();
	
	private double transactionFee;
	
	Market(double tf) {
		transactionFee = tf;
	}
	
	public double getTransFee() {
		return transactionFee;
	}
	
}
