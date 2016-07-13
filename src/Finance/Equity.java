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

	/**
	 * Method to compare to equities to each other.
	 * @param comparison
	 * @return
	 */
	public boolean equals(Equity comparison){
		return(this.name.equals(comparison.getName()));
	}

}
