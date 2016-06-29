package Finance;

import java.util.ArrayList;
import CSV.*;

/**
 * @author Sultan Mira & Hunter Caskey
 *
 */
public class Portfolio {
	private Log log;
	
	//private User owner; //Commented out until User class is made
	private String name;
	private ArrayList<Equity> equities;
	private ArrayList<CashAcct> cashAccounts;

	public Portfolio(String name){
		this.name = name;
		this.equities = new ArrayList<>();
		this.cashAccounts = new ArrayList<>();
	}
	
	public double getPortfolioValue(){
		double value = 0;
		for (Equity equity : this.equities){
			value += equity.getValue();
		}
		for (CashAcct cash : this.cashAccounts){
			value += cash.getValue();
		}
		return value;
	}
	
//	protected boolean createEquity(int numShares, EquityUtil equityRef){
//		if (this.hasEquity(equityRef)){
//			return false;
//		}
//		Equity equity;
//		try{
//			StockUtil stockRef = (StockUtil)equityRef;
//			equity = new Stock(numShares, stockRef);
//		}
//		catch(Exception e){
//			IndexUtil indexRef = (IndexUtil)equityRef;
//			equity = new Index(numShares, indexRef);
//		}
//		this.equities.add(equity);
//		return true;
//	}
	
	protected void addEquity(Equity equity){
		this.equities.add(equity);
	}
	
	protected void addCash(CashAcct cash){
		this.cashAccounts.add(cash);
	}
	
//	protected boolean removeEquity(EquityUtil equityRef){
//		if (this.hasEquity(equityRef)){
//			Equity removal = null;
//			for(Equity equity : this.equities){
//				if (equity.getName().equalsIgnoreCase(equityRef.getName())){
//					removal = equity;
//					break;
//				}
//			}
//			this.equities.remove(removal);
//			return true;
//		}
//		return false;
//	}
	
	protected void removeEquity(Equity equity){
		this.equities.remove(equity);
	}
	
//	protected boolean createCashAcct(String name, double balance){
//		for(CashAcct account : this.cashAccounts){
//			if (this.hasCashAccount(name)){
//				return false;
//			}
//		}
//		this.cashAccounts.add(new CashAcct(name, balance));
//		return true;
//	}
	
	protected ArrayList<CashAcct> getCashAccounts(){
		return this.cashAccounts;
	}
	
	protected ArrayList<Equity> getEquities(){
		return this.equities;
	}
	
	protected void addCashAccount(CashAcct account){
		this.cashAccounts.add(account);
	}
	
//	protected boolean removeCashAcct(String name){
//		if (this.hasCashAccount(name)){
//			CashAcct removal = null;
//			for(CashAcct account : this.cashAccounts){
//				if (account.getName().equalsIgnoreCase(name)){
//					removal = account;
//					break;
//				}
//			}
//			this.cashAccounts.remove(removal);
//			return true;
//		}
//		return false;
//	}
	
	protected void removeCashAccount(CashAcct account){
		this.cashAccounts.remove(account);
	}
	
	protected boolean hasEquity(EquityUtil equityRef){
		for(Equity equity : this.equities){
			if ((equity.getName()).equalsIgnoreCase(equityRef.getName())){
				return true;
			}
		}
		return false;
	}
	
	protected boolean hasCashAccount(String name){
		for(CashAcct account : this.cashAccounts){
			if ((account.getName()).equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public Equity getEquity(String name){
		for(Equity equity : this.equities){
			if(equity.getName().equalsIgnoreCase(name)){
				return(equity);
			}
		}
		return null;
	}
	
	public CashAcct getCashAcct(String name){
		for(CashAcct account : this.cashAccounts){
			if(account.getName().equalsIgnoreCase(name)){
				return(account);
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		String string = "Portfolio: " + this.name + "\n\tHoldings:";
		for (CashAcct account : this.cashAccounts){
			string += "\n\t\t" + account.toString();
		}
		for (Equity equity : this.equities){
			string += "\n\t\t" + equity.toString();
		}
		return string;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StockUtil stockRef = new StockUtil("Apple", "AAPL", "500.00");
		Stock testStock = new Stock(100, stockRef);
		IndexUtil indexRef = new IndexUtil("Tech Companies");
		StockUtil stock1 = new StockUtil("Apple", "APPL", "300");
		StockUtil stock2 = new StockUtil("Google", "GOOG", "200");
		StockUtil stock3 = new StockUtil("Microsoft", "MSFT", "1000");
		indexRef.addStock(stock1);
		indexRef.addStock(stock2);
		indexRef.addStock(stock3);
		Index testIndex = new Index(10, indexRef);
		CashAcct testAcct = new CashAcct("Account 1", 500.00);

		Portfolio testPortfolio = new Portfolio("MyPortfolio");
		testPortfolio.addEquity(testStock);
		testPortfolio.addEquity(testIndex);
		testPortfolio.addEquity(testIndex);
		testPortfolio.addCash(testAcct);
		testPortfolio.addCash(testAcct);
		System.out.println(testPortfolio.toString());
	}

}
