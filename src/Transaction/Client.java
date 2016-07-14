package Transaction;

import Finance.*;
import Market.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class acts as a driver for transactions. It takes one type of
 *          portfolio transaction and delegates between 8 of the concrete
 *          commands to have the operation done. Once every command is done with
 *          its operation it calls log in order for it to be logged.
 *
 */
public class Client {

	private User user;

	public Client(User user) {
		this.user = user;
	}

	public boolean buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort, Portfolio srcPort, CashAcct srcCashAcct){
		double cost = shares * price;
		Command command = new SubtractCash(srcPort, srcCashAcct.getName(), cost);
		if (command.execute()) {
			buyEquity(reference, shares, price, destPort);
		}
		return false;
	}

	public void buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort) {
		Command command;
		if (destPort.hasEquity(reference)) {
			command = new AddEquity(destPort, reference.getName(), shares);
		} else {
			command = new CreateEquity(destPort, reference, shares);
		}
		command.execute();
	}

	public void transferEquity(Portfolio srcPort, Equity equity, int shares, double price, Portfolio destPort,
			CashAcct destCashAcct) {
		if (transferEquity(srcPort, equity, shares, price)) {
			double salePrice = shares * price;
			addCash(destPort, destCashAcct.getName(), salePrice);
		}
	}

	public boolean transferEquity(Portfolio portfolio, Equity equity, int shares, double price) {
		if (equity.getNumShares() >= shares) {
			Command command = new SubtractEquity(portfolio, equity.getName(), shares);
			return (command.execute());
		}
		return false;
	}

	public void removeEquity(Portfolio portfolio, Equity equity) {
		Command command = new RemoveEquity(portfolio, equity.getReference());
		command.execute();
	}

	public void sellOffEquity(Portfolio portfolio, Equity equity, double price, Portfolio destPort,
			CashAcct destCashAcct) {
		double deposit = price * equity.getNumShares();
		removeEquity(portfolio, equity);
		addCash(destPort, destCashAcct.getName(), deposit);
	}

	public void addCash(Portfolio portfolio, String accountName, double amount) {
		Command command;
		if (portfolio.hasCashAccount(accountName)) {
			command = new CreateCash(portfolio, accountName, amount);
		} else {
			command = new AddCash(portfolio, accountName, amount);
		}
		command.execute();
	}

	public boolean createCash(Portfolio portfolio, String accountName, double amount) {
		Command command = new CreateCash(portfolio, accountName, amount);
		return (command.execute());
	}

	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount) {
		Command command = new RemoveCash(srcPort, srcCashAccount.getName());
		command.execute();
	}

	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount, Portfolio destPort, CashAcct destCashAccount) {
		addCash(destPort, destCashAccount.getName(), srcCashAccount.getValue());
		removeCash(srcPort, srcCashAccount);
	}

	public boolean withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount) {
		Command command = new SubtractCash(srcPort, srcCashAccount.getName(), amount);
		return (command.execute());
	}

	public void withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount, Portfolio destPort,
			CashAcct destCashAcct) {
		if (withdrawCash(srcPort, srcCashAccount, amount)) {
			addCash(destPort, destCashAcct.getName(), amount);
		}
	}

	public void importEquity(Equity equity, Portfolio destPort) {
		buyEquity(equity.getReference(), equity.getNumShares(), 0.0, destPort);
	}

	public boolean importCash(CashAcct account, Portfolio destPort) {
		if (account.getBalance() < 0)
			return false;
		addCash(destPort, account.getName(), account.getValue());
		return true;
	}

	public boolean importCash(CashAcct account, Portfolio destPort, boolean flag) { // true
																					// then
																					// add,
																					// false
																					// then
																					// replace
		if (account.getBalance() < 0)
			return false;
		if (destPort.hasCashAccount(account.getName()) && !flag) {
			Command command = new ReplaceCash(destPort, account.getName(), account.getValue());
			return (command.execute());
		}
		return (importCash(account, destPort));
	}
}