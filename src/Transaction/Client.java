package Transaction;

import java.util.ArrayList;
import java.util.Date;

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
	private Invoker invoker;

	public Client(User user) {
		this.user = user;
		invoker = Invoker.getInvoker();
	}
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