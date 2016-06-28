package CSV;

/*
 * @author Hunter Caskey
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
