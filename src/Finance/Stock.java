/**
 * 
 */
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
	
	public String getTickerSymbol(){
		return this.referenceStock.getTickerSymbol();
	}
	
	public String getName(){
		return super.getName();
	}
	
	public double getPrice(){
		return this.referenceStock.getPrice();
	}
	
	
	// Following methods should not be implemented as part of the Composite (leaf) Pattern.
	
	@Override
	void removeChild(Stock child) {}

	@Override
	void addChild(Stock child) {}

	@Override
	ArrayList<Stock> getChildren() {return null;}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
