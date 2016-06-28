/**
 * 
 */
package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CashAcct implements Holding {

	private double balance;
	private String name;
	
	public CashAcct(String name, double balance){
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public double getValue(){
		return this.getBalance();
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean withdraw(double amount){
		if (amount > this.balance){
			return false;
		}
		this.balance -= amount;
		return true;
	}
	
	public void deposit(double amount){
		this.balance += amount;
	}
	
	public void setBalance(double newBalance){
		this.balance = newBalance;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CashAcct testAcct = new CashAcct("Account 1", 500.00);

		int testNum = 5;
		int failCount = 0;
		
		if (testAcct.getName() != "Account 1"){
			++failCount;
		}
		if (testAcct.getBalance() != 500.00){
			++failCount;
		}
		testAcct.setBalance(1000.00);
		if (testAcct.getBalance() != 1000.00){
			++failCount;
		}
		testAcct.deposit(1000.00);
		if (testAcct.getBalance() != 2000.00){
			++failCount;
		}
		testAcct.withdraw(750.00);
		if (testAcct.getBalance() != 1250.00){
			++failCount;
		}
		
		System.out.println("Conducting unit tests for CashAcct:\n" + (testNum - failCount) + " out of " + testNum + " tests passed.");
	}
}
