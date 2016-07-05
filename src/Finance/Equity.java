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
public abstract class Equity implements Holding, Serializable {

	private int numShares;

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

	abstract double getPrice();

	public abstract EquityUtil getReference();

	public abstract String getName();
}
