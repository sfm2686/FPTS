/**
 * 
 */
package Core;

import Finance.CashAcct;
import Finance.Equity;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SpecifyHoldingType extends State {
	
	private int id = 10;

	/**
	 * @param context
	 */
	public SpecifyHoldingType(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		System.out.println("Holding Types:");
		System.out.println("1. Cash Account");
		System.out.println("2. Equity");
		System.out.print("Please select a holding type (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Select a Holding Type to Create-----\n");
		this.displayOptions();
		int input = getSc().nextInt();
		while (!isValid(1, 2, input)){
			System.out.println("Invalid input, please try again.");
			displayOptions();
			input = getSc().nextInt();
		}
		setNext(input - 1);
	}

	@Override
	int getID() {
		return this.id;
	}

}
