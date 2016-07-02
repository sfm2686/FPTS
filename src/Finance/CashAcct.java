package Finance;

import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class represents the Cash Account type of Holding. A user can
 *          have as many cash accounts as they want, cash accounts belong to a
 *          portfolio which belongs to a user. The cash account class supports
 *          getters and setters and it does all of its operations in the class
 *          scope to Separate concerns
 *
 */
public class CashAcct implements Holding, Serializable {

	private double balance;
	private String name;
	private Date creationDate;

	public CashAcct(String name, double balance) {
		this.name = name;
		this.balance = balance;
		this.creationDate = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
	}

	@Override
	public double getValue() {
		return this.getBalance();
	}

	public String getName() {
		return this.name;
	}

	public boolean withdraw(double amount) {
		if (amount > this.balance) {
			return false;
		}
		this.balance -= amount;
		return true;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}

	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}

	public double getBalance() {
		return this.balance;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	@Override
	public String toString() {
		return "Cash Holding: " + this.getName() + ", current balance: " + this.getBalance() + ", date created: "
				+ this.getCreationDate().toString() + ".";
	}

	/**
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
