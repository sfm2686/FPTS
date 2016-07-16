package Finance;

import java.io.Serializable;
import java.util.ArrayList;

import Market.*;

/**
 * @author Sultan Mira & Hunter Caskey
 * 
 *         This class represents a portfolio. A portfolio owns 2 collections of
 *         type holdings, a collection of cash accounts, and a collection of
 *         equities. A portfolio also has a  that it uses to  the
 *         transactions that happen to that . The portfolio is owned by a
 *         user but the portfolio itself does not know or need to know about its
 *         owner.
 *
 */
public class Portfolio implements Serializable {


	//Portfolio's name
	private String name;
	
	//A collection of the equities that a portoflio own
	private ArrayList<Equity> equities;
	
	//A collection of cash accounts owned by the portfolio
	private ArrayList<CashAcct> cashAccounts;

	/**
	 * Constructor
	 * @param name: the name for the portfolio to have
	 */
	public Portfolio(String name) {
		this.name = name;
		this.equities = new ArrayList<>();
		this.cashAccounts = new ArrayList<>();
	}

	
	/**
	 * Getter for the name
	 * @return: the name of the portfolio
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for the portfolio's value
	 * @return: the value of the portfolio
	 */
	public double getPortfolioValue() {
		double value = 0;
		for (Equity equity : this.equities) {
			value += equity.getValue();
		}
		for (CashAcct cash : this.cashAccounts) {
			value += cash.getValue();
		}
		return value;
	}


	/**
	 * Adds an equity to the collection of equities.
	 * @param equity: the equity to be added to the collection
	 */
	public void addEquity(Equity equity) {
		this.equities.add(equity);
	}

	/**
	 * Adds a cash account to the collection of cash accounts
	 * @param cash: the cash account to be stored
	 */
	protected void addCash(CashAcct cash) {
		this.cashAccounts.add(cash);
	}
	
	/**
	 * Removes an equity from the portfolio, so it is no longer owned.
	 * @param equity: the equity to be removed
	 */
	public void removeEquity(Equity equity) {
		this.equities.remove(equity);
	}
	
	/**
	 * Returns a list of all cash accounts a portfolio owns
	 * @return: an arraylist of cash accounts
	 */
	public ArrayList<CashAcct> getCashAccounts() {
		return this.cashAccounts;
	}

	/**
	 * Returns a list of all equities a portfolio owns
	 * @return: an arraylist of all equities
	 */
	public ArrayList<Equity> getEquities() {
		return this.equities;
	}

	/**
	 * Adds a cash account to a portfolio to be owned.
	 * @param account: the account to be stored
	 */
	public void addCashAccount(CashAcct account) {
		this.cashAccounts.add(account);
	}

	/**
	 * Removes a cash account from the collection of cash accounts
	 * @param account: the cash account to be removed
	 */
	public void removeCashAccount(CashAcct account) {
		if(this.cashAccounts.contains(account)){
			this.cashAccounts.remove(account);
		}
	}

	/**
	 * checks if the portfolio has an equity with the same name
	 * @param s: the equity to be checked against
	 * @return: true if the portfolio has an equity with the same name,
	 * 	false otherwise.
	 */
	public boolean hasEquity(String str) {
		for (Equity equity : this.equities) {
			if ((equity.getName()).equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks if the portfolio has a cash account with the same name
	 * @param name: the name to check
	 * @return: true if the portfolio already has a cash account with the same name,
	 * 	false otherwise
	 */
	public boolean hasCashAccount(String name) {
		for (CashAcct account : this.cashAccounts) {
			if ((account.getName()).equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * returns an equity that has the passed string as its name
	 * @param name: the name of the equity to be returned
	 * @return: the equity with the passed name, null if the portfolio
	 * 	does not have it
	 */
	public Equity getEquity(String name) {
		for (Equity equity : this.equities) {
			if (equity.getName().equalsIgnoreCase(name)) {
				return (equity);
			}
		}
		return null;
	}

	/**
	 * Returns the cash account object that is owned by the portfolio
	 * that has the same name as the passed string
	 * @param name: the name of the desired cash account
	 * @return: the cash account object that has the same name, false otherwise
	 */
	public CashAcct getCashAcct(String name) {
		for (CashAcct account : this.cashAccounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				return (account);
			}
		}
		return null;
	}

	/**
	 * toString method for the portfolio
	 * @return: a string that represents the portfolio
	 */
	@Override
	public String toString() {
		String string = "\n" + this.name + "\n\tHoldings:";
		for (CashAcct account : this.cashAccounts) {
			string += "\n\t\t" + account.toString();
		}
		for (Equity equity : this.equities) {
			string += "\n\t\t" + equity.toString();
		}
		return string + "\n";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		StockUtil stockRef = new StockUtil("Apple", "AAPL", "500.00");
//		Stock testStock = new Stock(100, stockRef);
//		IndexUtil indexRef = new IndexUtil("Tech Companies");
//		StockUtil stock1 = new StockUtil("Apple", "APPL", "300");
//		StockUtil stock2 = new StockUtil("Google", "GOOG", "200");
//		StockUtil stock3 = new StockUtil("Microsoft", "MSFT", "1000");
//		indexRef.addStock(stock1);
//		indexRef.addStock(stock2);
//		indexRef.addStock(stock3);
//		Index testIndex = new Index(10, indexRef);
//		CashAcct testAcct = new CashAcct("Account 1", 500.00);
//
//		Portfolio testPortfolio = new Portfolio("MyPortfolio");
//		testPortfolio.addEquity(testStock);
//		testPortfolio.addEquity(testIndex);
//		testPortfolio.addEquity(testIndex);
//		testPortfolio.addCash(testAcct);
//		testPortfolio.addCash(testAcct);
//		System.out.println(testPortfolio.toString());
	}

}
