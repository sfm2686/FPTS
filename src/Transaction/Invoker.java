package Transaction;

import TransactionStorage.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *			The Invoker class accepts newly created Command objects and executes them. 
 *			The invoker also determines whether or not the commands should be allowed to be 
 *			stored for undos/redos, and if so stores them in that class. Otherwise the commands
 *			are stored directly in the log.
 *
 */
public class Invoker {
	
	// Class attributes
	private Log log;
	private UndoRedo undoRedo;
	
	/**
	 * Constructor for an Invoker object.
	 * @param log The log in which non-undoableredoable commands will be stored.
	 * @param undoRedo The object in which undoableredoable commands will be stored.
	 */
	public Invoker(Log log, UndoRedo undoRedo){
		this.log = log;
		this.undoRedo = undoRedo;
	}
	
	/**
	 * invoke simply executes a command, checks the comman's type, 
	 * and passed it on to the appropriate command storage object.
	 * @param c The command to be invoked.
	 */
	public void invoke(Command c){
		if(c.execute()){
			if(c instanceof UndoableRedoable){
				UndoableRedoable command = (UndoableRedoable)c;
				this.undoRedo.add(command);
			}
			else{
				this.log.add(c);
			}
		}
	}
}
