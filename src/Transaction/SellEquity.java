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
	
	private Command subtraction;
	private String cashAcct;
	
	public SellEquity(Portfolio receiver, Command subtration, String cashAcct){
		super(receiver);
		this.subtraction = subtraction;
		this.cashAcct = cashAcct;
		
	}
	
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UndoableRedoable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(Command node) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeChild(Command node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Command> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
