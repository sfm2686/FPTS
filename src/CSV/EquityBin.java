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
	private CSVParser parser;
	
	
	public EquityBin(){
		this.stockBin = new HashSet<StockUtil>();
		this.indexBin = new HashSet<IndexUtil>();
		this.parser = new CSVParser();
	}
	
	public void readEquities(){
		parser.init(this);
	}
	
	/**
	 * TODO don't display
	 * This function displays all of the stock's in each of the indices 
	 */
	public ArrayList<EquityUtil> displayMarket(){
		ArrayList<EquityUtil> market = new ArrayList<>();
		for(IndexUtil index : this.indexBin){
			market.add(index);
		}
		for(StockUtil stock : this.stockBin){
			market.add(stock);
		}
		return marlet;
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
		StockUtil stock = new StockUtil(equity.get(0), equity.get(1), equity.get(2));
		this.stockBin.add(stock);
		for(int i = 3; i < equity.size(); i++){
			this.getIndex(equity.get(i)).addStock(stock);;
		}
	}
	
	private IndexUtil getIndex(String name){
		for(IndexUtil index : this.indexBin){
			if(index.getName().equalsIgnoreCase(name)){
				return(index);
			}
		}
		IndexUtil index = new IndexUtil(name);
		this.indexBin.add(index);
		return (new IndexUtil(name));	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EquityBin test = new EquityBin();
		test.readEquities();
	}
}
