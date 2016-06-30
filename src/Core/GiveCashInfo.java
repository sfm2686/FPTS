/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class GiveCashInfo extends State {
	
	private int id = 11;

	/**
	 * @param context
	 */
	public GiveCashInfo(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {}

	@Override
	void execute() {
		System.out.println("\n------Specify New Cash Account Information-----\n");
		String acctName;
		double amount = 0;
		boolean repeat;
		do{
			System.out.print("Please enter the name of the account: ");
			acctName = getSc().next();
			do{
				repeat = false;
				System.out.print("Please enter the initial balance of the account (double): ");
				String balance = getSc().next();
				try{
					amount = Double.parseDouble(balance);
				}
				catch(Exception e){
					System.out.println("Invalid input, please try again.");
					repeat = true;
				}
			}while(repeat);
			repeat = false;
			if(!getContext().getTransClient().createCash(getContext().getPort(), acctName, amount)){
				repeat = true;
				System.out.println("An account with that name is already taken, please try again");
			}
		}while(repeat);
		setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}

}
