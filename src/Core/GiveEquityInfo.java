/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *This state doesnt not use dispalyOptions because there arent any options
 *to display. This state's purpose is to have the user speicfy a new Equity
 *information for it to be made and added to the current portfolio owned b the user.
 */
public class GiveEquityInfo extends State {
	
	private int id = 12;

	/**
	 * @param context
	 * 
	 */
	public GiveEquityInfo(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
	}
	
	@Override
	void execute() {
		System.out.println("\n------Specify New Equity Information-----\n");
		String acctName;
		int shareNum = 0;
		double pricePerShare = 0;
		boolean repeat;
		
		do{
			repeat = false;
			System.out.print("Please enter the number of shares for the new equity (integer): ");
			String shares = getSc().next();
			try{
				shareNum = Integer.parseInt(shares);
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				repeat = true;
			}
		}while(repeat);
		
		do{
			repeat = false;
			System.out.print("Please enter the acquisition price per share (double): ");
			String price = getSc().next();
			try{
				pricePerShare = Double.parseDouble(price);
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				repeat = true;
			}
		}while(repeat);

		setNext(0);
		
		// This is the hackiest shit ever. 
		SpecifyEquity next = (SpecifyEquity)getContext().getNextState(0);
		next.shares = shareNum;
		next.price = pricePerShare;
	}
	@Override
	int getID() {
		return this.id;
	}

}
