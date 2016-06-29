/**
 * 
 */
package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CreateCash extends Transaction {

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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
