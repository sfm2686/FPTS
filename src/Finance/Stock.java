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
		// TODO Auto-generated method stub

	}
}
