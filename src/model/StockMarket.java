package model;//package TradingMakesMoney;

import java.util.ArrayList;

public class StockMarket extends Market {
	private ArrayList<Stock> stockMarket;

	StockMarket(double tf) {
		super(tf);
		stockMarket = new ArrayList<Stock>();
	}

	public boolean addStock(Stock toAdd) {
		//add guard
		stockMarket.add(toAdd);
		return true;
	}

	public boolean removeStock(Stock toRemove) {
		//add guard
		stockMarket.remove(toRemove);
		return true;
	}

	public void updatePrices() {}

	public Stock getStock(Stock toGet) {
		return new Stock("EXPL", "example", 0.0);
	}



}
