package Core;

import Finance.*;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class DisplayHoldings extends State {
	
	private int id = 8;
	private int i;
	private ArrayList<Holding> holdings;

	/**
	 * @param context
	 */
	public DisplayHoldings(Context context) {
		super(context);
		this.holdings = new ArrayList<Holding>();
	}

	@Override
	void displayOptions() {
		System.out.println("Cash Accounts:");
		i = 1;
		for(CashAcct acct : getContext().getPort().getCashAccounts()){
			System.out.println(i + ". " + acct.toString());
			this.holdings.add(acct);
		}
		for(Equity eq : getContext().getPort().getEquities()){
			System.out.println(i + ". " + eq.toString());
			this.holdings.add(eq);
		}
		System.out.print("Please select a holding (integer): ");
	}


	@Override
	void execute() {
		System.out.println("\n------Select a Holding to Remove-----\n");
		this.displayOptions();
		int input = getSc().nextInt();
		while (!isValid(1, i, input)){
			System.out.println("Invalid input, please try again.");
			displayOptions();
			input = getSc().nextInt();
		}
		if((i-1) > getContext().getPort().getCashAccounts().size()){
			getContext().getTransClient().removeCash(getContext().getPort(), (CashAcct)this.holdings.get(i-1));
		}
		else{
			getContext().getTransClient().removeEquity(getContext().getPort(), (Equity)this.holdings.get(i-1));
		}
		super.setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}

}
