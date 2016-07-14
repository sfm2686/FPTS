package Transaction;

import Finance.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;


import Market.Market;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          AddShares has functionality for adding shares to an existing equity as well as creating a new
 *          equity into a portfolio.
 *
 */
public class AddShares extends Command implements Serializable, UndoableRedoable {

	private String equityName;
	private int numShares;
	private Date date;

	public AddShares(Portfolio receiver, String name, int shares, Date acquitionDate) {
		super(receiver);
		this.equityName = name;
		this.numShares = shares;
		this.date = acquitionDate;
	}

	@Override
	public boolean execute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.addShares(this.numShares);
			return true;
		}
		else{
			if(Market.getMarketInstance().isIndex(this.equityName))
				equity = new Index(this.numShares, this.equityName);
			else if(Market.getMarketInstance().isStock(this.equityName))
				equity = new Stock(this.numShares, this.equityName);
			if(equity != null){
				this.getReciever().addEquity(equity);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void unexecute() {
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null) {
			equity.subtractShares(this.numShares);
			if(equity.getNumShares() == 0){
				this.getReciever().removeEquity(equity);
			}
		}
	}

	@Override
	public UndoableRedoable copy() {
		return(new AddShares(this.getReciever(), this.equityName, this.numShares, this.date));
	}
	
	public double getTransactionValue(){
		if (Market.getMarketInstance().isStock(this.equityName))
			return(this.numShares * Market.getMarketInstance().getPrice(this.equityName));
		else if(Market.getMarketInstance().isIndex(this.equityName))
			return(this.numShares * Market.getMarketInstance().getIndexPrice(this.equityName));
		else
			return(0.0);
	}
	
	@Override
	public String toString() {
		return "\nDate: " + this.getTransactionDate() + "\n\tPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + this.equityName
				+ "\n\tTransaction: Add Equity" + "\n\tShares: " + this.numShares;
	}

	/****** Lead Commands do not Implement Composite Behaviors ******/
	
	@Override
	public void addChild(Command node) {}


	@Override
	public void removeChild(Command node) {}

	@Override
	public ArrayList<Command> getChildren() { return null; }



}
