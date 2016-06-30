/**
 * 
 */
package Finance;

import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SubtractEquity extends Transaction implements Serializable {

	private String equityName;
	private int numShares;
	
	public SubtractEquity(Portfolio receiver, String equityName, int numShares){
		super(receiver);
		this.equityName = equityName;
		this.numShares = numShares;
	}
	
	public boolean Execute(){
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null){
			equity.subtractShares(this.numShares);
			return true;
		}
		return false;	
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + 
				this.equityName + "\n\tTransaction: Remove Shares" + "\n\tShares Removed: " +
				this.numShares;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
