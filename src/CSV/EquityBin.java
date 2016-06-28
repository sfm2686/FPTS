package CSV;
import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * EquityBin is responsible for maintaining a listing of all available stocks and 
 * indices within the system.
 */
public class EquityBin {

	private Set<StockUtil> stockBin;
	private Set<IndexUtil> indexBin;
	private static EquityBin instance = null;
	
	public EquityBin getEquityBin(){
		if (this.instance == null) {
			instance = new EquityBin();
		}
		return this.instance;
	}
	
	private EquityBin(){
		this.stockBin = new HashSet<StockUtil>();
		this.indexBin = new HashSet<IndexUtil>();
	}
	
	/**
	 * 
	 * @param equity A collection of the fields of a given stock. 
	 * 		The first index is the stock's ticker symbol.
	 * 		The second index is the stock's name.
	 * 		The third index is the stock's price. 
	 * 		The remaining values of the collection specify the index/sector that the stock belongs to, if any.
	 */
	public void addEquity(ArrayList<String> equity){
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
