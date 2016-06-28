package Finance;

import java.util.ArrayList;

/**
 * @author Sultan Mira & Hunter Caskey
 *
 */
public class Portfolio {
	private Log log;
	
	//private User owner; //Commented out until User class is made
	private ArrayList<Holding> holdings;

	public double getPortfolioValue(){
		double value = 0;
		for (Holding holding : this.holdings){
			value += holding.getValue();
		}
		return value;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
