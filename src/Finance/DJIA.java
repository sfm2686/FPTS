package Finance;

import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class DJIA extends Index{

	private double dowDivisor = 0.14602128057775; // Could not find an API to retrieve this dynamically

	public DJIA(int numShares){
		super(numShares, "Dow Jones Industrial Average");
	}

	@Override
	public double getPrice(){
		double sum = this.getIndexSum();
		double price = (sum / this.dowDivisor);
		return (Math.round(price * 100.0) / 100.0);
	}

	/**
	 * Helper function to return the sum of prices for each component stock in the DJIA.
	 * @return sum The sum of all prices for each component stock in the DJIA
	 */
	private double getIndexSum(){
		double sum = 0.0;
		for(Equity child : this.children){
			sum += child.getPrice();
		}
		return sum;
	}
	
	/**
	 * Helper method for testing purposes.
	 * @return
	 */
	private String listStockPrices(){
		String str = "\n";
		for(Equity element : this.children){
			str += "\tTicker: " + element.toString() + ", Price: " + element.getPrice() + "\n";
		}
		return str;
	}
}
