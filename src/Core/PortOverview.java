/**
 * 
 */
package Core;

import DataInterface.DBInterface;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *          This state displays the holdings of the selected portfolio. The
 *          portfolio is selected in another state. This state also displays the
 *          options of which a user can choose from to move on to another state.
 *          If the user saves changes, the state goes back to AccountOverview,
 *          S2.
 */
public class PortOverview extends State {

	private int id = 3;

	/**
	 * @param context
	 */
	public PortOverview(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		/*
		 * Can go to: 1. S2 2. S4 3. S5 4. S7 5. S13
		 */
		System.out.println("Options:");
		System.out.println("\tAccount Overview (enter: 1)"); // S2
		System.out.println("\tView Log (enter: 2)"); // S4
		System.out.println("\tSimulate (enter: 3)"); // S5
		System.out.println("\tTransaction Menu (enter: 4)"); // S7
		System.out.println("\tSave changes (enter: 5)");
		System.out.println("\tQuit (enter: 0)"); // S13
		System.out.print("Taking input: ");
	}

	@Override
	void execute() {
		System.out.println("\n------Portfolio Overview-----\n");
		System.out.println("Holdings in this Portfolio:");
		System.out.println(getContext().getPort());

		this.displayOptions();
		int in;
		in = super.getSc().nextInt();
		// option bounds [0 , 4]
		while (!isValid(0, 5, in)) {
			System.out.println("Invalid input. Please try again");
			this.displayOptions();

			in = getSc().nextInt();
		}
		if (in == 5) {
			if (DBInterface.saveUserData(getContext().getUser()))
				System.out.println("Saved successfully");
			setNext(0);
			return;
		}
		if (in > 0) {
			super.setNext(in - 1);
			return;
		}
		super.setNext(super.getContext().getTable()[this.id].length - 1);
	}

	@Override
	int getID() {
		return this.id;
	}

}
