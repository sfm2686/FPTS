/**
 * 
 */
package Transaction;

import java.util.ArrayList;
import java.io.Serializable;
import Finance.Portfolio;
import Finance.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CashTransfer extends Command implements Serializable, UndoableRedoable {

	private ArrayList<Command> children;
	private Command withdraw;
	private Command deposit;
	
	
	public CashTransfer(Portfolio receiver, Command withdraw, Command deposit){
		super(receiver);
		this.children = new ArrayList<Command>();
		this.children.add(withdraw);
		this.children.add(deposit);
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
	public void unexecute(){
		for(int i = this.children.size() - 1; i >= 0; i--){
			((UndoableRedoable)this.children.get(i)).unexecute();
		}
	}
	
	@Override
	public UndoableRedoable copy() {
		return (new CashTransfer(this.getReciever(), this.children.get(0), this.children.get(1)));
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
