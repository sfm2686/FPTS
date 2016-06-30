package Core;

import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AskAmountDest extends State {

	private int id = 20;
	private int i = 1;
	private Portfolio srcPort, destPort;
	private CashAcct srcAcct, destAcct;
	/**
	 * @param context
	 */
	public AskAmountDest(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
	}
	//Helper method for selecting portfolios
	private void selectPorts(){
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
		this.srcPort = getContext().getUserPorts().get(in - 1);
		
		//destination portfolio selection
		this.i = 1;
		for ( Portfolio port : getContext().getUserPorts() )
			System.out.println(i++ + ". " + port.getName());
		System.out.print("Select a destination portfolio by their number (integer): ");		
		in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( Portfolio port : getContext().getUserPorts() )
				System.out.println(i++ + ". " + port.getName());
			System.out.print("Select a destination portfolio by their number (integer): ");		
			in = getSc().nextInt();
		}
		this.srcPort = getContext().getUserPorts().get(in - 1);
	}
	
	private void selectCashAcct(){
		
		//source CashAcct selection
		this.i = 1;
		for ( CashAcct acct : this.srcPort.getCashAccounts() )
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a source Cash Account by their number (integer): ");		
		int in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( CashAcct acct : this.srcPort.getCashAccounts() )
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a source Cash Account by their number (integer): ");		
			in = getSc().nextInt();
		}
		this.srcAcct = this.srcPort.getCashAccounts().get(in - 1);
		
		//destination CashAcct selection
		this.i = 1;
		for ( CashAcct acct : this.destPort.getCashAccounts() )
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a destination Cash Account by their number (integer): ");		
		in = getSc().nextInt();
		
		while ( !isValid(1, i, in) ){
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for ( CashAcct acct : this.destPort.getCashAccounts() )
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a destination Cash Account by their number (integer): ");		
			in = getSc().nextInt();
		}
		this.srcAcct = this.destPort.getCashAccounts().get(in - 1);
	}
	@Override
	void execute() {
		System.out.println("\n------Withdrawal-----\n");
		this.selectPorts();
		this.selectCashAcct();
		System.out.print("Please enter the amount you would like to withdraw (double): ");
		double in = getSc().nextDouble();
		while ( in > this.srcAcct.getBalance() ){
			System.out.println("insufficient funds, please try again");
			System.out.print("Please enter the amount you would like to withdraw (double): ");
			in = getSc().nextDouble();
		}
		
		this.srcAcct.setBalance(this.srcAcct.getBalance() - in);
		this.destAcct.setBalance(this.destAcct.getBalance() + in);
		System.out.println("Transfer successful..");
		setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}

}
