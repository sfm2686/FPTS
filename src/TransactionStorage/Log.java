package TransactionStorage;

import java.io.Serializable;
import java.util.*;
import Transaction.*; 
import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class is responsible for logging the (transaction) Command objects that are
 *          invoked onto the user's portfolios. This class stores a collection of type Command
 *          and lists (by date) them when needed. 
 */
public class Log implements Serializable { // Serializable in order to be persisted in the database.

	// Maintain a history of all of the (transaction) commands that a user invokes on their portfolios. 
	private ArrayList<Command> history;

	/**
	 * Constructor for the Log class, initialized the history class attribute.
	 */
	public Log() {
		this.history = new ArrayList<Command>();
	}

	/**
	 * Selection sort algorithm for ordering the log's collection of commands by
	 * their  dates in descending order. 
	 * 
	 * Algorithm skeleton looked up from Wikipedia.
	 */
	private void sort(){
		/* a[0] to a[n-1] is the array to sort */
		int i,j;

		/* advance the position through the entire array */
		/*   (could do j < n-1 because single element is also min element) */
		for (j = 0; j < this.history.size() - 1; j++) {
		    /* find the min element in the unsorted a[j .. n-1] */

		    /* assume the min is the first element */
		    int iMin = j;
		    /* test against elements after j to find the smallest */
		    for ( i = j+1; i < this.history.size(); i++) {
		        /* if this element is less, then it is the new minimum */
		        if (this.history.get(i).getTransactionDate().compareTo(this.history.get(iMin).getTransactionDate()) > 0) {
		            /* found new minimum; remember its index */
		            iMin = i;
		        }
		    }

		    if(iMin != j) {
		        this.swap(j, iMin);
		    }
		}
		return;
	}
	
	/**
	 * Helper function for the sorting method directly above.
	 * @param i The index of the first element to swap.
	 * @param j THe index of the second element to swap.
	 */
	private void swap(int i, int j){
		Command temp = this.history.get(j);
		this.history.set(j, this.history.get(i));
		this.history.set(i, temp);
	}
	
	/**
	 * For use by the Invoker of a Command object.
	 * @param command A command representing a transaction on a portfolio to be persisted.
	 */
	public void add(Command command){
		this.history.add(command);
	}
	
	/**
	 * For testing purposes.
	 */
	@Override
	public String toString(){
		this.sort(); // Sort before ever outputting
		String str = "\n";
		for(Command command : this.history){
			str += "\t" + command.toString() + ".\n";
		}
		return str;
	}
	
	/**
	 * Unit tests for Log
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		Portfolio port1 = new Portfolio("Port 1");
//		Portfolio port2 = new Portfolio("Port 2");
//		Command c1 = new CreateCash(port2, "cash 1", 10.0);
//		Thread.sleep(1000);
//		Command c2 = new CreateCash(port2, "cash 2", 10.0);
//		Thread.sleep(1000);
//		Command c3 = new CreateCash(port1, "cash 3", 10.0);
//		Thread.sleep(1000);
//		Command c4 = new CreateCash(port1, "cash 4", 10.0);
//		Thread.sleep(1000);
//		Command c5 = new CreateCash(port1, "cash 5", 10.0);
//		Thread.sleep(1000);
//		Command c6 = new CreateCash(port1, "cash 6", 10.0);
//		
//		Collection<Command> coll = new Stack<Command>();
//		coll.add(c5);
//		coll.add(c6);
//		
//		Log log = new Log();
//		log.add(c3);
//		log.add(c4);
//		log.add(c1);
//		log.add(c2);
//		
//		System.out.println(log.toString());
//		log.sort();
//		System.out.println(log.toString());	
		
		Portfolio port1 = new Portfolio("Port 1");
		Portfolio port2 = new Portfolio("Port 2");
		Command c1 = new CreateCash(port2, "cash 1", 10.0);
		Command c2 = new CreateCash(port2, "cash 2", 10.0);
		Command c3 = new CreateCash(port1, "cash 3", 10.0);
		Command c4 = new CreateCash(port1, "cash 4", 10.0);
		Command c5 = new CreateCash(port1, "cash 5", 10.0);
		Command c6 = new CreateCash(port1, "cash 6", 10.0);
		
		Collection<Command> coll = new Stack<Command>();
		coll.add(c5);
		coll.add(c6);
		
		Log log = new Log();
		log.add(c3);
		log.add(c4);
		log.add(c1);
		log.add(c2);
		
		System.out.println(log.toString());
		log.sort();
		System.out.println(log.toString());	
		
		String[] logA = log.toString().split(".");
		
		System.out.println(logA);
	}
}
