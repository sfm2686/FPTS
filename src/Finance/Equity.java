/**
 * 
 */
package Finance;

import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class Equity extends Holding {

	private int numShares;
	
	protected void setNumShares(int shares){
		this.numShares = shares;
	}
	
	public int getNumShares(){
		return this.numShares;
	}
	
	
	protected double getPrice( String name ){
		//TODO
		//THIS WILL BE DONE AFTER EQUITYBIN IS DONE
		return -1;
	}
	
	public String getName(){
		return super.getName();
	}
	
	//CHILD RELATED OPERATIONS
	
	abstract void removeChild(Stock child);
	abstract void addChild(Stock child);
	abstract ArrayList<Stock> getChildren();
	
}
