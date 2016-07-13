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
public abstract class Equity implements Holding, Serializable {

	private String name;
	private int numShares;
	private ArrayList<Equity> children;
	
	// Declare inteface operations for manipulating the composite relationships
	public abstract void add(Equity child);
	public abstract void remove(Equity child);
	public abstract Equity getChild(int index);
	public abstract double getPrice();
	public abstract String getName();
	public abstract double getValue();

	// Operations for accessing/manipulating the number of shares
	protected void setNumShares(int shares) {
		this.numShares = shares;
	}

	public int getNumShares() {
		return this.numShares;
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
	
	/**
	 * Method to compare to equities to each other.
	 * @param comparison
	 * @return
	 */
	public boolean equals(Equity comparison){
		return(this.name.equals(comparison.getName()));
	}


}
