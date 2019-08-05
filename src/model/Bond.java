package model;

import java.util.Date;

public class Bond {
	private Date dateBought;
	private Date maturityDate;
	private String issuer;
	private double yield;
	private double price;
	//private double arrears;
	//private double interest rate;
	
	Bond(String name, double p, double ir, String type) {
		issuer = name;
		price = p;
		yield = ir;
		
		//Way to diff b/w bonds will be added with a case statement probably
	}
	
	//public double getInterestRate() {}
	//public boolean paymentDue() {}
	//public boolean faceValDue() {} <- delete totalDue() method.
	
	public double getPrice() {
		return price;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public double getYield() {
		return yield;
	}
	
	public Date getPurchaseDate() {
		return dateBought;
	}
	
	public Date dateDue() {
		return maturityDate;
	}
	
	public boolean isTotalDue() {
		//check if today's date is the same as maturity date.
		return false;
	}
	

}
