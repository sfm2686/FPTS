/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimView extends State {
	
	private int id = 6;

	/**
	 * @param context
	 */
	public SimView(Context context) {
		super(context);
		}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		/*
		 * Can go to:
		 * 1. S3
		 * 2. S5
		 */
		System.out.println("Options:");
		System.out.println("\tPortfolio Overview (enter: 1)"); //S3
		System.out.println("\tSimulate again (enter: 2)");     //S5
		System.out.print("Taking input: ");
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Simulation Results View-----\n");

		int c = 1;
		double val = getContext().getSim().getNextResult();
		while ( val != -1 ){
			if ( getSc().nextLine().isEmpty() )
				System.out.print(getContext().getSim().getInterval() + " " + c ++ + ": " + val);
			else {
				System.out.println("Stopping simulation result display\n");
				break;
			}
			val = getContext().getSim().getNextResult();
		}
		System.out.println("\n");

		this.displayOptions();
		int in = getSc().nextInt();
		while ( !isValid(1, 2, in) ){
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			in = getSc().nextInt();
		}
		setNext(in - 1);
		
		if ( in == 1 )
			System.out.println("Your portfolio's value is reset back to " + 
		getContext().getPort().getPortfolioValue());
	}


	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		return this.id;
	}

}
