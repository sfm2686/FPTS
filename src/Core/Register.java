/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Register extends State {
	
	private int id = 1;

	/**
	 * @param context
	 */
	public Register(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		
		/*
		 * Can go to:
		 * 1. S2
		 * 2. S4
		 * 3. S10
		 * 4. S13
		 * 5. S15
		 * 6. S19
		 */
		System.out.println("To go to: ");
		System.out.println("\t'Transactions' enter (1) ");
		System.out.println("\t'Simulation' enter (2)");
		System.out.println("\t'Log View' enter 3");
		System.out.println("\t'Quit' enter 0");
		super.setNext(super.getSc().nextInt() - 1);
		
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		
		System.out.println();
		System.out.println("Printed values belew is for testing:");
		
		for ( int i = 0; i < 5; i ++ )
			System.out.println("Equitiy " + i + ": name .. price ..");
	}

	/* (non-Javadoc)
	 * @see Core.State#transition()
	 */
	@Override
	int transition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		return this.id;
	}

}
