package Finance;

import java.io.Serializable;

import CSV.EquityUtil;
import CSV.StockUtil;
import CSV.IndexUtil;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class is responsible to creating an equity and binding it to
 *          the instance of portfolio that is passed in the constructor
 *
 */
public class CreateEquity extends Transaction implements Serializable {

	private EquityUtil reference;
	private int numShares;

	public CreateEquity(Portfolio receiver, EquityUtil reference, int numShares) {
		super(receiver);
		this.reference = reference;
		this.numShares = numShares;
	}

	public boolean Execute() {
		if (super.getReciever().hasEquity(this.reference)) {
			return false;
		}
		Equity equity;
		try {
			StockUtil stockRef = (StockUtil) this.reference;
			equity = new Stock(numShares, stockRef);
		} catch (Exception e) {
			IndexUtil indexRef = (IndexUtil) this.reference;
			equity = new Index(numShares, indexRef);
		}
		super.getReciever().addEquity(equity);
		return true;
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.reference.getName()
				+ "\n\tTransaction: Created new Equity" + "\n\tShares: " + this.numShares;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
