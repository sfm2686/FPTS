package Transaction;

/**
 * Interface to enforce which commands shall be allowed to undo and redo their 
 * associated operations. 
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public interface UndoableRedoable {
	
	/**
	 * unexecute will effectively reverse the consequences of a comman's execute command.
	 * This method will allow for undo functionality.
	 */
	public abstract void unexecute();
	
	/**
	 * copy will allow a command to be copied so they can be reexecuted while still preserving 
	 * This method will allow for redo functionality.
	 */
	public abstract UndoableRedoable copy();

}
