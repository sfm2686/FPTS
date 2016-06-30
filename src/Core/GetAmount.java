/**
 * 
 */
package Core;

import Finance.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class GetAmount extends State {
	
	private int id = 16;

	/**
	 * @param context
	 */
	public GetAmount(Context context) {
		super(context);
	}


	@Override
	void displayOptions() {
		System.out.println("Cash Accounts:");
		int i = 1;
		for(CashAcct acct : getContext().getPort().getCashAccounts()){
			System.out.println(i + ". " + acct.toString());
		}
		System.out.print("Please select a cash account to add to (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Add to Cash Holding-----\n");
		boolean failure;
		int acct = 0;
		do{
			failure = false;
			displayOptions();
			try{
				acct = getSc().nextInt();
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		}while(failure || isValid(1, getContext().getPort().getCashAccounts().size(), acct));
		
		double input = -1;
		do{
			failure = false;
			System.out.print("Please enter the amount you wish to deposit: ");
			try{
				 input = getSc().nextDouble();
				 if(input < 0){
					 failure = true;
				 }
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		}while(failure);
		
		setNext(0);
		AskSource next = (AskSource)getContext().getNextState(0);
		next.acct = getContext().getPort().getCashAccounts().get(acct - 1);
		next.amount = input;
	}

	@Override
	int getID() {
		return this.id;
	}

}
