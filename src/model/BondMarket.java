package model;//package TradingMakesMoney;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

public class BondMarket extends Market {
	private ArrayList<Bond> bondMarket = new ArrayList<Bond>();
	
	BondMarket(double tf) {
		super(tf);
		bondMarket = new ArrayList<Bond>();
	}
	
	public boolean addBond(Bond toAdd) {
		//add guard
		bondMarket.add(toAdd);
		return true;
	}
	
	public boolean removeBond(Bond toRemove) {
		//add guard
		bondMarket.remove(toRemove);
		return true;
	}
	
	public boolean hasBond(Bond toCheck) {
		Iterator<Bond> iter = bondMarket.iterator();
		
		while (iter.hasNext()) {
			Bond ex = iter.next();
			if(ex.equals(toCheck)) {
				return true;
			}
		}
		return false;
	}
	
	public Bond getBond(Bond toGet) {
		Iterator<Bond> iter = bondMarket.iterator();
		
		while (iter.hasNext()) {
			Bond ex = iter.next();
			if(ex.equals(toGet)) {
				return ex;
			}
		}
		return new OneWkBond("Empty", 0, 0);
	}
	
	public void updatePrices() {
		Iterator<Bond> iter = bondMarket.iterator();
		
		while (iter.hasNext()) {
			double randPercent = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
			randPercent = Math.round(randPercent * 10000.0)/ 10000.0;
			Bond toUpdate = iter.next();
			double newPrice = (toUpdate.getPrice() * randPercent) + toUpdate.getPrice();
			double newYield = toUpdate.getYield();
			
			//if the percent change in price is negative, yield should go up and vice versa
			if ((newYield - randPercent) <= 0) {
				newYield = 0.01;
			}
			else {
				newYield -= randPercent;
			}
			
			toUpdate.updatePrice(newPrice);
			toUpdate.updateYield(newYield);
		}
		
	}

}
