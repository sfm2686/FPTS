package Transaction;

import TransactionStorage.*;

/**
 * The Invoker class accepts newly created Command objects and executes them. 
 * 
 * The Invoker is implemented as a GOF Singleton, with a modification 'tracks' the 
 * user that is currently logged in with its getInstance() implementation.
 * 
 * The invoker also determines whether or not the commands should be allowed to be 
 * stored for undos/redos, and if so stores them in that class. Otherwise the commands
 * are stored directly in the log.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public class Invoker {
	
	/****** Class Attributes ******/
	private Log log;
	private UndoRedo undoRedo;
	private static Invoker instance = null; // Required for Singleton implementation.
	
	/****** Class Methods ******/
	
	/**
	 * Private constructor for an Invoker object.
	 * 
	 * @param log The log in which non-undoableredoable commands will be stored.
	 */
	private Invoker(Log log){
		this.log = log;
		this.undoRedo = new UndoRedo();
	}
	
	/**
	 * Provide global point of access for the Singleton invoker object.
	 * 
	 * @param Log The user's log object that the invoker should send non-undoable commands to.
	 * @return The one and only instance of Invoker.
	 */
	public static Invoker getInvoker(Log log){
		if (instance == null){
			instance = new Invoker(log);
		}
		if(instance.log != log){ // Ensure that the invoker is passing to the correct log
			instance.undoRedo.clean(instance.log); // If we had to change logs, then clean undoRedo;
			instance.log = log;
		}
		return instance;
	}
	
	/**
	 * Accessor for the invoker's undoRedo stack.
	 * @return The invoker's undoRedo stack.
	 */
	public UndoRedo getUndoRedoStack(){
		return (this.undoRedo);
	}
	
	/**
	 * invoke simply executes a command, checks the command's type, 
	 * and passed it on to the appropriate command storage object.
	 * 
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
