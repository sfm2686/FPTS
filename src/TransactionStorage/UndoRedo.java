package TransactionStorage;

import Transaction.*;
import java.util.*;
import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * 			The UndoRedo class is responsible for keeping track of transaction commands that can be re-executed or un-executed.
 * 			The class supports functionality for saving all of the content's of this class' stack to a log associated with
 * 			a user. At that point the transactions are considered permanent and will be persisted in the system.
 */
public class UndoRedo {

	// Stack to store commands (transactions) in order to implement undo/redo functionality.
	private Stack<UndoableRedoable> stack;
	
	/**
	 * Constructor for the UndoRedo class.
	 */
	public UndoRedo(){
		this.stack = new Stack<UndoableRedoable>();
	}
	
	/**
	 * Adds a single element to the top of the stack.
	 * @param command A command to be saved to the stack.
	 */
	public void add(UndoableRedoable command){
		this.stack.push(command);
	}
	
	/**
	 * Pops the top element of the stack and reverses the command's effect by calling unexecute.
	 * 
	 * @see UndoableRedoable
	 */
	public void undo(){
		if (this.stack.isEmpty())
			return;
		UndoableRedoable last = this.stack.pop();
		last.unexecute();
	}
	
	/**
	 * Creates a clone of the top element of the stack, executes it, 
	 * and pushed the newly created clone back onto the stack.
	 * 
	 * @see UndoableRedoable
	 */
	public void redo(){
		if (this.stack.isEmpty())
			return;
		Command clone = (Command)this.stack.peek().copy();
		clone.execute();
		this.stack.add((UndoableRedoable)clone);
	}
	
	/**
	 * Forwards all of the contents of the stack to the log; refreshed the stack with a new instance. 
	 * @param log A log that is associated with a user.
	 */
	public void clean(Log log){
		for(UndoableRedoable item : this.stack){
			log.add((Command)item);
		}
		this.stack = new Stack<UndoableRedoable>();
	}
	
	/**
	 * For testing purposes.
	 */
	@Override
	public String toString(){
		if(this.stack.isEmpty()){
			return("Stack is empty.");
		}
		String str = "Current contents of stack:\n";
		for(UndoableRedoable item : this.stack){
			str += "\t" + ((Command)item).toString() + "\n\n";
		}
		return str;
	}
	
	/**
	 * Unit tests for UndoRedo
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Portfolio port1 = new Portfolio("Port 1");
		
		CashAcct account1 = new CashAcct("Cash Account1", 50.0);
		CashAcct account2 = new CashAcct("Cash Account2", 100.0);

		UndoableRedoable c1 = new DepositCash(port1, "Cash Account1", 50.0);
		UndoableRedoable c2 = new DepositCash(port1, "Cash Account2", 100.0);
		UndoableRedoable c3 = new DepositCash(port1, "Cash Account1", 500.0);
		UndoableRedoable c4 = new DepositCash(port1, "Cash Account2", 1000.0);
		
		UndoRedo test = new UndoRedo();
		Log log = new Log();
		
		System.out.println("========== Testing the Redo Functionality. ==========\n");
		System.out.println(test.toString());
		System.out.println("Adding a command to the stack...");
		test.add(c1);
		System.out.println(test.toString());
		System.out.println("Calling redo on the stack...");
		test.redo();
		System.out.println(test.toString());
		test.clean(log);
		System.out.println("Cleaning the stack...\n");
		System.out.println(test.toString());	
		System.out.println("\nPrinting the log...");
		System.out.println(log.toString());
		System.out.println("========== Done Testing the Redo Functionality ==========\n");
		
		System.out.println("========== Testing the Undo Functionality. ==========\n");
		log = new Log();
		System.out.println(test.toString());
		System.out.println("Adding a command to the stack...");
		test.add(c1);
		System.out.println(test.toString());
		System.out.println("Calling undo on the stack...");
		test.undo();
		System.out.println(test.toString());
		test.clean(log);
		System.out.println("Cleaning the stack...\n");
		System.out.println(test.toString());	
		System.out.println("\nPrinting the log...");
		System.out.println(log.toString());
		System.out.println("========== Done Testing the Undo Functionality ==========\n");
	}
	
}
