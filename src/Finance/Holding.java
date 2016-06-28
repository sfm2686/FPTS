package Finance;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 * Holding is the common abstract superclass for CashhAcct and Equity.
 */
public abstract class Holding {
	
	private String name;
	
	protected void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
