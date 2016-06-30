/**
 * 
 */
package Core;

import DataInterface.DBInterface;

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
		System.out.println("Logging out will save all changes to portfolios."
				+ "Are you sure you wish to logout?");
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
		String input = getSc().next();
		while ( !(input.equalsIgnoreCase("yes")) && !(input.equalsIgnoreCase("no")) ){
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			input = getSc().next();
		}
		if ( input.equalsIgnoreCase("yes") ){
			DBInterface.saveUserData(getContext().getUser());
			System.out.println("System exiting....");
			System.exit(1);
		}
		else
			setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}
	
	@Override
	public String toString(){
		return "This is state number #13, LOGOUT";
	}

}
