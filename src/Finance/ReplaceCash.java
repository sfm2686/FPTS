/**
 * 
 */
package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class ReplaceCash extends Transaction {

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
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
