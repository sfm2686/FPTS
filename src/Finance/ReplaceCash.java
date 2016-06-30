/**
 * 
 */
package Finance;

import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class ReplaceCash extends Transaction implements Serializable{

	private String accountName;
	private double newBalance;
	
	public ReplaceCash(Portfolio receiver, String accountName, double newBalance){
		super(receiver);
		this.accountName = accountName;
		this.newBalance = newBalance;
	}
	
	@Override
	public boolean Execute(){
		if (super.getReciever().hasCashAccount(this.accountName)){
			super.getReciever().getCashAcct(this.accountName).setBalance(this.newBalance);;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + 
				this.accountName + "\n\tTransaction: Reset Balane" + "\n\tNew Balance: " +
				this.newBalance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
