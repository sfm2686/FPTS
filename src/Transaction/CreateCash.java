package Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import Finance.Portfolio;
import Finance.CashAcct;

/**
 * This concrete command is responsible for creating a new cash account and
 * binding it to a portfolio.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class CreateCash extends Command implements Serializable {
	
	/****** Class Attributes ******/
	private String accountName;
	private double balance;

	
	/****** Class Methods ******/
	
	/**
	 * Constructor for a CreateCash object.
	 * 
	 * @param receiver The portfolio to add the cash account to.
	 * @param name The name of the new cash account.
	 * @param balance The initial balance of the new cash account.
	 */
	public CreateCash(Portfolio receiver, String name, double balance) {
		super(receiver);
		this.accountName = name;
		this.balance = balance;
	}

	/**
	 * Defined by the Command interface.
	 * 
	 * execute creates a cash account and adds it to the specified receiver.
	 * 
	 * @return A boolean value indicating the success of the command.
	 */
	@Override
	public boolean execute() {
		Portfolio receiver = super.getReceiver();
		if (receiver.hasCashAccount(this.accountName)) {
			return false;
		}
		receiver.addCashAccount(new CashAcct(this.accountName, this.balance));
		return true;
	}

	/**
	 * Generic toString method.
	 * 
	 * @return A string representation of this WithdrawCash command object.
	 */
	@Override
	public String toString() {
		return "Date: " + this.getTransactionDate() + "\nPortfolio Operated On: " + super.getReceiver().getName() + "\n\tAccount: " +
				this.accountName
				+ "\n\tTransaction: Created Cash Account" + "\n\tInitial Amount: " + this.balance;
	}

	/****** Leaf Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }
}
