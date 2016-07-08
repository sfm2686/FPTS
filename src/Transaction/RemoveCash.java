/**
 * 
 */
package Transaction;

import java.io.Serializable;

import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This concerete command is responsible for removing the cash account
 *          that has the passed String as a name from the portfolio passed as
 *          the receiver.
 *
 */
public class RemoveCash extends Command implements Serializable {

	private String accountName;

	public RemoveCash(Portfolio receiver, String accountName) {
		super(receiver);
		this.accountName = accountName;
	}

	public boolean execute() {
		if (super.getReciever().hasCashAccount(this.accountName)) {
			CashAcct removal = null;
			for (CashAcct account : super.getReciever().getCashAccounts()) {
				if (account.getName().equalsIgnoreCase(this.accountName)) {
					removal = account;
					break;
				}
			}
			super.getReciever().removeCashAccount(removal);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + this.accountName
				+ "\n\tTransaction: Removed Cash Account";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
