package Finance;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * CashAcct represents a holding in a portfolio in which monetary funds are available. 
 * These funds can be used to purchase equities in other subsystem of this applicaiton.
 * CashAcct implements java.io.Serializable in order to be persisted within the database.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class CashAcct implements Serializable {

	/****** Class Attributes ******/
	private double balance;
	private String name;
	private Date creationDate;

	/****** Class Methods ******/
	
	/**
	 * Constructor for a CashAcct object.
	 * 
	 * @param name The name of the newly created cash account.
	 * @param balance The initial balance to have within the account.
	 */
	public CashAcct(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.creationDate = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
	}

	/**
	 * getValue is simply a verbose method for retrieving the balance of a cash account.
	 * 
	 * @return A double representing the monetary funds within a cash account.
	 */
	public double getValue() {
		return this.getBalance();
	}

	/**
	 * Accessor for the name of a cash account.
	 * 
	 * @return The name of this cash account.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * withdraw allows for easy removal of funds from the cash account.
	 * 
	 * @param amount The amount of money to withdraw from the cash account.
	 * @return A boolean indicating success of the operation.
	 */
	public boolean withdraw(double amount) {
		if (amount > this.balance) {
			return false;
		}
		this.balance -= amount;
		return true;
	}
 
	/**
	 * deposit allows for easy addition of funds to the cash account
	 * 
	 * @param amount The amount to be deposited into this cash account.
	 */
	public void deposit(double amount) {
		this.balance += amount;
	}

	/**
	 * setBalance allows for complete overwrite of a cash account's balance.
	 * 
	 * @param newBalance The new balance of this cash account.
	 */
	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}

	/**
	 * Accessor for the balance of a cash account.
	 * 
	 * @return A double representing the monetary funds within this cash account.
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Accessor for a cash account's date of creation within the application.
	 * 
	 * @return A Date object of when this cash account was created.
	 */
	public Date getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Generic toString method.
	 * 
	 * @return A String representation of this cash account.
	 */
	public String toString() {
		return "Cash Holding: " + this.getName() + ", current balance: " + this.getBalance() + ", date created: "
				+ this.getCreationDate().toString() + ".";
	}

	/**
	 * Unit Tests in main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CashAcct testAcct = new CashAcct("Account 1", 500.00);

		int testNum = 5;
		int failCount = 0;

		if (testAcct.getName() != "Account 1") {
			++failCount;
		}
		if (testAcct.getBalance() != 500.00) {
			++failCount;
		}
		testAcct.setBalance(1000.00);
		if (testAcct.getBalance() != 1000.00) {
			++failCount;
		}
		testAcct.deposit(1000.00);
		if (testAcct.getBalance() != 2000.00) {
			++failCount;
		}
		testAcct.withdraw(750.00);
		if (testAcct.getBalance() != 1250.00) {
			++failCount;
		}

		System.out.println("Conducting unit tests for CashAcct:\n" + (testNum - failCount) + " out of " + testNum
				+ " tests passed.");
		System.out.println(testAcct.toString());
	}
}
