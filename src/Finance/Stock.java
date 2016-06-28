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

	private String name;
	
	
	public String getTicker(){
		return this.name;
	}
	
	@Override
	double getPrice(String name) {
		// TODO Auto-generated method stub
		return 0;
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
