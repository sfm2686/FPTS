package Finance;

import CSV.*;
import Core.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransactionClient {

	private User user;
	
	public TransactionClient(User user){
		this.user = user;
	}
	
	public boolean buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort, Portfolio srcPort, CashAcct srcCashAcct){
		double cost = shares * price;
		Transaction command = new SubtractCash(srcPort, srcCashAcct.getName(), cost);
		if(command.Execute()){
			
			buyEquity(reference, shares, price, destPort);
		}
		return false;
	}
	
	public void buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort){
		Transaction command;
		if(destPort.hasEquity(reference)){
			command = new AddEquity(destPort, reference.getName(), shares);
		}
		else{
			command = new CreateEquity(destPort, reference, shares);
		}
		command.Execute();
	}
	
	public void transferEquity(Portfolio srcPort, Equity equity, int shares, double price, Portfolio destPort, CashAcct destCashAcct){
		if (transferEquity(srcPort, equity, shares, price)){
			double salePrice = shares * price;
			addCash(destPort, destCashAcct.getName(), salePrice);
		}
	}
	
	public boolean transferEquity(Portfolio portfolio, Equity equity, int shares, double price){
		if (equity.getNumShares() >= shares){
			Transaction command = new SubtractEquity(portfolio, equity.getName(), shares);
			return(command.Execute());
		}
		return false;
	}
	
	public void removeEquity(Portfolio portfolio, Equity equity){
		Transaction command = new RemoveEquity(portfolio, equity.getReference());
		command.Execute();
	}
	
	public void sellOffEquity(Portfolio portfolio, Equity equity, double price, Portfolio destPort, CashAcct destCashAcct){
		double deposit = price * equity.getNumShares();
		removeEquity(portfolio, equity);
		addCash(destPort, destCashAcct.getName(), deposit);
	}
	
	public void addCash(Portfolio portfolio, String accountName, double amount){
		Transaction command;
		if (portfolio.hasCashAccount(accountName)){
			command = new CreateCash(portfolio, accountName, amount);
		}
		else{
			command = new AddCash(portfolio, accountName, amount);
		}
		command.Execute();
	}
	
	public boolean createCash(Portfolio portfolio, String accountName, double amount){
		Transaction command = new CreateCash(portfolio, accountName, amount);
		return(command.Execute());
	}
	
	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount){
		Transaction command = new RemoveCash(srcPort, srcCashAccount.getName());
		command.Execute();
	}
	
	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount, Portfolio destPort, CashAcct destCashAccount){
		addCash(destPort, destCashAccount.getName(), srcCashAccount.getValue());
		removeCash(srcPort, srcCashAccount);
	}
	
	public boolean withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount){
		Transaction command = new SubtractCash(srcPort, srcCashAccount.getName(), amount);
		return(command.Execute());
	}
	
	public void withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount, Portfolio destPort, CashAcct destCashAcct){
		if(withdrawCash(srcPort, srcCashAccount, amount)){
			addCash(destPort, destCashAcct.getName(), amount);
		}
	}
	
	public void importEquity(Equity equity, Portfolio destPort){
		buyEquity(equity.getReference(), equity.getNumShares(), 0.0 , destPort);
	}
	
	public boolean importCash(CashAcct account, Portfolio destPort){
		if (account.getBalance() < 0) return false;
		addCash(destPort, account.getName(), account.getValue());
		return true;
	}
	
	public boolean importCash(CashAcct account, Portfolio destPort, boolean flag){ // true then add, false then replace
		if (account.getBalance() < 0) return false;
		if (destPort.hasCashAccount(account.getName()) && !flag){
			Transaction command = new ReplaceCash(destPort, account.getName(), account.getValue());
			return (command.Execute());
		}
		return(importCash(account, destPort));
	}
}