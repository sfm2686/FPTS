/**
 * 
 */
package Core;

import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class State {
	
	private Context context;
	
	//Next state index number in the look-up table
	private int next;
	private Scanner sc = new Scanner(System.in);
	
	public State(Context context){
		this.context = context;
	}
	
	protected Context getContext(){
		return this.context;
	}
	
	protected int getNext(){
		return this.next;
	}
	
	protected void setNext(int next){
		this.next = next;
	}
	
	protected Scanner getSc(){
		return this.sc;
	}
	
	protected boolean isValid(int b1, int b2, int val){
		//Bounds [b1 , b2], val should be between them
		if ( val < b1 || val > b2 )
			return false;
		return true;
	}
	
	abstract void displayOptions();
	abstract void execute();
	abstract int transition();
	abstract int getID();
}
