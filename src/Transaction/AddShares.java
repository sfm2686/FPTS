package Transaction;

import Finance.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import Market.Market;

/**
 * AddShares has functionality for adding shares to an existing equity as well as creating a new
 * equity into a portfolio.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class AddShares extends Command implements Serializable, UndoableRedoable {

	/****** Class Attributes *****/
	
	private String equityName;
	private int numShares;
	private Date date;

	/****** Class Mehods *****/
	
	/**
	 * Constructor for an AddShares command object.
	 * 
	 * @param receiver The portfolio that we the command is operating on.
	 * @param name The name of the equity being added to.
	 * @param shares The number of shares to add to the given equity.
	 * @param acquitionDate The date that the equity shares were acquired.
	 */
	public AddShares(Portfolio receiver, String name, int shares, Date acquitionDate) {
		super(receiver);
		this.equityName = name;
		this.numShares = shares;
		this.date = acquitionDate;
	}

	/**
	 * execute is defined by the Command interface.
	 * 
	 * execute will add the number of shares to the given equity in the 
	 * given portfolio. If the equity does not yet exist within the portfolio
	 * then one will be created with the specified number of shares.
	 * 
	 * @return A boolean indicating the success of the command.
	 */
	@Override
	public boolean execute() {
		Equity equity = super.getReceiver().getEquity(this.equityName);
		// If the equity is already in the portfolio, add to it.
		if (equity != null) {
			equity.addShares(this.numShares);
			return true;
		}
		// Otherwise create an appropriate equity using Market
		else{
			if(Market.getMarketInstance().isIndex(this.equityName))
				equity = new Index(this.numShares, this.equityName);
			else if(Market.getMarketInstance().isStock(this.equityName))
				equity = new Stock(this.numShares, this.equityName);
			if(equity != null){
				this.getReceiver().addEquity(equity);
				Market.getMarketInstance().addUpdateEquity(equity);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * unexcute is defined by the UndoableRedoable interface.
	 * 
	 * unexecute simply reverses the operations performed in execute. 
	 */
	@Override
	public void unexecute() {
		Equity equity = super.getReceiver().getEquity(this.equityName);
		if (equity != null) {
			equity.subtractShares(this.numShares);
			if(equity.getNumShares() == 0){
				this.getReceiver().removeEquity(equity);
			}
		}
	}

	/**
	 * copy is defined by the UndoableRedoable interface.
	 * 
	 * copy returns a 'clone' of the command.
	 * 
	 * @return A copy of this command.
	 */
	@Override
	public UndoableRedoable copy() {
		return(new AddShares(this.getReceiver(), this.equityName, this.numShares, this.date));
	}
	
	/**
	 * getTransactionValue is used by macro command that are
	 * comprised of AddShare commands.
	 * 
	 * @return The total cost of performing this command.
	 */
	public double getTransactionValue(){
		if (Market.getMarketInstance().isStock(this.equityName))
			return(this.numShares * Market.getMarketInstance().getPrice(this.equityName));
		else if(Market.getMarketInstance().isIndex(this.equityName))
			return(this.numShares * Market.getMarketInstance().getIndexPrice(this.equityName));
		else
			return(0.0);
	}
	
	/**
	 * Generic toString method.
	 */
	@Override
	public String toString() {
		return "\nDate: " + this.getTransactionDate() + "\n\tPortfolio Operated On: " + super.getReceiver() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Add Equity" + "\n\tShares: " + this.numShares
				+ "\n\tAcquisition Date: " + this.date;
	}
	
	/**
	 * Specialized toString method for depositing cash.
	 * 
	 *  @return The String representation of this leaf command.
	 */
	public String leafToString(){
		return("Add Shares Transaction, Portfolio: " + this.getReceiver() + ", "
				+ "Equity: " + this.equityName + ", Number of Shares: " + this.numShares);
	}

	/****** Leaf Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }

}
