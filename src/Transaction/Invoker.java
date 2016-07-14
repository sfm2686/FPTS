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
	private static Invoker instance = null;
	
	/**
	 * Private constructor for an Invoker object.
	 * @param log The log in which non-undoableredoable commands will be stored.
	 * @param undoRedo The object in which undoableredoable commands will be stored.
	 */
	private Invoker(){
		this.log = new Log();
		this.undoRedo = new UndoRedo();
	}
	
	/**
	 * Provide global point of access for the Singleton invoker object.
	 * @return The one and only instance of Invoker.
	 */
	public static Invoker getInvoker(){
		if (instance == null){
			instance = new Invoker();
		}
		return instance;
	}
	
	/**
	 * Extension of the Singleton pattern.
	 * Clear the instance, aids in resetting the user session upon logout.
	 */
	public static void clearInvoker(){
		instance = null;
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
