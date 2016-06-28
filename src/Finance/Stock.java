/**
 * 
 */
package Finance;

import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Stock extends Equity {

	
	public String getTicker(){
		return super.getName();
	}
	
	public double getPrice(){
		return super.getPrice(getTicker());
	}
	
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
