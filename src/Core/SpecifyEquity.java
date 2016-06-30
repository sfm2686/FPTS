package Core;

import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SpecifyEquity extends State {
	
	private int id = 14;
	protected double price;
	protected int shares;
	
	/**
	 * @param context
	 */
	public SpecifyEquity(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		System.out.println("Available Equities:");
		int i = 1;
		for(EquityUtil equity : getContext().getMarket().displayMarket()){
			System.out.println(i++ + ". " + equity.toString());
		}
		System.out.print("Please select an equity by the listing number (integer): ");
	}
	
	@Override
	public void execute(){
		System.out.println("\n------Select a Equity Listing to Create-----\n");
		displayOptions();
		int in;
		in = getSc().nextInt();
		while(!isValid(1, getContext().getMarket().displayMarket().size(), in)){
			System.out.println("Invalid input, please try again.");
			displayOptions();
		}
		EquityUtil reference = getContext().getMarket().displayMarket().get(in -1);
		getContext().getTransClient().buyEquity(reference, shares, price, getContext().getPort()); 
	}

	@Override
	int getID() {
		return this.id;
	}

}
