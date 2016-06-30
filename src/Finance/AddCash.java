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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
