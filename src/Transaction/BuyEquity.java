package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class BuyEquity extends Command implements Serializable, UndoableRedoable {
	
	private ArrayList<Command> children;
	private String cashAcct;
	
	public BuyEquity(Portfolio receiver, AddEquity addition, String cashAcct){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(new WithdrawCash(receiver, cashAcct, addition.getTransactionValue()));
		this.children.add(addition);
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
		return (new BuyEquity(this.getReciever(), (AddEquity)this.children.get(0), this.cashAcct));
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
