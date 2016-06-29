package Finance;


import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransactionClient {

	public void buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort, Portfolio srcPort, CashAcct srcCashAcct){}
	public void buyEquity(EquityUtil reference, int shares, double price, Portfolio destPort){}
	public void transferEquity(Portfolio srcPort, Equity srcEquity, int shares, double price, Portfolio destPort, CashAcct destCashAcct){}
	public void transferEquity(Portfolio srcPort, Equity srcEquity, int shares, double price){}
	public void removeEquity(Portfolio portfolio, Equity equity){}
	public void sellOffEquity(Portfolio portfolio, Equity equity, double price, Portfolio destPort, CashAcct destCashAcct){}
	public void addCash(String accountName, double amount){}
	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount){}
	public void removeCash(Portfolio srcPort, CashAcct srcCashAccount, Portfolio destPort, CashAcct destCashAccount){}
	public void withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount){}
	public void withdrawCash(Portfolio srcPort, CashAcct srcCashAccount, double amount, Portfolio destPort, CashAcct destCashAcct){}
	public void importEquity(Equity equity, Portfolio destPort){}
	public void importCash(CashAcct account, Portfolio destPort, boolean flag){}
	
}
