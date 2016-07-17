package Transaction;

import java.io.Serializable;
import java.util.ArrayList;

import Finance.Equity;
import Finance.Portfolio;
import Market.Market;

/** 
 * SubtractEquity is responsible for removing a specified number of shares from an equity.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class SubtractEquity extends Command implements Serializable, UndoableRedoable {

	/****** Class Attributes ******/
	private String equityName;
	private int numShares;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a SubtractEquity object.
	 * 
	 * @param receiver The portfolio containing the equity to subtract shares from.
	 * @param equityName The name of the equity to subtract shares from.
	 * @param numShares The number of shares to be removed from the specified equity.
	 */
	public SubtractEquity(Portfolio receiver, String equityName, int numShares) {
		super(receiver);
		this.equityName = equityName;
		this.numShares = numShares;
	}
	
	/**
	 * execute is defined by the Command interface.
	 * 
	 * execute simply retrieves an equity from the given portfolio, if it exists, 
	 * and subtracts the specified number of shares from it.
	 * 
	 * @return A boolean indicating the success of the command.
	 */
	@Override
	public boolean execute() {
		Equity equity = super.getReceiver().getEquity(this.equityName);
		if (equity != null) {
			equity.subtractShares(this.numShares);
			return true;
		}
		return false;
	}

	/**
	 * unexecute is defined by the UndoableRedoable interface.
	 * 
	 * unexecute simply reverses the effect of the execute method.
	 */
	@Override
	public void unexecute() {
		Equity equity = super.getReceiver().getEquity(this.equityName);
		if (equity != null) {
			equity.addShares(this.numShares);
		}
	}
	
	/**
	 * copy is defined by the UndoableRedoable interface.
	 * 
	 * @return A 'clone' of this command.
	 */
	@Override
	public UndoableRedoable copy() {
		return(new SubtractEquity(this.getReceiver(), this.equityName, this.numShares));
	}
	
	/**
	 * getTransactionValue calculates the total amount of money gained by selling the 
	 * shares from the given equity.
	 * 
	 * @return A double indicating the profits from 'selling' off part of an equity.
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
	 * A generic toString method
	 * 
	 * @return The string representation of this command object.
	 */
	@Override
	public String toString() {
		return "\nPortfolio Operated On: " + super.getReceiver() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Subtract Equity Shares" + "\n\tShares Removed: " + this.numShares;
	}
	
	/**
	 * Specialized toString method for depositing cash.
	 * 
	 *  @return The String representation of this lead command.
	 */
	public String leafToString(){
		return("Subtract Equity Transaction, Portfolio: " + this.getReceiver() + ", "
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
