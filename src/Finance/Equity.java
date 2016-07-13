package Finance;

import java.io.Serializable;
import java.util.ArrayList;
import CSV.EquityUtil;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This abstract class defines what an equity/index should have. The
 *          composite pattern was originally applied here but the application of
 *          the Composite was moved to CSV because the intent was needed there.
 *
 */
public abstract class Equity implements Serializable {

	private int numShares;
	
	//Ticker symbol for stocks and name for sectors.
	private String name;

	protected void setNumShares(int shares) {
		this.numShares = shares;
	}

	public int getNumShares() {
		return this.numShares;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public boolean subtractShares(int numShares) {
		if (numShares <= this.numShares) {
			this.numShares -= numShares;
			return true;
		}
		return false;
	}

	public void addShares(int numShares) {
		this.numShares += numShares;
	}
	
	public abstract void addChild();
	public abstract void removeChild();

	public abstract double getPrice();
	public abstract String getName();
	public abstract double getValue();
	
}
