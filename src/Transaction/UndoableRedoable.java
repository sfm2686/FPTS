/**
 * 
 */
package Transaction;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public interface UndoableRedoable {
	
	public void unexecute();
	
	public UndoableRedoable clone();

}
