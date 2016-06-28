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
	private ArrayList<Equity> equities;
	private ArrayList<CashAcct> cashAccounts;

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
	
	protected boolean createEquity(int numShares, EquityUtil equityRef){
		if (this.hasEquity(equityRef)){
			return false;
		}
		Equity equity;
		try{
			StockUtil stockRef = (StockUtil)equityRef;
			equity = new Stock(numShares, stockRef);
		}
		catch(Exception e){
			IndexUtil indexRef = (IndexUtil)equityRef;
			equity = new Index(numShares, indexRef);
		}
		this.equities.add(equity);
		return true;
	}
	
	protected boolean removeEquity(EquityUtil equityRef){
		if (this.hasEquity(equityRef)){
			Equity removal = null;
			for(Equity equity : this.equities){
				if (equity.getName().equalsIgnoreCase(equityRef.getName())){
					removal = equity;
					break;
				}
			}
			this.equities.remove(removal);
			return true;
		}
		return false;
	}
	
	protected boolean createCashAcct(String name, double balance){
		for(CashAcct account : this.cashAccounts){
			if (this.hasCashAccount(name)){
				return false;
			}
		}
		this.cashAccounts.add(new CashAcct(name, balance));
		return true;
	}
	
	protected boolean removeCashAcct(String name){
		if (this.hasCashAccount(name)){
			CashAcct removal = null;
			for(CashAcct account : this.cashAccounts){
				if (account.getName().equalsIgnoreCase(name)){
					removal = account;
					break;
				}
			}
			this.cashAccounts.remove(removal);
			return true;
		}
		return false;
	}
	
	private boolean hasEquity(EquityUtil equityRef){
		for(Equity equity : this.equities){
			if ((equity.getName()).equalsIgnoreCase(equityRef.getName())){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCashAccount(String name){
		for(CashAcct account : this.cashAccounts){
			if ((account.getName()).equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
