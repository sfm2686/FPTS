package Finance;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Portfolio represents a collection of equities and cash accounts that is 
 * owned by a user. A portfolio is responsible for maintaining its own collections, 
 * via design by encapsulation, and it implements the java.io.Serializable interface
 * so that it can be persisted as user data. Portfolio names are unique.
 * 
 * @author Sultan Mira & Hunter Caskey
 */
@SuppressWarnings("serial")
public class Portfolio implements Serializable {

	/****** Class Attributes ******/
	private String name;
	private ArrayList<Equity> equities;
	private ArrayList<CashAcct> cashAccounts;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a Portfolio object.
	 * 
	 * @param name The String representation of a portoflio's identifier.
	 */
	public Portfolio(String name) {
		this.name = name;
		this.equities = new ArrayList<>();
		this.cashAccounts = new ArrayList<>();
	}

	
	/**
	 * Accessor for the portfolio's name.
	 * 
	 * @return The name of the portfolio.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Accessor for the portfolio's total wealth value.
	 * 
	 * @return The value of the portfolio.
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
	 * addEquity simply adds an equity object to the collection of equities.
	 * 
	 * @param equity The equity to be added to the collection.
	 */
	public void addEquity(Equity equity) {
		this.equities.add(equity);
	}


	/**
	 * addCashAccount simply adds a cash account to the collection of cash accounts.
	 * 
	 * @param account The cash account to be stored in the portfolio.
	 */
	public void addCashAccount(CashAcct account) {
		this.cashAccounts.add(account);
	}
	
	/**
	 * addCash simply adds a cash account to the collection of cash accounts.
	 * 
	 * @param cash The cash account to be stored.
	 */
	protected void addCash(CashAcct cash) {
		this.cashAccounts.add(cash);
	}

	
	/**
	 * removeEquity removes an equity from the portfolio, so it is no longer owned.
	 * 
	 * @param equity The equity to be removed from the portfolio.
	 */
	public void removeEquity(Equity equity) {
		this.equities.remove(equity);
	}
	
	/**
	 * removeCashAccount removes a cash account from the collection of cash accounts.
	 * 
	 * @param account The cash account to be removed from the portfolio.
	 */
	public void removeCashAccount(CashAcct account) {
		if(this.cashAccounts.contains(account)){
			this.cashAccounts.remove(account);
		}
	}
	
	/**
	 * Accessor for the portfolio's collection of cash accounts.
	 * 
	 * @return An ArrayList of the portfolio's cash accounts.
	 */
	public ArrayList<CashAcct> getCashAccounts() {
		return this.cashAccounts;
	}

	/**
	 * Accessor for the portfolio's collection of equities
	 * 
	 * @return An ArrayList of the portfolio's equities.
	 */
	public ArrayList<Equity> getEquities() {
		return this.equities;
	}

	/**
	 * hasEquity checks if the portfolio has an equity with the same name.
	 * 
	 * @param s The equity to be checked against.
	 * @return true if the portfolio has an equity with the same name, false otherwise.
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
	 * hasCashAccount  checks if the portfolio has a cash account with the same name
	 * 
	 * @param name The name of the cash account to check against.
	 * @return true if the portfolio already has a cash account with the same name, false otherwise
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
	 * Accessor for a specific equity within the portfolio.
	 * 
	 * @param name The name of the equity to be returned.
	 * @return The equity with the passed name, null if the portfolio does not have it.
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
	 * Accessor for a specific cash account within the portfolio.
	 * 
	 * @param name The name of the cash account to be returned.
	 * @return The cash account with the passed name, null if the portfolio does not have it.
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
	 * Generic toString method.
	 * 
	 * @return: A String representation of this portfolio.
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
}
