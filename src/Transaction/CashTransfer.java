package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class CashTransfer extends Command implements Serializable, UndoableRedoable {

	/****** Class Attributes ******/
	
	private ArrayList<Command> children;

	
	/****** Class Methods ******/

	/**
	 * Constructor for a CashTransfer command object.
	 * 
	 * @param receiver Null in this case
	 * @param withdraw The command object specifying a withdrawal from a cash account.
	 * @param deposit The command object specifying a deposit to a cash account.
	 */
	public CashTransfer(Portfolio receiver, Command withdraw, Command deposit){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(withdraw);
		this.children.add(deposit);
	}
	
	/**
	 * execute is defined by the Command interface.
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
	 * unexecute is defined by the UndoableRedoable interface.
	 * 
	 * unexecute simply reverses the effect of the execute command.
	 */
	@Override
	public void unexecute(){
		for(int i = this.children.size() - 1; i >= 0; i--){
			((UndoableRedoable)this.children.get(i)).unexecute();
		}
	}
	
	/**
	 * copy is defined by the UndoabeRedoable interface.
	 * 
	 * @return A copy of this CashTransfer object.
	 */
	@Override
	public UndoableRedoable copy() {
		return (new CashTransfer(this.getReceiver(), this.children.get(0), this.children.get(1)));
	}
	
	/**
	 * Generic toString method.
	 * 
	 * @return A string representation of this WithdrawCash command object.
	 */
	@Override
	public String toString(){
		return "Date: " + this.getTransactionDate() + "\n\tTransaction: Conducted Cash Transfer, " + 
						 ((WithdrawCash)this.children.get(0)).leafToString() + 
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
