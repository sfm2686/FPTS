package Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import Finance.Portfolio;
import Finance.CashAcct;

/**
 * DepositCash is a concrete command responsible for adding funds to an existing cash account.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class DepositCash extends Command implements Serializable, UndoableRedoable {

	/****** Class Attributes ******/
	private String acctName;
	private double deposit;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a DepositCash command object.
	 * 
	 * @param receiver The portfolio containing the cash account.
	 * @param name The name of the cash account to add funds to.
	 * @param deposit The amount to deposit in the specified cash account.
	 */
	public DepositCash(Portfolio receiver, String name, double deposit) {
		super(receiver);
		this.acctName = name;
		this.deposit = deposit;
	}

	/**
	 * execute is defines by the command interface.
	 * 
	 * execute retrieves the given cash account, and adds the specified 
	 * funds to that account.
	 * 
	 * @return A boolean value indicating the success of the command.
	 */
	@Override
	public boolean execute() {
		CashAcct acct = super.getReceiver().getCashAcct(this.acctName);
		if (acct != null) {
			acct.deposit(this.deposit);
			return true;
		}
		return false;
	}

	/**
	 * unexecute is defined by the UndoableRedoable interface
	 * 
	 * unexecute reverses the effect of the execute method.
	 */
	@Override
	public void unexecute() {
		CashAcct acct = super.getReceiver().getCashAcct(this.acctName);
		if (acct != null) {
			acct.withdraw(this.deposit);
		}
	}
	
	/**
	 * copy is defined by the UndoableRedoable interface.
	 * 
	 * copy returns a 'clone' of this command object.
	 */
	@Override
	public UndoableRedoable copy() {
		return (new DepositCash(super.getReceiver(), this.acctName, this.deposit));
	}
	
	/**
	 * Generic toString method.
	 * 
	 * @return The string representation of this object.
	 */
	@Override
	public String toString() {
		return "Date: " + this.getTransactionDate() + "\n\tPortfolio Operated On: " + super.getReceiver() + "\n\tAccount: " + this.acctName
				+ "\n\tTransaction: Deposit Cash" + "\n\tAmount: " + this.deposit;
	}

	/**
	 * Specialized toString method for depositing cash.
	 * 
	 *  @return The String representation of this lead command.
	 */
	public String leafToString(){
		return("Deposit Transaction, Portfolio: " + this.getReceiver() + ", "
				+ "Account: " + this.acctName + ", Amount: " + this.deposit);
	}
	
	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
