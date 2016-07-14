package Transaction;

import Finance.Equity;
import Finance.Portfolio;
import Market.Market;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class operates similarly to AddCash class, however this
 *          concrete command adds shares to an equity that the owner of the
 *          portfolio, user, already owns. Instead of adding money to a cash
 *          account like AddCash
 *
 */
public class AddEquity extends Command {

	private String equityName;
	private int numShares;

	public AddEquity(Portfolio receiver, String name, int shares) {
		super(receiver);
		this.equityName = name;
		this.numShares = shares;
	}

	public boolean execute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.addShares(this.numShares);
			return true;
		}
		return false;
	}
	
	public double getTransactionValue(){
		if (Market.getMarketInstance().isStock(this.equityName))
			return(this.numShares * Market.getMarketInstance().getPrice(this.equityName));
		else if(Market.getMarketInstance().isIndex(this.equityName))
			return(this.numShares * Market.getMarketInstance().getIndexPrice(this.equityName));
		else
			return(0.0);
	}

	@Override
	public String toString() {
		return "\nDate: " + this.getTransactionDate() + "\n\tPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Add Equity" + "\n\tShares: " + this.numShares;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
