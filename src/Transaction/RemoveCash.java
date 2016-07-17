package Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import Finance.*;

/**
 * RemoveCash is a concrete command responsible for removing a cash account from a 
 * specified portfolio.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class RemoveCash extends Command implements Serializable {

	/****** Class Attributes ******/
	private String accountName;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a RemoveCash command object.
	 * 
	 * @param receiver The portfolio containing the cash account to remove. 
	 * @param accountName The cash account to remove.
	 */
	public RemoveCash(Portfolio receiver, String accountName) {
		super(receiver);
		this.accountName = accountName;
	}

	/**
	 * execute is defined by the Command interface.
	 * 
	 * execute simply retrieves the specified cash account, if it exists, and 
	 * removes it from the portfolio of which it is a holding of.
	 * 
	 * @return A boolean indicating the success of the command.
	 */
	public boolean execute() {
//		if (super.getReceiver().hasCashAccount(this.accountName)) {
//			CashAcct removal = null;
//			for (CashAcct account : super.getReceiver().getCashAccounts()) {
//				if (account.getName().equalsIgnoreCase(this.accountName)) {
//					removal = account;
//					break;
//				}
//			}
//			super.getReceiver().removeCashAccount(removal);
//			return true;
//		}
//		return false;
		
		if (super.getReceiver().hasCashAccount(this.accountName)) {
			CashAcct removal = null;
			for (CashAcct account : super.getReceiver().getCashAccounts()) {
				if (account.getName().equalsIgnoreCase(this.accountName)) {
					super.getReceiver().removeCashAccount(account);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Generic toString method.
	 * 
	 * @return The string representation of this command object.
	 */
	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReceiver().getName() + "\n\tAccount: " + this.accountName
				+ "\n\tTransaction: Removed Cash Account";
	}

	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}

	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
