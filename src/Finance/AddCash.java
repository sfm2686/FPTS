package Finance;

import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AddCash extends Transaction implements Serializable {

	private String acctName;
	private double deposit;
	
	public AddCash(Portfolio receiver, String name, double deposit){
		super(receiver);
		this.acctName = name;
		this.deposit = deposit;
	}
	
	public boolean Execute(){
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null){
			acct.deposit(this.deposit);
			return true;
		}
		return false;	
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + 
				this.acctName + "\n\tTransaction: Deposit Cash" + "\n\tAmount: " +
				this.deposit;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
