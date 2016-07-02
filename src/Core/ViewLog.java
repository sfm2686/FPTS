/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class lists the logs that contain different transaction types
 *          along with dates. This state prints off so the user is able to view
 *          them. The logs that are displayed belong to the current selected
 *          portfolio
 *
 */
public class ViewLog extends State {

	private int id = 4;

	/**
	 * @param context
	 */
	public ViewLog(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		/*
		 * Can go to: 1. S3 2. S13
		 */
		System.out.println("Options:");
		System.out.println("\tPortfolio Overview (enter: 1)"); // S3
		System.out.println("\tQuit (enter: 0)"); // S13
		System.out.print("Taking input: ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------View Log-----\n");

		System.out.println(getContext().getPort().getLog());

		this.displayOptions();
		int in = getSc().nextInt();
		while (in != 0 && in != 1) {
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			in = getSc().nextInt();
		}
		if (in == 0)
			setNext(getContext().getTable()[this.id].length);
		else
			setNext(in - 1);
	}

	@Override
	int getID() {
		return this.id;
	}

}
