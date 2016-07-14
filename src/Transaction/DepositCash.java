package Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import Finance.Portfolio;
import Finance.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This is a concrete object of transaction. This can be viewed as a
 *          microCommand however it does not employ the Composite pattern. This
 *          class is only responsible for adding a number of cash to a cash
 *          account.
 *
 */
public class DepositCash extends Command implements Serializable, UndoableRedoable {

	private String acctName;
	private double deposit;

	public DepositCash(Portfolio receiver, String name, double deposit) {
		super(receiver);
		this.acctName = name;
		this.deposit = deposit;
	}

	@Override
	public boolean execute() {
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			acct.deposit(this.deposit);
			return true;
		}
		return false;
	}

	@Override
	public void unexecute() {
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			acct.withdraw(this.deposit);
		}
	}

	@Override
	public String toString() {
		return "Date: " + this.getTransactionDate() + "\n\tPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + this.acctName
				+ "\n\tTransaction: Deposit Cash" + "\n\tAmount: " + this.deposit;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public UndoableRedoable copy() {
		return (new DepositCash(super.getReciever(), this.acctName, this.deposit));
	}
	
	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
