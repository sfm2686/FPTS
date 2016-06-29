/**
 * 
 */
package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class RemoveCash extends Transaction {

	private String accountName;
	
	public RemoveCash(Portfolio receiver, String accountName){
		super(receiver);
		this.accountName = accountName;
	}
	
	public boolean Execute(){
		if (super.getReciever().hasCashAccount(this.accountName)){
			CashAcct removal = null;
			for(CashAcct account : super.getReciever().getCashAccounts()){
				if (account.getName().equalsIgnoreCase(this.accountName)){
					removal = account;
					break;
				}
			}
			super.getReciever().removeCashAccount(removal);
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
