/**
 * 
 */
package Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import Finance.Portfolio;
import Finance.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This concrete is responsible for creating a new cash accounts and
 *          binding it to the portfolio that is passed in the constructor.
 *
 */
public class CreateCash extends Command implements Serializable {

	private String accountName;
	private double balance;

	public CreateCash(Portfolio receiver, String name, double balance) {
		super(receiver);
		this.accountName = name;
		this.balance = balance;
	}

	public boolean execute() {
		Portfolio receiver = super.getReciever();
		if (receiver.hasCashAccount(this.accountName)) {
			return false;
		}
		receiver.addCashAccount(new CashAcct(this.accountName, this.balance));
		return true;
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tAccount: " + this.accountName
				+ "\n\tTransaction: Created Cash Account" + "\n\tInitial Amount: " + this.balance;
	}

	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }
}
