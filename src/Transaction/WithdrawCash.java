package Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import Finance.Portfolio;
import Finance.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class represents the concert command of withdrawing from a cash
 *          account. The class takes a portfolio as a receiver and retrieves the
 *          cash account in the Receiver portfolio and subtracts the amount
 *          passed in the constructor.
 *
 */
public class WithdrawCash extends Command implements Serializable, UndoableRedoable {

	private String acctName;
	private double withdrawal;

	public WithdrawCash(Portfolio receiver, String acctName, double withdrawal) {
		super(receiver);
		this.acctName = acctName;
		this.withdrawal = withdrawal;
	}

	public boolean execute() {
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			if (acct.withdraw(this.withdrawal)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public void unexecute(){
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			acct.deposit(this.withdrawal);
		}
	}
	
	public UndoableRedoable copy(){
		return((UndoableRedoable)new WithdrawCash(this.getReciever(), this.acctName, this.withdrawal)); 
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + this.acctName
				+ "\n\tTransaction: Cash Withdrawal" + "\n\tAmount withdrew: " + this.withdrawal;
	}

	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
