package Core;

import Finance.CashAcct;
import Finance.Portfolio;

import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AskSource extends State {
	
	private int id = 18;
	protected CashAcct acct;
	protected double amount;
	private ArrayList<CashAcct> cashAccounts; 
	private ArrayList<Portfolio> portfolioMapping;

	/**
	 * @param context
	 */
	public AskSource(Context context) {
		super(context);
		this.cashAccounts = new ArrayList<>();
		this.portfolioMapping = new ArrayList<>();
	}

	@Override
	void displayOptions() {
		System.out.println("Cash Accounts:");
		int i = 0;
		System.out.println("If the source funds are from outside the system, enter 0");
		for(Portfolio port : getContext().getUserPorts()){
			System.out.println("\tCash Accounts from Portfolio: " + port.getName());
			for(CashAcct acct : port.getCashAccounts()){
				System.out.println("\t\t" + ++i + ". " + acct.toString());
				this.cashAccounts.add(acct);
				this.portfolioMapping.add(port);
			}		
		}
		System.out.print("Please select an account to withdraw from (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Specify Source-----\n");
		boolean failure = false;
		int in = -1;
		CashAcct src = null;
		do{
			displayOptions();
			try{
				in = getSc().nextInt();
				if(isValid(0, this.cashAccounts.size(), in)){
					if(in != 0){
						src = this.cashAccounts.get(in - 1);
						if(src.getBalance() < this.amount){
							failure = true;
							System.out.println("Insufficient funds. Please use a different source.");
						}
					}
				}
				else{
					failure = true;
					System.out.println("Invalid input, please try again.");
				}
			}
			catch(Exception e){
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		}while(failure);
		
		if(in > 0){ // Conduct a transfer
			getContext().getTransClient().withdrawCash(portfolioMapping.get(in - 1), src, this.amount, getContext().getPort(), this.acct);
		}
		else{ // Raw deposit
			getContext().getTransClient().addCash(getContext().getPort(), this.acct.getName(), this.amount);	
		}
	}
	
	@Override
	int getID() {
		return this.id;
	}

}
