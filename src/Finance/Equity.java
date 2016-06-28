/**
 * 
 */
package Finance;

import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class Equity implements Holding {

	private int numShares;
	
	protected void setNumShares(int shares){
		this.numShares = shares;
	}
	
	public int getNumShares(){
		return this.numShares;
	}

	//CHILD RELATED OPERATIONS
	
	abstract void removeChild(Stock child);
	abstract void addChild(Stock child);
	abstract ArrayList<Stock> getChildren();
	
}
