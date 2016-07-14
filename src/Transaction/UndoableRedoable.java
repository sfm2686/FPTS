/**
 * 
 */
package Transaction;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public interface UndoableRedoable {
	
	public abstract void unexecute();
	
	public abstract UndoableRedoable copy();

}
