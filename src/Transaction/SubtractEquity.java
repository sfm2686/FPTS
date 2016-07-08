/**
 * 
 */
package Transaction;

import java.io.Serializable;

import Finance.Equity;
import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class subtracts shares from an equity that belongs to the
 *          receiver portfolio that has the same name as the passed in string.
 *          Also the number of shares is provided.
 *
 */
public class SubtractEquity extends Command implements Serializable {

	private String equityName;
	private int numShares;

	public SubtractEquity(Portfolio receiver, String equityName, int numShares) {
		super(receiver);
		this.equityName = equityName;
		this.numShares = numShares;
	}

	public boolean execute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.subtractShares(this.numShares);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Remove Shares" + "\n\tShares Removed: " + this.numShares;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
