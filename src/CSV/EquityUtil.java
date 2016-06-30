package CSV;

/*
 * @author Hunter Caskey, Sultan Mira
 * 
 * This class is the super class for the indexUtil and the stockUtil that
 * populate EquityBin which contains the base market of equities. 
 */
public abstract class EquityUtil {
	
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	protected void setName(String name){
		this.name = name;
	}
	
	public abstract double getPrice();
}
