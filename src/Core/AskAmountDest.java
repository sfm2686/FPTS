package Core;

import Finance.*;
import Transaction.CashAcct;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *          This state is responsible for setting up the transaction of
 *          withdrawal between 2 different Cash account, current portfolio and a
 *          destination one
 */
public class AskAmountDest extends State {

	private int id = 20;
	private int i = 1;
	private Portfolio destPort;
	private CashAcct srcAcct, destAcct;

	/**
	 * @param context
	 */
	public AskAmountDest(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		System.out.println("Options:");
		System.out.println("\tWithdraw cash to Outside FPTS (enter: 1)"); // S2
		System.out.println("\tTransfer Cash between Cash Accounts (enter: 2)"); // S4
		System.out.print("Taking input: ");
	}

	// Helper method for selecting portfolios
	private void selectPorts() {

		// destination portfolio selection
		this.i = 1;
		for (Portfolio port : getContext().getUserPorts())
			System.out.println(i++ + ". " + port.getName());
		System.out.print("Select a destination portfolio by their number (integer): ");
		int in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (Portfolio port : getContext().getUserPorts())
				System.out.println(i++ + ". " + port.getName());
			System.out.print("Select a destination portfolio by their number (integer): ");
			in = getSc().nextInt();
		}
		this.destPort = getContext().getUserPorts().get(in - 1);
	}

	private void selectCashAcct() {

		// source CashAcct selection
		this.i = 1;
		for (CashAcct acct : getContext().getPort().getCashAccounts())
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a source Cash Account by their number (integer): ");
		int in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (CashAcct acct : getContext().getPort().getCashAccounts())
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a source Cash Account by their number (integer): ");
			in = getSc().nextInt();
		}
		this.srcAcct = getContext().getPort().getCashAccounts().get(in - 1);

		// destination CashAcct selection
		this.i = 1;
		for (CashAcct acct : this.destPort.getCashAccounts())
			System.out.println(i++ + ". " + acct.toString());
		System.out.print("Select a destination Cash Account by their number (integer): ");
		in = getSc().nextInt();

		while (!isValid(1, i, in)) {
			System.out.println("Invalid input, please try again");
			this.i = 1;
			for (CashAcct acct : this.destPort.getCashAccounts())
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a destination Cash Account by their number (integer): ");
			in = getSc().nextInt();
		}
		this.srcAcct = this.destPort.getCashAccounts().get(in - 1);
	}

	@Override
	void execute() {
		System.out.println("\n------Withdrawal-----\n");
		this.displayOptions();
		int input = getSc().nextInt();
		while (!isValid(1, 2, input)) {
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			input = getSc().nextInt();
		}

		if (input == 2) {
			this.selectPorts();
			this.selectCashAcct();
			System.out.print("Please enter the amount you would like to withdraw (double): ");
			double amount = getSc().nextDouble();
			while (amount > this.srcAcct.getBalance()) {
				System.out.println("insufficient funds, please try again");
				System.out.print("Please enter the amount you would like to withdraw (double): ");
				amount = getSc().nextDouble();
			}

			getContext().getTransClient().withdrawCash(getContext().getPort(), this.srcAcct, amount, this.destPort,
					this.destAcct);
			System.out.println("Transfer successful..");
			setNext(0);
		} else {
			this.i = 1;
			for (CashAcct acct : getContext().getPort().getCashAccounts())
				System.out.println(i++ + ". " + acct.toString());
			System.out.print("Select a source Cash Account by their number (integer): ");
			int in = getSc().nextInt();

			while (!isValid(1, i, in)) {
				System.out.println("Invalid input, please try again");
				this.i = 1;
				for (CashAcct acct : getContext().getPort().getCashAccounts())
					System.out.println(i++ + ". " + acct.toString());
				System.out.print("Select a source Cash Account by their number (integer): ");
				in = getSc().nextInt();
			}
			this.srcAcct = getContext().getPort().getCashAccounts().get(in - 1);

			System.out.print("Please enter the amount you would like to withdraw (double): ");
			double amount = getSc().nextDouble();
			while (amount > this.srcAcct.getBalance()) {
				System.out.println("insufficient funds, please try again");
				System.out.print("Please enter the amount you would like to withdraw (double): ");
				amount = getSc().nextDouble();
			}

			getContext().getTransClient().withdrawCash(getContext().getPort(), this.srcAcct, amount);
			setNext(0);
		}
	}

	@Override
	int getID() {
		return this.id;
	}

}
