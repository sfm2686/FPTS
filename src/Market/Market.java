 package Market;

import java.util.*;
import Finance.*; // for testing


/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          EquityBin is responsible for maintaining a listing of all available
 *          stocks and indices within the system. It can display them, search
 *          for them and read them from the parser class.
 */
public class Market extends Observable {

	private final int nameIndex = 0;
	private final int priceIndex = 1;
	
	//The key of the HashMap is the ticker symbol.
	//Value by index:
	//	1. name
	//	2. price
	//	3(and over). indices.
	private static HashMap<String, ArrayList<String>> stocks;
	
	//The key of the HashMap is the name of the Index.
	//Value by index:
	//	stocks in that index.
	private static HashMap<String, ArrayList<String>> indices;
	
	private static Market instance = null;
	
	//A list of stocks to update.
	private static ArrayList<Stock> obStocks;

	public static Market getMarketInstance(){
		if(instance == null){
			instance = new Market();
		}
		return instance;
	}
	
	private Market() {
		
		this.stocks = new HashMap<String, ArrayList<String>>();
		this.indices = new HashMap<String, ArrayList<String>>();
		CSVParser.read();
		
		//TODO
		//Add Observers here..
		//addObserver();
		
	}
	

	/**
	 * 
	 * @param equity
	 *            A collection of the fields of a given stock. The first index
	 *            is the stock's ticker symbol. The second index is the stock's
	 *            name. The third index is the stock's price. The remaining
	 *            values of the collection specify the index/sector that the
	 *            stock belongs to, if any.
	 */
	public static void addEquity(ArrayList<String> equity) {
		String name = equity.get(0);
		equity.remove(0);
		stocks.put(name, equity);
		
		for ( int i = 2; i < equity.size(); i ++ ){
			if ( indices.containsKey(equity.get(i)) )
				indices.get(equity.get(i)).add(name);
			else {
				ArrayList<String> val = new ArrayList<String>();
				val.add(name);
				indices.put(equity.get(i), val);
			}
		}
	}
	
	protected void setPrice(String ticker, double price){
		if ( this.stocks.containsKey(ticker) )
			this.stocks.get(ticker).set(priceIndex, Double.toString(price));
	}
	
	/**
	 * This method returns the price of the given ticker symbol
	 * @param ticker passed to get the price for.
	 * @return the price of the passed ticker symbol.
	 */
	public double getPrice(String ticker){
		if(this.stocks.containsKey(ticker))
			return Double.parseDouble(this.stocks.get(ticker).get(priceIndex));
		return 0.0;
	}
	
	public double getIndexPrice(String name){
		if ( !this.indices.containsKey(name) )
			return 0.0;
		
		double sum = 0;
		for ( String key : this.indices.get(name) ) {
			sum += Double.parseDouble(this.stocks.get(key).get(priceIndex));
			System.out.println("THE SUM IS: " + sum);
		}
		
		double result = sum / (this.indices.get(name).size());
		System.out.println("SIZOE OF " + name + " is: " + this.indices.get(name).size() );
		return Math.round( result * 100.0 ) / 100.0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Market market = Market.getMarketInstance();
		String index;
		System.out.println("Indices:");
		for ( String key : indices.keySet() )
			System.out.println("\t" + key);
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter Index name to calc price: ");
		index = sc.nextLine();
		
		Index indexTest;
		if(index.equals("DOW")){
			indexTest = new DJIA(1);
		}
		else{
			indexTest = new Index(1, index);
		}
		for(String key : indices.get(index)){
			indexTest.addChild(new Stock(1, key));
		}
		
		System.out.println("The price for " + index + " is: " + indexTest.getPrice());
		System.out.println("done..");

	}
}
