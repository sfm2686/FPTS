package Finance;

import CSV.EquityUtil;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class takes a portfolio as a receiver and removes all of the shares
 * of equities that has the passed in string as their name in the receiver.
 *
 */
public class RemoveEquity extends Transaction implements Serializable{

	private EquityUtil reference;
	
	public RemoveEquity(Portfolio receiver, EquityUtil reference){
		super(receiver);
		this.reference = reference;
	}
	
	public boolean Execute(){
		if (super.getReciever().hasEquity(this.reference)){
			Equity removal = null;
			for(Equity equity : super.getReciever().getEquities()){
				if (equity.getName().equalsIgnoreCase(this.reference.getName())){
					removal = equity;
					break;
				}
			}
			super.getReciever().removeEquity(removal);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + 
				this.reference + "\n\tTransaction: Removed Equity";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
