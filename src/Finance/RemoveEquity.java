package Finance;

import CSV.EquityUtil;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
