package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.*;

/**
 * BuyEquity has functionality for adding shares to an existing or new equity, removing the 
 * requisite funds from a cash account.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class BuyEquity extends Command implements Serializable, UndoableRedoable {
	
	/****** Class Attributes *****/
	
	private ArrayList<Command> children; // To implement composite functionality.
	private String cashAcct;
	
	/****** Class Methods *****/
	
	/**
	 * Constructor for a BuyEquity command object. 
	 * 
	 * @param receiver The portfolio in which the cash account is held.
	 * @param addition A command object that has the details of the equity addition encapsulated.
	 * @param cashAcct The name of the cash account to extract funds from.
	 */
	public BuyEquity(Portfolio receiver, AddShares addition, String cashAcct){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(new WithdrawCash(receiver, cashAcct, addition.getTransactionValue()));
		this.children.add(addition);
		this.cashAcct = cashAcct;
	}
	
	/**
	 * Defined by the Command interface.
	 * 
	 * Cycles through the collection of (leaf) commands,
	 * executing them all upon success of the previous.
	 * 
	 * @return A boolean value indicating the success of the command.
	 */
	@Override
	public boolean execute() {
		for(Command c : this.children){
			if(!c.execute()){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Defined by the UndoableRedoable interface.
	 * 
	 * Reverses the effect of the execute method.
	 */
	@Override
	public void unexecute() {
		for(int i = this.children.size() - 1; i >= 0; i--){
			((UndoableRedoable)this.children.get(i)).unexecute();
		}
	}

	/**
	 * Defined by the UndoableRedoable interface.
	 * 
	 * @return A copy of this command.
	 */
	@Override
	public UndoableRedoable copy() {
		return (new BuyEquity(this.getReceiver(), (AddShares)this.children.get(0), this.cashAcct));
	}
	
	/**
	 * Generic toString method.
	 * 
	 * @return A string representation of this BuyEquity command object.
	 */
	@Override
	public String toString(){
		return "Date: " + this.getTransactionDate() + "\n\tTransaction: Equity Bought with a Cash Account, " + 
						 ((WithdrawCash)this.children.get(0)).leafToString() + 
						 ((AddShares)this.children.get(1)).leafToString();
	}
	
	/**
	 * Defined by the Command interface.
	 * 
	 * Add a leaf command to the composite structure of this macro command.
	 */
	@Override
	public void addChild(Command node) {
		this.children.add(node);
	}

	/**
	 * Defined by the Command interface.
	 * 
	 * Remove a leaf command from the composite structure of this macro command.
	 */
	@Override
	public void removeChild(Command node) {
		this.children.remove(node);
	}

	/**
	 * Defined by the Command interface.
	 * 
	 * Return a collection of the child commands from this macro-commands.
	 */
	@Override
	public ArrayList<Command> getChildren() {
		return(this.children);
	}
}
