/**
 * 
 */
package Core;

import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *          This is the super class of all of the states. The super gives its
 *          subclasses private attrs that they can only access using getters in
 *          order to restrict access to them. This state also contains the same
 *          constructor that most of the states use. Some state have extra attrs
 *          they need to set so they have additions to this constructor This
 *          class only contains a number of concrete methods that are protected
 *          so they are only visible in the package of Core.
 */
public abstract class State {

	private Context context;

	// Next state index number in the look-up table
	private int next;
	private Scanner sc = new Scanner(System.in);

	public State(Context context) {
		this.context = context;
	}

	protected Context getContext() {
		return this.context;
	}

	protected int getNext() {
		return this.next;
	}

	protected void setNext(int next) {
		this.next = next;
	}

	protected Scanner getSc() {
		return this.sc;
	}

	protected boolean isValid(int b1, int b2, int val) {
		// Bounds [b1 , b2], val should be between them
		if (b1 == b2 && val == b1)
			return true;
		if (val < b1 || val > b2)
			return false;
		return true;
	}

	abstract void displayOptions();

	abstract void execute();

	abstract int getID();
}
