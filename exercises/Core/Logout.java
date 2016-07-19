/**
 * 
 */
package Core;

import DataInterface.DBInterface;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *          This state's main purpose is to log the user out of the system and
 *          shut down the system. Before doing anything the state asks the user
 *          if they are sure they would like to logout, if they answer yes then
 *          the state calls the DBInterface in order to save any changes they
 *          might have made in case they forgot to save those changes, and
 *          closes the system.
 */
public class Logout extends State {

	private int id = 13;

	/**
	 * @param context
	 */
	public Logout(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		System.out.println("Logging out will save all changes to portfolios." + "Are you sure you wish to logout?");
		System.out.println("(yes, no)");
		System.out.print("Taking input: ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Logout-----\n");
		this.displayOptions();
		String input = getSc().next();
		while (!(input.equalsIgnoreCase("yes")) && !(input.equalsIgnoreCase("no"))) {
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			input = getSc().next();
		}
		if (input.equalsIgnoreCase("yes")) {
			if (DBInterface.saveUserData(getContext().getUser()))
				System.out.println("All changes saved successfully");
			else
				System.out.println("Could not save changes, plaese contact customer service");
			System.out.println("System exiting....");
			System.exit(1);
		} else
			setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}

	@Override
	public String toString() {
		return "This is state number #13, LOGOUT";
	}

}
