package Transaction;

import Finance.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * RemoveEquity is responsible for removing a specified equity from a given portfolio.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class RemoveEquity extends Command implements Serializable {

	/****** Class Attributes ******/
	private String equityName;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor for a RemoveEquity command object.
	 * 
	 * @param receiver The portfolio to remove the specified equity from.
	 * @param equityName The name of the equity to be removed.
	 */
	public RemoveEquity(Portfolio receiver, String equityName) {
		super(receiver);
		this.equityName = equityName;
	}

	/**
	 * execute if defined by the Command interface.
	 * 
	 * Command simply retrieves the specified equity object, if it exists, from the given
	 * portfolio and removes it.
	 * 
	 * @return A boolean indicatin the success of this command.
	 */
	public boolean execute() {
		if (super.getReceiver().hasEquity(this.equityName)) {
			Equity removal = null;
			for (Equity equity : super.getReceiver().getEquities()) {
				if (equity.getName().equalsIgnoreCase(this.equityName)) {
					removal = equity;
					break;
				}
			}
			super.getReceiver().removeEquity(removal);
			return true;
		}
		return false;
	}

	/**
	 * A generic toString method.
	 * 
	 * @return The string representation of this command object.
	 */
	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReceiver() + "\n\tEquity: " + this.equityName
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
