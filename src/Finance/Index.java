package Finance;

import java.util.ArrayList;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 */
public class Index extends Equity {

	private IndexUtil referenceIndex;
	
	public Index(int numShares, IndexUtil referenceIndex){
		super.setNumShares(numShares);
		this.referenceIndex = referenceIndex;
	}
	
	public double getPrice(String name) {
		return this.referenceIndex.getPrice();
	}

	public String getName(){
		return this.referenceIndex.getName();
	}
	
	/**
	 * Unit Tests for Index
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
