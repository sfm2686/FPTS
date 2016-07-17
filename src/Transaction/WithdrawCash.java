package Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import Finance.Portfolio;
import Finance.CashAcct;

/**
 * WithdrawCash has the responsibility of removing a specified amount of funds from
 * a given cash account.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class WithdrawCash extends Command implements Serializable, UndoableRedoable {

	/****** Class Attributes ******/
	private String acctName;
	private double withdrawal;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a WithdrawCash command object.
	 * @param receiver The portfolio containing the cash account to withdraw from.
	 * @param acctName The name of the cash account to withdraw from.
	 * @param withdrawal The amount to withdraw from the cash account.
	 */
	public WithdrawCash(Portfolio receiver, String acctName, double withdrawal) {
		super(receiver);
		this.acctName = acctName;
		this.withdrawal = withdrawal;
	}

	/**
	 * execute is defined by the Command interface. 
	 * 
	 * execute simply retrieves the cash account with the supplied information, and if
	 * it indeed exists, then subtracts funds from its balance.
	 * 
	 * @return A boolean value indicating the success of the command.
	 */
	public boolean execute() {
		CashAcct acct = super.getReceiver().getCashAcct(this.acctName);
		if (acct != null) {
			if (acct.withdraw(this.withdrawal)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * unexecute is defined by the UndoableRedoable interface.
	 * 
	 * unexecute simply reverses the effect of the execute method.
	 */
	public void unexecute(){
		CashAcct acct = super.getReceiver().getCashAcct(this.acctName);
		if (acct != null) {
			acct.deposit(this.withdrawal);
		}
	}
	
	/**
	 * copy is defined by the UndoableRedoable interface.
	 * 
	 * @return A 'clone' of the current command object.
	 */
	public UndoableRedoable copy(){
		return((UndoableRedoable)new WithdrawCash(this.getReceiver(), this.acctName, this.withdrawal)); 
	}

	/**
	 * Generic toString method.
	 * 
	 * @return A string representation of this WithdrawCash command object.
	 */
	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReceiver() + "\n\tAccount: " + this.acctName
				+ "\n\tTransaction: Cash Withdrawal" + "\n\tAmount withdrew: " + this.withdrawal;
	}
	

	/**
	 * Specialized toString method for withdrawing cash.
	 * 
	 *  @return The String representation of this lead command.
	 */
	public String leafToString(){
		return("Withdraw Transaction, Portfolio: " + this.getReceiver() + ", "
				+ "Account: " + this.acctName + ", Amount: " + this.withdrawal);
	}

	/****** Leaf Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
