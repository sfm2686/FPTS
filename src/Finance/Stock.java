package Finance;

import java.io.Serializable;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class extends the Equity abstract class and represents an owned stock.
 * A stock is owned by a portfolio which is owned by a user. A stock has a 
 * reference to a StockUtil from EquityBin in the CSV subsystem. The stock
 * depends on the reference to get some of its information such as price.
 * This allows to dynamically change prices of stocks without directly changing
 * the owned objects until needed.
 *
 */
public class Stock extends Equity implements Serializable{

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
	
	@Override
	public EquityUtil getReference(){
		return this.referenceStock;
	}
	
	@Override
	public String toString(){
		return "Stock Holding: " + this.getName() + ", " + this.getTickerSymbol() 
			+ ", " + this.getNumShares() + " shares, current price: $" + this.getPrice() 
				+ ", current value: " + this.getValue() + ".";
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
