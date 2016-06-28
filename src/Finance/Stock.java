package Finance;

import java.util.ArrayList;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Stock extends Equity {

	private StockUtil referenceStock;
	
	public Stock(int numShares, StockUtil referenceStock){
		super.setNumShares(numShares);
		this.referenceStock = referenceStock;
	}
	
	public double getValue(){
		return (super.getNumShares() * this.getPrice());
	}
	
	public String getTickerSymbol(){
		return this.referenceStock.getTickerSymbol();
	}
	
	public String getName(){
		return referenceStock.getName();
	}
	
	@Override
	public double getPrice(){
		return this.referenceStock.getPrice();
	}
	
	/**
	 * Unit tests for Stock.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StockUtil stockRef = new StockUtil("Apple", "AAPL", "500.00");
		Stock testStock = new Stock(100, stockRef);
		
		int testCount = 6;
		int failCount = 0;
		
		if (testStock.getName() != "Apple"){
			++failCount;
		}
		if (testStock.getNumShares() != 100){
			++failCount;
		}
		if (testStock.getTickerSymbol() != "AAPL"){
			++failCount;
		}
		if (testStock.getValue() != 50000.00){
			++failCount;
		}
		testStock.addShares(100);
		if (testStock.getValue() != 100000.00){
			++failCount;
		}
		testStock.subtractShares(150);
		if(testStock.getValue() != 25000.00){
			++failCount;
		}
		
		System.out.println("Conducting unit tests for Stock:\n" + (testCount - failCount) + " out of " + testCount + " tests passed.");
		
	}
}
