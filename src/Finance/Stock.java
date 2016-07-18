package Finance;

import java.io.Serializable;
import java.util.*;
import Market.Market;

/**
 * Stock extends the Equity abstract class and represents an owned
 * stock. A stock is owned by a portfolio which is owned by a user. All stocks
 * observe the Market.Market singleton for price updates. Stocks also implement
 * the java.io.Serializable interface so that they can be persisted within the database.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class Stock extends Equity implements Serializable, Observer {

	/****** Class Attributes ******/
	private double price;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a Stock object.
	 * @param numShares The number of shares of the stock that are owned.
	 * @param name The identifier of the equity, a ticker symbol.
	 */
	public Stock(int numShares, String name) {
		this.setNumShares(numShares);
		this.name = name; // Ticker symbol
		this.price = Market.getMarketInstance().getPrice(name);
		Market.getMarketInstance().addUpdateEquity(this);
	}

	/**
	 * getValue is defined by the Equity interface.
	 * 
	 * Accessor for the total value of a stock. 
	 * 
	 * @return A double representing the total value of an owned stock.
	 */
	@Override
	public double getValue() {
		return (getNumShares() * this.getPrice());
	}

	/**
	 * The ticker symbol is available by the getName method, however, 
	 * the getTickerSymbol is helpful in making code cleaner when distinguishing
	 * between stocks and indices.
	 * 
	 * @return The stock's name attribute (ticker symbol).
	 */
	public String getTickerSymbol() {
		return this.getName();
	}

	/**
	 * update is defined by the Observer interface.
	 * 
	 * Whenever the update method is called on a stock, it should
	 * simply update it's price.
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.price = Market.getMarketInstance().getPrice(this.name);
		this.price = Double.parseDouble(String.format("%.2f", this.price)); // Round it down
	}
	
	/**
	 * getPrice is defined by the Equity interface.
	 * 
	 * @return A double representing the price per share of a stock.
	 */
	@Override
	public double getPrice() {
		return this.price;
	}

	/**
	 * Generic toString method.
	 * @return The String representation of a Stock object.
	 */
	@Override
	public String toString() {
		return "Stock Holding: " + this.getName() + ", " 
				+ Market.getMarketInstance().getStockDescriptor(this.getName()) + ", " 
				+ this.getNumShares() + " shares, current price: $" 
				+ this.getPrice() + ", current value: " + this.getValue() + ".";
	}
	
	//Child related methods should not be implemented at all in leaf components.
	
	@Override
	public void addChild(Equity child) { }

	@Override
	public void removeChild(Equity child) { }
	
	@Override
	public ArrayList<Equity> getChildren() { return null; }

	/**
	 * Unit tests for Stock.
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Stock testStock = new Stock(100, "AAPL");

		int testCount = 6;
		int failCount = 0;

		if (testStock.getName() != "AAPL") {
			++failCount;
		}
		if (testStock.getNumShares() != 100) {
			++failCount;
		}
		if (testStock.getTickerSymbol() != "AAPL") {
			++failCount;
		}
		if (testStock.getValue() != 50000.00) {
			++failCount;
		}
		testStock.addShares(100);
		if (testStock.getValue() != 100000.00) {
			++failCount;
		}
		testStock.subtractShares(150);
		if (testStock.getValue() != 25000.00) {
			++failCount;
		}

		System.out.println("Conducting unit tests for Stock:\n" + (testCount - failCount) + " out of " + testCount
				+ " tests passed.");
	}

}
