package Transaction;

import Finance.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class takes a portfolio as a receiver and removes all of the
 *          shares of equities that has the passed in string as their name in
 *          the receiver.
 *
 */
public class RemoveEquity extends Command implements Serializable {

	private String equityName;
	
	public RemoveEquity(Portfolio receiver, String equityName) {
		super(receiver);
		this.equityName = equityName;
	}

	public boolean execute() {
		if (super.getReciever().hasEquity(this.equityName)) {
			Equity removal = null;
			for (Equity equity : super.getReciever().getEquities()) {
				if (equity.getName().equalsIgnoreCase(this.equityName)) {
					removal = equity;
					break;
				}
			}
			super.getReciever().removeEquity(removal);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Removed Equity";
	}

	/****** Leaf Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
