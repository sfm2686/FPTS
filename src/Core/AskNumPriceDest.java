package Core;

import java.util.ArrayList;

import CSV.EquityUtil;
import Finance.*;
import Transaction.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *          This state asks for the number of shares, price per share, and
 *          destination of an Equity to be sold.
 */
public class AskNumPriceDest extends State {

	private int id = 21;
	private int i;
	private Equity eq;
	private CashAcct acct;
	private Portfolio port;
	private ArrayList<CashAcct> cashAccounts;
	private ArrayList<Portfolio> portfolioMapping;

	/**
	 * @param context
	 */
	public AskNumPriceDest(Context context) {
		super(context);
		this.cashAccounts = new ArrayList<>();
		this.portfolioMapping = new ArrayList<>();
	}

	// USed differently in this state
	@Override
	void displayOptions() {
		System.out.println("Cash Accounts:");
		int i = 0;
		System.out.println("If the destination of the fund is outside the system, enter 0");
		for (Portfolio port : getContext().getUserPorts()) {
			System.out.println("\tCash Accounts from Portfolio: " + port.getName());
			for (CashAcct acct : port.getCashAccounts()) {
				System.out.println("\t\t" + ++i + ". " + acct.toString());
				this.cashAccounts.add(acct);
				this.portfolioMapping.add(port);
			}
		}
		System.out.print("Please select an account to retrieve the required funds from (integer): ");
	}

	@Override
	void execute() {
		System.out.println("\n------Equity Selection-----\n");
		this.selectEq();

		System.out.print("Please specify the number of shares you would like to sell (integer): ");
		int shares = getSc().nextInt();
		while (shares > this.eq.getNumShares()) {
			System.out.println("Invalid input, please try again");
			System.out.print("Please specify the number of shares you would like to sell (integer): ");
			shares = getSc().nextInt();
		}
		boolean failure;
		int in = -1;
		CashAcct dest = null;
		do {
			displayOptions();
			failure = false;
			try {
				in = getSc().nextInt();
				if (isValid(0, this.cashAccounts.size(), in)) {
					if (in != 0) {
						dest = this.cashAccounts.get(in - 1);
					}
				} else {
					failure = true;
					System.out.println("Invalid input, please try again.");
				}
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				failure = true;
			}
		} while (failure);

		System.out.print("Please specify the sale price (double): ");
		double price = getSc().nextDouble();

		if (in == 0) {
			getContext().getTransClient().transferEquity(getContext().getPort(), this.eq, shares, price);
		} else {
			getContext().getTransClient().transferEquity(getContext().getPort(), this.eq, shares, price,
					this.portfolioMapping.get(in - 1), dest);
		}
		setNext(0);
	}

	private void selectEq() {
		this.i = 1;
		for (Equity eq : getContext().getPort().getEquities())
			System.out.println(this.i++ + ". " + eq.toString());
		System.out.print("Please select an Equity by their number to sell shares from: ");
		int in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (Equity eq : getContext().getPort().getEquities())
				System.out.println(this.i++ + ". " + eq.toString());
			System.out.print("Please select an Equity by their number to sell shares from: ");
			in = getSc().nextInt();
		}
		this.eq = getContext().getPort().getEquities().get(in - 1);
	}

	private void selectCashAcct() {

		// source portfolio selection
		this.i = 1;
		for (Portfolio port : getContext().getUserPorts())
			System.out.println(i++ + ". " + port.getName());
		System.out.print("Select a source portfolio by their number (integer): ");
		int in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (Portfolio port : getContext().getUserPorts())
				System.out.println(i++ + ". " + port.getName());
			System.out.print("Select a source portfolio by their number (integer): ");
			in = getSc().nextInt();
		}
		this.port = getContext().getUserPorts().get(in - 1);

		// source CashAcct selection
		this.i = 1;
		for (CashAcct acct : this.port.getCashAccounts())
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a source Cash Account by their number (integer): ");
		in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (CashAcct acct : this.port.getCashAccounts())
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
