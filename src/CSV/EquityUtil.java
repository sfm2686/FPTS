package CSV;

public abstract class EquityUtil {
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	 public abstract double getPrice();
}
