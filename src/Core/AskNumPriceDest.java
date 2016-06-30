/**
 * 
 */
package Core;

import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AskNumPriceDest extends State {
	
	/**
	 * @param context
	 */
	public AskNumPriceDest(Context context) {
		super(context);
	}

	private int id = 21;
	private int i;
	private Equity eq;
	private CashAcct acct;
	private Portfolio port;

	//USed differently in this state 
	@Override
	void displayOptions() {
		this.i = 1;
		for ( Equity eq : getContext().getPort().getEquities() )
			System.out.println(this.i++ + ". " + eq.toString());
		System.out.print("Please select an Equity by their number to sell shares from: ");
		int in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( Equity eq : getContext().getPort().getEquities() )
				System.out.println(this.i++ + ". " + eq.toString());
			System.out.print("Please select an Equity by their number to sell shares from: ");
			in = getSc().nextInt();
		}
		this.eq = getContext().getPort().getEquities().get(in - 1);
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Equity Selection-----\n");
		this.displayOptions();
		
		System.out.print("Please specify the number of shares you would like to sell (integer): ");
		int in = getSc().nextInt();
		while ( in > this.eq.getNumShares() ){
			System.out.println("Invalid input, please try again");
			System.out.print("Please specify the number of shares you would like to sell (integer): ");
			in = getSc().nextInt();
		}
		
		System.out.print("Please specify the sale price (integer): ");
		in = getSc().nextInt();
		
		System.out.println("\n\n");
		System.out.println("Please select a destination Cash Account");
		this.selectCashAcct();
		
		
	}
	
	private void selectCashAcct(){
		
		//source portfolio selection
		this.i = 1;
		for ( Portfolio port : getContext().getUserPorts() )
			System.out.println(i++ + ". " + port.getName());
		System.out.print("Select a source portfolio by their number (integer): ");		
		int in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( Portfolio port : getContext().getUserPorts() )
				System.out.println(i++ + ". " + port.getName());
			System.out.print("Select a source portfolio by their number (integer): ");		
			in = getSc().nextInt();
		}
		this.port = getContext().getUserPorts().get(in - 1);
		
		//source CashAcct selection
		this.i = 1;
		for ( CashAcct acct : this.port.getCashAccounts() )
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a source Cash Account by their number (integer): ");		
		int in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( CashAcct acct : this.port.getCashAccounts() )
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a source Cash Account by their number (integer): ");		
			in = getSc().nextInt();
		}
		this.acct = this.port.getCashAccounts().get(in - 1);
	}

	@Override
	int getID() {
		return this.id;
	}

}
