/**
 * 
 */
package Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import Finance.Equity;
import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class subtracts shares from an equity that belongs to the
 *          receiver portfolio that has the same name as the passed in string.
 *          Also the number of shares is provided.
 *
 */
public class SubtractEquity extends Command implements Serializable, UndoableRedoable {

	private String equityName;
	private int numShares;

	public SubtractEquity(Portfolio receiver, String equityName, int numShares) {
		super(receiver);
		this.equityName = equityName;
		this.numShares = numShares;
	}

	@Override
	public boolean execute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.subtractShares(this.numShares);
			return true;
		}
		return false;
	}

	@Override
	public void unexecute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.addShares(this.numShares);
		}
	}
	
	@Override
	public UndoableRedoable copy() {
		return(new SubtractEquity(this.getReciever(), this.equityName, this.numShares));
	}
	
	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Subtract Equity Shares" + "\n\tShares Removed: " + this.numShares;
	}
	
	/****** Leaf Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }


	}

}
