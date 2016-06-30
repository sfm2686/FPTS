/**
 * 
 */
package Core;

import Finance.Equity;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *This state prompts the user to input the shares and price of equities to be 
 *bought, this state also lists the current owned equities. The state's main
 *purpose is to have the user specifies the details so it can pass them to the
 *next one.
 */
public class GetSharesPrice extends State{
	
	private int id = 17;

	/**
	 * @param context
	 */
	public GetSharesPrice(Context context) {
		super(context);
	}


	@Override
	void displayOptions() {
		System.out.println("Currently Owned Equities:");
		int i = 1;
		for(Equity equity : getContext().getPort().getEquities()){
			System.out.println(i + ". " + equity.toString());
		}
		System.out.print("Please select an equity by the listing number (integer): ");
	}
	
	@Override
	public void execute(){
		System.out.println("\n------Select an Equity to Add Shares To-----\n");
		boolean failure = false;
		displayOptions();
		int in;
		in = getSc().nextInt();
		while(!isValid(1, getContext().getPort().getEquities().size(), in)){
			System.out.println("Invalid input, please try again.");
			displayOptions();
		}
		
		double price = -1;
		do{
			failure = false;
			System.out.print("Please enter the acquisition price per share (double): ");
			try{
				price = getSc().nextDouble();
				if(price <= 0){
					failure = true;
					System.out.println("Invalid input, please try again.");
				}
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		}while(failure);
		
		int shares = -1;
		do{
			failure = false;
			System.out.print("Please enter the number of shares being acquired (double): ");
			try{
				shares = getSc().nextInt();
				if(shares <= 0){
					failure = true;
					System.out.println("Invalid input, please try again.");
				}
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		}while(failure);

		setNext(0);
		AskSrc next = (AskSrc)getContext().getNextState(0);
		next.equity = getContext().getPort().getEquities().get(in - 1);
		next.price = price;
		next.shares = shares;
		
	}

	@Override
	int getID() {
		return this.id;
	}

}
