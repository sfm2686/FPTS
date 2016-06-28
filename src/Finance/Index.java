/**
 * 
 */
package Finance;

import java.util.ArrayList;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class is the composite in the composite pattern.
 * This class has all of the children-related operations.
 *
 */
public class Index extends Equity {

	private IndexUtil stocks;
	
	public Index( int numShares, IndexUtil indexUtil ){
	}
	
	@Override
	void removeChild(Stock child) {
		this.stocks.remove(child);
	}

	@Override
	void addChild(Stock child) {
		this.stocks.add(child);
	}

	@Override
	ArrayList<Stock> getChildren() {
		return this.stocks;
	}
	
	@Override
	double getPrice(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
