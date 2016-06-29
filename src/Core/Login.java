/**
 * 
 */
package Core;

import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Login extends State{

	
	//State number in the look-up table
	private int id = 0;
	 /**
	 * 
	 */
	public Login(Context context) {
		super(context);
		Scanner sc = new Scanner(System.in);
	} 

	
	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		
		/*
		 * Can go to:
		 * 1. S1
		 * 2. S2
		 */
		System.out.println("To go to: ");
		System.out.println("\t'Transactions' enter (1) ");
		System.out.println("\t'Logout' enter 0");
		int input = super.getSc().nextInt();
		if ( input != 0 )
			super.setNext(input - 1);
		else
			super.setNext(super.getContext().getTable()[this.id].length);
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n\nALL PORTFOLIO OWNED BY CURRENT USER DISPLAYED HERE\n\n");
		this.displayOptions();
	}

	/* (non-Javadoc)
	 * @see Core.State#transition()
	 */
	@Override
	int transition() {
		return super.getNext();
	}


	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		return this.id;
	}

}
