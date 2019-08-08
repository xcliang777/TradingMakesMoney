package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public abstract class Market {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static final double TRADE_FEE = 5.0;
	public static final double BOND_TRANS_FEE = 3.0;
	public static Date marketDate = new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime();
	public static Date curDate;

	private double transactionFee;

	Market(double tf) {
		transactionFee = tf;
	}

	public double getTransFee() {
		return transactionFee;
	}

}
