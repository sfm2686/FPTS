 package Market;

import java.util.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

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
	private int updateInterval = 60; // Default to updating the thread every minute

	public void setUpdateInterval(int time){
		this.updateInterval = time;
	}
	
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

	/**
	 * Singleton instance getter. To ensure there is only one.
	 * @return: instance of Market.
	 */
	public static Market getMarketInstance(){
		if(instance == null){
			instance = new Market();
		}
		return instance;
	}
	
	/**
	 * Singleton private constructor
	 */
	private Market() {
		this.obStocks = new ArrayList<Stock>();
		this.stocks = new HashMap<String, ArrayList<String>>();
		this.indices = new HashMap<String, ArrayList<String>>();
		CSVParser.read();
		StockUpdate thread = new StockUpdate();
		thread.run();
	}
	
	/**
	 * Returns all stocks that have the same ticker symbol, name, or
	 * 	contains the passed string as a substring.
	 * @param s: the string that is going to be checked in the stocks collection
	 * @return: any stocks that have the same name as the passed string,
	 * 	same ticker symbol as the passed string or the passed string as a substring.
	 */
	public HashMap<String, ArrayList<String>> searchStock(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		
		//Search for exact ticker symbols
		if ( this.stocks.containsKey(s) )
			result.put(s, this.stocks.get(s));
		
		
		//Search for subStrings in Stock names and their ticker symbols
		for ( String key : this.stocks.keySet() ){
			if ( this.stocks.get(key).get(nameIndex).toLowerCase().contains(s.toLowerCase()) )
				result.put(key, this.stocks.get(key));
			if ( key.toLowerCase().contains(s.toLowerCase()) )
				result.put(key, this.stocks.get(key));
		}
		return result;
	}
	
	/**
	 * This method returns all of the indices that have the same name of 
	 * 	the passed string
	 * @param s: the string that is going to be used for the search.
	 * @return: an arraylist of indices if they are in the collection 
	 */
	public HashMap<String, ArrayList<String>> searchIndex(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		
		//Exact match.
		if ( this.indices.containsKey(s) )
			result.put(s, this.indices.get(s));
		
		//Substring in the name.
		for ( String key : this.indices.keySet() )
			if ( key.toLowerCase().contains(s.toLowerCase()) )
				result.put(key, this.indices.get(key));
		
		return result;
	}
	
	public HashMap<String, ArrayList<String>> searchEquity(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		result.putAll(this.searchStock(s));
		result.putAll(this.searchIndex(s));
		
		return result;
		
	}
	
	/**
	 * Adds a stock object to the list of stocks to be updated.
	 * @param s the stock to be added.
	 */
	public void addUpdateEquity(Equity s){
		//If stock is not in market
		if ( !this.stocks.containsKey(s.getName()) ||
				!this.indices.containsKey(s.getName()) )
			return ;
		
		if ( s instanceof Stock ){
			this.obStocks.add( (Stock) s);
			this.addObserver( (Stock) s);
			return ;
		}
		//s must be an index..
		s = (Index) s;

		for ( Equity p : s.getChildren() ){
			if ( this.stocks.containsKey(p.getName()) )
				this.obStocks.add( (Stock) p);
		}
	}
	
	/**
	 * Getter for the obStocks collection
	 * @return: obStocks arraylist
	 */
	protected ArrayList<Stock> getObStocks(){
		return this.obStocks;
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
		if ( this.stocks.containsKey(ticker) ) {
			this.stocks.get(ticker).set(priceIndex, Double.toString(price));
		}
	}
	
	protected void doneUpdating(){
		setChanged();
		notifyObservers();
	}
	
	/**
	 * This method checks if the string passed is an index.
	 * @param s the name of the index.
	 * @return true if s is a name of an index, false otherwise
	 */
	public boolean isIndex(String s){
		if ( this.indices.containsKey(s) )
			return true;
		return false;
	}
	
	/**
	 * This method checks if the passed string is a stock.
	 * @param s the name of the stock
	 * @return true if s is a stock, false otherwise
	 */
	public boolean isStock(String s){
		if ( this.stocks.containsKey(s) )
			return true;
		return false;
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
	
	/******************************** StockUpdate ********************************/
	private class StockUpdate extends Thread {
		
		private StockUpdate(){}
		
		@Override
		public void run(){
			while ( true ){
				try {
					sleep(updateInterval * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (Stock s : obStocks){
					setPrice(s.getName(), YahooAPI.getPrice(s.getName()));
				}
				doneUpdating();
			}
		}
	}
	/******************************** StockUpdate ********************************/

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Market market = Market.getMarketInstance();
		String index;
		Scanner sc = new Scanner(System.in);
		
		while ( true ) {
			//Testing searches..
			System.out.print("Search for a stock: ");
			String stock = sc.nextLine();
			
			HashMap<String, ArrayList<String>> r = Market.getMarketInstance().searchEquity(stock); 
			System.out.println("Results: ");
			for ( String key : r.keySet() )
				System.out.print("\t" + "Name: " + key + ", Value: " + r.get(key) + "\n");
		}
		
		
//		System.out.println("Indices:");
//		for ( String key : indices.keySet() )
//			System.out.println("\t" + key);
//		System.out.print("Please enter Index name to calc price: ");
//		index = sc.nextLine();
//		
//		Index indexTest;
//		if(index.equals("DOW")){
//			indexTest = new DJIA(1);
//		}
//		else{
//			indexTest = new Index(1, index);
//		}
//		for(String key : indices.get(index)){
//			indexTest.addChild(new Stock(1, key));
//		}
//		
//		System.out.println("The price for " + index + " is: " + indexTest.getPrice());
//		System.out.println("done..");

	}
}
