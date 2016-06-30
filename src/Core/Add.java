/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Add extends State {
	
	private int id = 15;

	/**
	 * @param context
	 */
	public Add(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		System.out.println("Holding Types:");
		System.out.println("1. Cash Account");
		System.out.println("2. Equity");
		System.out.print("Please select a holding type to add to (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Select a Holding Type to Add to-----\n");
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
