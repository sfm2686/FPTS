/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Logout extends State {
	
	private int id = 13;

	/**
	 * @param context
	 */
	public Logout(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		System.out.println("Are you sure you wish to logout and quit?");
		System.out.println("(yes, no)");
		System.out.print("Taking input: ");
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Logout-----\n");
		this.displayOptions();
		String input = super.getSc().next();
		while ( !(input.equalsIgnoreCase("yes") && input.equalsIgnoreCase("no" )) ){
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			input = super.getSc().next();
		}
		if ( super.getSc().next().equalsIgnoreCase("yes") ){
			System.out.println("System exiting....");
			System.exit(1);
		}
		else
			super.setNext(0);
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
	
	@Override
	public String toString(){
		return "This is state number #13, LOGOUT";
	}

}
