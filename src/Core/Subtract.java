/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *This state's main purpose is to have the user specify a holding type
 *, equity or cash account.
 *The state as almost all of the other states does some light validation on the
 *input so they user's input is somewhat restrict.
 */
public class Subtract extends State {
	
	private int id = 19;

	/**
	 * @param context
	 */
	public Subtract(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		System.out.println("Holding Types:");
		System.out.println("1. Cash Account");
		System.out.println("2. Equity");
		System.out.print("Please select a holding type to subtract from (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Subtracting from a Holding-----\n");
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
