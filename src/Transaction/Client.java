package Transaction;

import java.util.Date;
import Finance.*;

/**
 * This class acts as a creator of transactions. It takes GUI-supplied date
 * and delegates the operation to be done to one eight of the concrete
 * commands. Once a command is created is it passed to Invoker for execution.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
public class Client {

	/****** Class Attributes ******/
	private Invoker invoker; // An invoker (singleton) we use to pass commands.

	/****** Class Methods ******/
	
	/**
	 * Constructor for the Client class.
	 * 
	 * @param user The current user logged into FPTS
	 */
	public Client(User user) {
		invoker = Invoker.getInvoker(user.getLog());
	}
	
	/**
	 * DepositCash takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio containing the cash account to deposit to.
	 * @param name The cash account being deposited to.
	 * @param deposit The amount that is being deposited into the cash account.
	 */
	public void depositCash(Portfolio port, String name, double deposit){
		Command c = new DepositCash(port, name, deposit);
		invoker.invoke(c);
	}
	
	/**
	 * WithdrawCash takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio containing the cash account to withdraw from.
	 * @param name The cash account being withdrawn from.
	 * @param withdrawal The amount that is being withdrawn from the cash account.
	 */
	public void withdrawCash(Portfolio port, String name, double withdrawal){
		Command c = new WithdrawCash(port, name, withdrawal);
		invoker.invoke(c);
	}
	
	/**
	 * CreateCash takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio that the cash account will be placed into.
	 * @param name The name of the cash account to create.
	 * @param balance The initial balance of the cash account to  be created.
	 */
	public void createCash(Portfolio port, String name, double balance){
		Command c = new CreateCash(port, name, balance);
		invoker.invoke(c);
	}
	
	/**
	 * RemoveCash takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio containing the cash account to remove.
	 * @param accountName The name of the cash account to remove.
	 */
	public void removeCash(Portfolio port, String accountName){
		Command c = new RemoveCash(port, accountName);
		invoker.invoke(c);
	}
	
	/**
	 * CashTransfer takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param destPort The portfolio containing the cash account to deposit to.
	 * @param srcPort The portfolio containing the cash account to withdraw from.
	 * @param destAcct The name of the cash account to deposit to.
	 * @param srcAcct The name of the cash account to withdraw from.
	 * @param amount The amount to transfer between cash accounts.
	 */
	public void cashTransfer(Portfolio destPort, Portfolio srcPort, String destAcct, String srcAcct, double amount){
		Command withdraw = new WithdrawCash(srcPort, srcAcct, amount);
		Command deposit = new DepositCash(destPort, destAcct, amount);
		Command cashTransfer = new CashTransfer(null, withdraw, deposit);
		invoker.invoke(cashTransfer);
	}
	
	/**
	 * RemoveEquity takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio containing the equity to be removed.
	 * @param equityName The name of the equity (ticker symbol or index name) to remove from teh given portfolio.
	 */
	public void removeEquity(Portfolio port, String equityName){
		Command c = new RemoveEquity(port, equityName);
		invoker.invoke(c);
	}
	
	/**
	 * SubtractEquity takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The portfolio containing the equity to subtract shares from.
	 * @param equityName The name of the equity (ticker symbol or index name) to subtract shares from.
	 * @param numShares The number of shares to be subtracted from the given equity.
	 */
	public void subtractEquity(Portfolio port, String equityName, int numShares){
		Command c = new SubtractEquity(port, equityName, numShares);
		invoker.invoke(c);
	}
	
	/**
	 * AddShares takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param port The name of the portfolio containing the equity to be operated on.
	 * @param name The name of the equity to have shares added to it.
	 * @param shares The number of shares to add to the given equity.
	 * @param acquitionDate The date that the user acquired these shares.
	 */
	public void addShares(Portfolio port, String name, int shares, Date acquitionDate){
		Command c = new AddShares(port, name, shares, acquitionDate);
		invoker.invoke(c);
	}
	
	/**
	 * BuyEquity takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param destPort The portfolio containing the equity to add shares to.
	 * @param srcPort The portfolio containing the cash account with funds to purchase the shares from. 
	 * @param destName The name of the equity to add shares to.
	 * @param srcName The name of the cash account to withdraw from.
	 * @param shares The number of shares to add to the equity.
	 * @param acquitionDate The date that the user acquired these shares.
	 */
	public void buyEquity(Portfolio destPort, Portfolio srcPort, String destName, String srcName, int shares, Date acquitionDate){
		AddShares addition = new AddShares(destPort, destName, shares, acquitionDate);
		Command c = new BuyEquity(srcPort, addition, srcName);
		invoker.invoke(c);
	}
	
	/**
	 * SellShares takes GUI-supplied information and creates the corresponding command
	 * object to fulfill the request. The command is then passed to the invoker for execution.
	 * 
	 * @param destPort The portfolio containing the cash account to deposit to.
	 * @param srcPort The portfolio containing the equity with shares to sell.
	 * @param destName The name of the cash account to deposit to.
	 * @param srcName The name of the equity from which to sell shares.
	 * @param shares The number of shares to sell from the equity.
	 */
	public void sellShares(Portfolio destPort, Portfolio srcPort, String destName, String srcName, int shares){
		SubtractEquity subtraction = new SubtractEquity(srcPort, srcName, shares);
		Command c = new SellEquity(destPort, subtraction, destName);
		invoker.invoke(c);
	}
}