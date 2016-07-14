package Transaction;

import java.util.ArrayList;
import java.util.Date;
import Finance.*;
import Market.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class acts as a driver for transactions. It takes GUI-supplied date
 *          and delegates the operation to be gone to one eight of the concrete
 *          commands. Once a command is created is it passed to Invoker for execution.
 *
 */
public class Client {

	private User user; // The current user logged into FPTS
	private Invoker invoker; // An invoker (singleton) we use to pass commands.

	/**
	 * Constructor for the Client class.
	 * @param user The current user logged into FPTS
	 */
	public Client(User user) {
		this.user = user;
		invoker = Invoker.getInvoker(user.getLog());
	}
	
	/**
	 * TODO: FINISH
	 * DepositCash takes GUI-supplied information
	 * @param port
	 * @param name
	 * @param deposit
	 */
	public void depositCash(Portfolio port, String name, double deposit){
		Command c = new DepositCash(port, name, deposit);
		invoker.invoke(c);
	}
	public void withdrawCash(Portfolio port, String name, double withdrawal){
		Command c = new WithdrawCash(port, name, withdrawal);
		invoker.invoke(c);
	}
	public void createCash(Portfolio port, String name, double balance){
		Command c = new WithdrawCash(port, name, balance);
		invoker.invoke(c);
	}
	public void removeCash(Portfolio port, String accountName){
		Command c = new RemoveCash(port, accountName);
		invoker.invoke(c);
	}
	public void cashTransfer(Portfolio destPort, Portfolio srcPort, String destAcct, String srcAcct, double amount){
		Command withdraw = new WithdrawCash(srcPort, srcAcct, amount);
		Command deposit = new DepositCash(destPort, destAcct, amount);
		Command cashTransfer = new CashTransfer(null, withdraw, deposit);
		invoker.invoke(cashTransfer);
	}
	public void removeEquity(Portfolio port, String equityName){
		Command c = new RemoveEquity(port, equityName);
		invoker.invoke(c);
	}
	public void subtractEquity(Portfolio port, String equityName, int numShares){
		Command c = new SubtractEquity(port, equityName, numShares);
		invoker.invoke(c);
	}
	public void addShares(Portfolio port, String name, int shares, Date acquitionDate){
		Command c = new AddShares(port, name, shares, acquitionDate);
		invoker.invoke(c);
	}
	public void buyEquity(Portfolio destPort, Portfolio srcPort, String destName, String srcName, int shares, Date acquitionDate){
		AddShares addition = new AddShares(destPort, destName, shares, acquitionDate);
		Command c = new BuyEquity(srcPort, addition, srcName);
		invoker.invoke(c);
	}
	public void sellShares(Portfolio destPort, Portfolio srcPort, String destName, String srcName, int shares){
		SubtractEquity subtraction = new SubtractEquity(srcPort, srcName, shares);
		Command c = new SellEquity(destPort, subtraction, destName);
		invoker.invoke(c);
	}
}