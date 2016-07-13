package Finance;

import java.io.Serializable;
import java.util.ArrayList;

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

	// Declare interface operations for manipulating the composite relationships
	public abstract void addChild(Equity child);
	public abstract void removeChild(Equity child);
	public abstract Equity getChild(int index);
	
	// Declare methods shared between all Equities (stocks and indexes)
	public abstract double getPrice();
	public abstract double getValue();


	// Operations for accessing/manipulating the number of shares
	
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
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * Method to compare to equities to each other.
	 * @param comparison
	 * @return
	 */
	public boolean equals(Equity comparison){
		return(this.name.equals(comparison.getName()));
	}

}
