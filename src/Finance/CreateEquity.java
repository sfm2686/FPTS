package Finance;

import java.io.Serializable;

import CSV.EquityUtil;
import CSV.StockUtil;
import CSV.IndexUtil;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CreateEquity extends Transaction implements Serializable {

	private EquityUtil reference;
	private int numShares;
	
	public CreateEquity(Portfolio receiver, EquityUtil reference, int numShares){
		super(receiver);
		this.reference = reference;
		this.numShares = numShares;
	}
	
	public boolean Execute(){
		if (super.getReciever().hasEquity(this.reference)){
			return false;
		}
		Equity equity;
		try{
			StockUtil stockRef = (StockUtil)this.reference;
			equity = new Stock(numShares, stockRef);
		}
		catch(Exception e){
			IndexUtil indexRef = (IndexUtil)this.reference;
			equity = new Index(numShares, indexRef);
		}
		super.getReciever().addEquity(equity);
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
