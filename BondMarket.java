package TradingMakesMoney;

import java.util.ArrayList;

public class BondMarket extends Market{
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
	
	public Bond getBond(Bond b) {
		return new Bond("example", 0.0, 0.0, "1wk");
	}

}
