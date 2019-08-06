package model;//package TradingMakesMoney;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class StockMarket extends Market {
	private ArrayList<Stock> stockMarket;
	
	StockMarket(double tf) {
		super(tf);
		stockMarket = new ArrayList<Stock>();
	}
	
	public boolean addStock(Stock toAdd) {
		if (this.hasStock(toAdd)) {
			return false;
		}
		stockMarket.add(toAdd);
		return true;
	}
	
	public boolean removeStock(Stock toRemove) {
		if(!this.hasStock(toRemove)) {
			return false;
		}
		stockMarket.remove(toRemove);
		return true;
	}
	
	public void updatePrices() {
		Iterator<Stock> iter = stockMarket.iterator();
		
		while(iter.hasNext()) {
			double randPercent = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
			randPercent = Math.round(randPercent * 10000.0)/ 10000.0;
			Stock toUpdate = iter.next();
			double newPrice = (toUpdate.getPrice() * randPercent) + toUpdate.getPrice();
			toUpdate.updatePrice(newPrice);
		}
		
	}
	
	//Prints all stocks and their prices
	public void printStocks() {
		Iterator<Stock> iter = stockMarket.iterator();
		
		while(iter.hasNext()) {
			Stock ex = iter.next();
			System.out.println(ex.getTicker() + ", $" + ex.getPrice());
		}
	}
	
	public boolean hasStock(String ticker) {
		Iterator<Stock> iter = stockMarket.iterator();
		
		while(iter.hasNext()) {
			Stock ex = iter.next();
			if (ex.getTicker().equalsIgnoreCase(ticker)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasStock(Stock toCheck) {
		Iterator<Stock> iter = stockMarket.iterator();
		String ticker = toCheck.getTicker();
		
		while(iter.hasNext()) {
			Stock ex = iter.next();
			if (ex.getTicker().equalsIgnoreCase(ticker)) {
				return true;
			}
		}
		return false;
		
	}
	
	public Stock getStock(Stock toGet) {
		Iterator<Stock> iter = stockMarket.iterator();
		String ticker = toGet.getTicker();
		
		while(iter.hasNext()) {
			Stock ex = iter.next();
			if (ex.getTicker().equalsIgnoreCase(ticker)) {
				return ex;
			}
		}
		return new Stock("EMPTY", "empty", 0.0);
	}
	
	public Stock getStock(String ticker) {
		Iterator<Stock> iter = stockMarket.iterator();
		
		while(iter.hasNext()) {
			Stock ex = iter.next();
			if (ex.getTicker().equalsIgnoreCase(ticker)) {
				return ex;
			}
		}
		return new Stock("EMPTY", "empty", 0.0);
	}
	
}

