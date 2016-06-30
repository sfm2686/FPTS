package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AddEquity extends Transaction {

	private String equityName;
	private int numShares;
	
	public AddEquity(Portfolio receiver, String name, int shares){
		super(receiver);
		this.equityName = name;
		this.numShares = shares;
	}
	
	public boolean Execute(){
		Equity equity = super.getReciever().getEquity(this.equityName);
		if (equity != null){
			equity.addShares(this.numShares);
			return true;
		}
		return false;	
	}
	@Override
	public String toString(){
		return "\nPortfolio Operated On: " + super.getReciever() + "\n\tEquity: " + 
				this.equityName + "\n\tTransaction: Add Equity" + "\n\tShares: " +
				this.numShares;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
