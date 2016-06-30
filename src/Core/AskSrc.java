package Core;

import java.util.ArrayList;

import Finance.CashAcct;
import Finance.Equity;
import Finance.Portfolio;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AskSrc extends State {
	
	private int id = 9;
	protected Equity equity;
	protected int shares;
	protected double price;
	private ArrayList<CashAcct> cashAccounts; 
	private ArrayList<Portfolio> portfolioMapping;

	/**
	 * @param context
	 */
	public AskSrc(Context context) {
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
		System.out.print("Please select an account to retrieve the required funds from (integer): ");
	}

	@Override
	void execute() {
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
						if(src.getBalance() < (this.price * this.shares)){
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
		
		EquityUtil reference = this.equity.getReference();
		if(in > 0){ // Conduct a transfer
			getContext().getTransClient().buyEquity(reference, in, in, getContext().getPort(), this.portfolioMapping.get(in - 1), this.cashAccounts.get(in - 1));
		}
		else{ // Raw Buy
			getContext().getTransClient().buyEquity(reference, this.shares, this.price, getContext().getPort());
		}
	}
	
	@Override
	int getID() {
		return this.id;
	}

}
