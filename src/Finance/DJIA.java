package Finance;

import java.io.Serializable;

/**
 * DJIA, or Dow Jones Industrial Average, is a subclassed Index. This allows for any instance
 * of the DOW index to have a unique algorithm for determining the price per share. DJIA
 * objects implement the java.io.Serializable method to allow for persistence in the database.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class DJIA extends Index implements Serializable{

	/****** Class Attributes ******/
	private double dowDivisor = 0.14602128057775; // Could not find an API to retrieve this dynamically

	/****** Class Methods ******/
	
	public DJIA(int numShares){
		super(numShares, "DOW");
	}

	/**
	 * getPrice is specified by the Equity interface.
	 * 
	 * getPrice simply return the calculated value of a share of the DJIA. The calculation
	 * uses a unique divisor, known here as the dowDivisor.
	 * 
	 * @return A double representing the price per share of the Dow Jones Industrial Average.
	 */
	@Override
	public double getPrice(){
		double sum = this.getIndexSum();
		double price = (sum / this.dowDivisor);
		return (Math.round(price * 100.0) / 100.0);
	}

	/**
	 * getIndexSum is a helper function to return the sum of
	 * prices for each component stock in the DJIA.
	 * 
	 * @return sum The sum of all prices for each component stock in the DJIA.
	 */
	private double getIndexSum(){
		double sum = 0.0;
		for(Equity child : this.children){
			sum += child.getPrice();
		}
		return sum;
	}
	
	/**
	 * listStockPrices is a helper method for testing purposes.
	 * 
	 * @return A String displaying all of the component stocks within the DJIA and their respective prices.
	 */
	public String listStockPrices(){
		String str = "\n";
		for(Equity element : this.children){
			str += "\tTicker: " + element.toString() + ", Price: " + element.getPrice() + "\n";
		}
		return str;
	}
}
