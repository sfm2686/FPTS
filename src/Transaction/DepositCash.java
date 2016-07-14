package Transaction;

import java.io.Serializable;

import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This is a concrete object of transaction. This can be viewed as a
 *          microCommand however it does not employ the Composite pattern. This
 *          class is only responsible for adding a number of cash to a cash
 *          account.
 *
 */
public class AddCash extends Command implements Serializable, UndoableRedoable {

	private String acctName;
	private double deposit;

	public AddCash(Portfolio receiver, String name, double deposit) {
		super(receiver);
		this.acctName = name;
		this.deposit = deposit;
	}

	public boolean execute() {
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			acct.deposit(this.deposit);
			return true;
		}
		return false;
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

	/**
	 * 
	 */
	@Override
	public void unexecute() {
		CashAcct acct = super.getReciever().getCashAcct(this.acctName);
		if (acct != null) {
			acct.withdraw(this.deposit);
		}
	}

	/**
	 * 
	 */
	@Override
	public UndoableRedoable clone() {
		return ((UndoableRedoable) new AddCash(super.getReciever(), this.acctName, this.deposit));
	}
}
