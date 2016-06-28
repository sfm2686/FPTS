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
}
