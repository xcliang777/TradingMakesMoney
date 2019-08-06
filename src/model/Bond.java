package model;

import java.util.Date;
import java.util.*;

import java.util.Calendar;

public abstract class Bond {
	private Date dateBought;
	private String issuer;
	private double yield;
	private double price;
	//private double arrears;
	//private double interest rate;
	
	Bond(String name, double p, double ir) {
		issuer = name;
		price = p;
		yield = ir;
		dateBought = Market.today;
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
	
	public boolean updatePrice(double newPrice) {
		if(newPrice < 0) {
			return false;
		}
		price = newPrice;
		return true;
	}
	
	public boolean updateYield(double newYield) {
		if ( newYield < 0) {
			return false;
		}
		yield = newYield;
		return true;
	}
	
	public boolean equals(Bond toComp) {
		if((issuer.equals(toComp.getIssuer())) && (price == toComp.getPrice()) && (yield == toComp.getYield())) {
			return true;
		}
		
		return false;
	}
	
	abstract public Date dateDue();
	
	abstract public boolean isTotalDue();
	

}
