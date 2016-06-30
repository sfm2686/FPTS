package Finance;

import java.util.ArrayList;
import CSV.EquityUtil;

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
	
	public boolean subtractShares(int numShares){
		if (numShares <= this.numShares){
			this.numShares -= numShares;
			return true;
		}
		return false;
	}
	
	public void addShares(int numShares){
		this.numShares += numShares;
	}
	
	abstract double getPrice();
	public abstract EquityUtil getReference();
	public abstract String getName();
}
