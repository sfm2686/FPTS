package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.*;

/**
 * SellEquity is a macro-command responsible for subtracting shares from one equity 
 * and adding the funds gained to a given cash account.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class SellEquity extends Command implements Serializable, UndoableRedoable {
	
	/****** Class Attributes ******/
	private ArrayList<Command> children;
	private String cashAcct;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor for a SellEquity object.
	 * 
	 * @param receiver The portfolio containing the cash account to deposit the profits from the equity sale to. 
	 * @param subtraction A command object that specifies the details of the subtraction from the equity.
	 * @param cashAcct The name of the cash account to add the funds from the sale of the equity to.
	 */
	public SellEquity(Portfolio receiver, SubtractEquity subtraction, String cashAcct){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(subtraction);
		this.children.add(new DepositCash(receiver, cashAcct, subtraction.getTransactionValue()));
		this.cashAcct = cashAcct;
	}
	
	/**
	 * execute is defined by the Command interface.
	 * 
	 * execute simply cycles through all of the commands in the composite relationship 
	 * and executes each based on the success of the previous.
	 * 
	 * @return A boolean indicating the success of this macro-command.
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
	 * unexecute is defined by the UndoableRedoable interface.
	 * 
	 * unexecute simply reverses the effect of the execute method.
	 */
	@Override
	public void unexecute() {
		for(int i = this.children.size() - 1; i >= 0; i--){
			((UndoableRedoable)this.children.get(i)).unexecute();
		}
	}

	/**
	 * copy is defined by the UndoableRedoable interface.
	 * 
	 * @return A 'clone' of this command object.
	 */
	@Override
	public UndoableRedoable copy() {
		return (new SellEquity(this.getReceiver(), (SubtractEquity)this.children.get(0), this.cashAcct));
	}
	
	/**
	 * Generic toString method.
	 * 
	 *  @return The String representation of this transaction command object.
	 */
	public String toString(){
		return "Date: " + this.getTransactionDate() + "\n\tTransaction: Equity Liquidated to a Cash Account, " + 
						((SubtractEquity)this.children.get(0)).leafToString() +
						((DepositCash)this.children.get(1)).leafToString(); 

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
