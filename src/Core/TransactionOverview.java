/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransactionOverview extends State {
	
	private int id = 7;

	/**
	 * @param context
	 */
	public TransactionOverview(Context context) {
		super(context);
	}


	@Override
	void displayOptions() {
		/*
		 * Can go to:
		 * 1. S3 
		 * 2. S8
		 * 3. S10
		 * 4. S15
		 * 5. S19
		 * 6. S13
		 * 
		 */
		System.out.println("Options:");
		System.out.println("\tPortofolio Overview (enter: 1)");  //S3
		System.out.println("\tRemove a Holding  (enter: 2)");          //S8
		System.out.println("\tCreate a Holding (enter: 3)");          //S10
		System.out.println("\tAdd to a Holding (enter: 4)");  //S15
		System.out.println("\tTake from a Holding (enter: 5)");  //S19
		System.out.println("\tQuit (enter: 0)");              //S13
		System.out.print("Taking input: ");
	}


	@Override
	void execute() {
		System.out.println("\n------Transaction Menu-----\n");
		displayOptions();
		int in;
		in = getSc().nextInt();
		while(!isValid(0,5,in)){
			System.out.println("Invalid input, please try again.");
			displayOptions();
			in = getSc().nextInt();
		}
		if(in == 0){
			setNext(getContext().getTable()[this.id].length);
		}
		setNext(in - 1);
	}

	@Override
	int getID() {
		return this.id;
	}

}
