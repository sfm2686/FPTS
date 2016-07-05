/**
 * 
 */
package WebServices;

import java.util.HashMap;
import java.util.Map;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class DOW {

	private double currentPrice = 0.0;
	private double dowDivisor = 0.14602128057775;
	private Map<String, Double> dict; 
	private String configPath = "./stocks.txt";

	public DOW(){
		this.dict = new HashMap<String, Double>();
		this.populateDict();
	}
	
	private void populateDict(){
		
	}
	
	/**
	 * Getter method to retrieve the current price of the DJIA
	 * @return The current price of the DJIA.
	 */
	public double getPrice(){
		return 0.0;
	}
	
	/**
	 * Formula for calculating the DJIA is: DWIA = ∑(component stocks) / (DJIA divisor)
	 */
	private void setPrice(){
		double sum = this.getIndexSum();
		this.currentPrice = (sum / this.dowDivisor);
		return;
		
	}
	
	/**
	 * Helper function to return the sum of prices for each component stock in the DJIA.
	 * @return sum The sum of all prices for each component stock in the DJIA
	 */
	private double getIndexSum(){
		double sum = 0;
		for(double price : dict.values()){
			sum += price;
		}
		return sum;
	}
	
}
