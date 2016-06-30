/**
 * 
 */
package Finance;

import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This concrete is responsible for creating a new cash accounts and binding it to
 * the portfolio that is passed in the constructor. 
 *
 */
public class CreateCash extends Transaction implements Serializable {

	private String accountName;
	private double balance;
	
	public CreateCash(Portfolio receiver, String name, double balance){
		super(receiver);
		this.accountName = name;
		this.balance = balance;
	}
	
	public boolean Execute(){
		Portfolio receiver = super.getReciever();
		if (receiver.hasCashAccount(this.accountName)){
			return false;
		}
		receiver.addCashAccount(new CashAcct(this.accountName, this.balance));
		return true;
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + 
				this.accountName + "\n\tTransaction: Created Cash Account" + "\n\tInitial Amount: " +
				this.balance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
