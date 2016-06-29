/**
 * 
 */
package Core;

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
	} 

	
	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		
		/*
		 * Can go to:
		 * 1. S1
		 * 2. S16
		 * 3. S18
		 * 4. S19
		 */
		System.out.println("To go to: ");
		System.out.println("\t'Transactions' enter (1) ");
		System.out.println("\t'Simulation' enter (2)");
		System.out.println("\t'Log View' enter 3");
		System.out.println("\t'Logout' enter 0");
		super.setNext(super.getSc().nextInt() - 1);
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
