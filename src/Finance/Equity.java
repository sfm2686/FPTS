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

	protected String name;
	private int numShares;
	
	// Declare interface operations for manipulating the composite relationships
	public abstract void addChild(Equity node);
	public abstract void removeChild(Equity node);
	
	// Declare the method to be shared between all Equities (stocks and indexes)
	public abstract double getPrice();
	
	public double getValue(){
		return(this.getPrice() * this.getNumShares());
	}

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
