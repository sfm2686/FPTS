package Finance;

import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SubtractCash extends Transaction implements Serializable {

	private String acctName;
	private double withdrawal;
	
	public SubtractCash(Portfolio receiver, String acctName, double withdrawal){
		super(receiver);
		this.acctName = acctName;
		this.withdrawal = withdrawal;
	}
	
	public boolean Execute(){
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null){
			if(acct.withdraw(this.withdrawal)){
				return true;
			}
			else{
				return false;
			}
		}
		return false;	
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + 
				this.acctName + "\n\tTransaction: Cash Withdrawal" + "\n\tAmount withdrew: " +
				this.withdrawal;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
