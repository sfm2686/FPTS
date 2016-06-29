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
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		this.displayOptions();
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
