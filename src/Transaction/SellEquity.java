/**
 * 
 */
package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SellEquity extends Command implements Serializable, UndoableRedoable {
	
	private ArrayList<Command> children;
	private String cashAcct;
	
	public SellEquity(Portfolio receiver, SubtractEquity subtraction, String cashAcct){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(subtraction);
		this.children.add(new DepositCash(receiver, cashAcct, subtraction.getTransactionValue()));
		this.cashAcct = cashAcct;
	}
	
	@Override
	public boolean execute() {
		for(Command c : this.children){
			if(!c.execute()){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void unexecute() {
		for(int i = this.children.size() - 1; i >= 0; i--){
			((UndoableRedoable)this.children.get(i)).unexecute();
		}
	}

	@Override
	public UndoableRedoable copy() {
		return (new SellEquity(this.getReciever(), (SubtractEquity)this.children.get(0), this.cashAcct));
	}

	@Override
	public void addChild(Command node) {
		this.children.add(node);
	}

	@Override
	public void removeChild(Command node) {
		this.children.remove(node);
	}


	@Override
	public ArrayList<Command> getChildren() {
		return(this.children);
	}


}