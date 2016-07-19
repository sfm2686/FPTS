 package Market;

import java.util.*;
import Finance.*; // For testing.

/**
 * Market is responsible for maintaining a listing of all available
 * stocks and indices within the system. Market provides functionality for 
 * searching through equities, automatically updating equities' prices using an
 * inner thread class, and updating any stocks that are observing market for
 * their price. Market is a singleton object, to centrally hold all equity information
 * and meanwhile making that information globally available to all subsystems easily.
 * 
 * @authors Sultan Mira, Hunter Caskey          
 */
public class Market extends Observable {

	/****** Class Attributes ******/
	private final int nameIndex = 0;
	private final int priceIndex = 1;
	private int updateInterval = 60; // Default to updating the thread every 60 seconds
	/* The key of the HashMap is the ticker symbol. Value by index: 0. name 1. price 2..n indices.*/
	private static HashMap<String, ArrayList<String>> stocks;
	/* The key of the HashMap is the name of the Index. Value by index: 0..n Stocks in that index. */
	private static HashMap<String, ArrayList<String>> indices;
	private static Market instance = null;
	private static ArrayList<Stock> obStocks; // A list of stocks to update.
	
	/****** Class Methods ******/
	
	/**
	 * Singleton instance accessor from GOF text.
	 * 
	 * @return The static instance of Market.
	 */
	public static Market getMarketInstance(){
		if(instance == null){
			instance = new Market();
		}
		return instance;
	}
	
	/**
	 * Private constructor for a Market object.
	 */
	private Market() {
		Market.obStocks = new ArrayList<Stock>();
		Market.stocks = new HashMap<String, ArrayList<String>>();
		Market.indices = new HashMap<String, ArrayList<String>>();
		CSVParser.read();
		StockUpdate thread = new StockUpdate();
		thread.start();
	}
	
	/**
	 * setUpdateInterval allows for the interval in which stocks
	 * are updated to be manipulated at runtime.
	 * 
	 * @param time The interval at which to update stocks (in seconds).
	 */
	public void setUpdateInterval(int time){
		this.updateInterval = time;
	}

	/**
	 * searchStock returns all stocks that have the same ticker symbol, name, or
	 * contains the passed string as a substring.
	 * 
	 * @param s The string that is going to be checked in the stocks collection.
	 * @return A mapping of any stocks that have the same name as the passed string,
	 * 	same ticker symbol as the passed string, or the passed string as a substring.
	 */
	@SuppressWarnings("static-access")
	public HashMap<String, ArrayList<String>> searchStock(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		
		//Search for exact ticker symbols
		if ( this.getStocks().containsKey(s) )
			result.put(s, Market.getStocks().get(s));
		
		
		//Search for subStrings in Stock names and their ticker symbols
		for ( String key : Market.getStocks().keySet() ){
			if ( this.stocks.get(key).get(nameIndex).toLowerCase().contains(s.toLowerCase()) )
				result.put(key, Market.getStocks().get(key));
			if ( key.toLowerCase().contains(s.toLowerCase()) )
				result.put(key, Market.getStocks().get(key));
		}
		return result;
	}
	
	/**
	 * searchIndex returns all of the indices that have the same name of the passed string
	 * 
	 * @param s The string that is going to be used for the search.
	 * @return A collection of indices if they are in the collection 
	 */
	public HashMap<String, ArrayList<String>> searchIndex(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		
		//Exact match.
		if ( Market.getIndices().containsKey(s) )
			result.put(s, Market.getIndices().get(s));
		
		//Substring in the name.
		for ( String key : Market.getIndices().keySet() )
			if ( key.toLowerCase().contains(s.toLowerCase()) )
				result.put(key, Market.getIndices().get(key));
		
		return result;
	}
	
	/**
	 * searchEqutiy returns all equities that have the same ticker symbol, name, or
	 * contains the passed string as a substring.
	 * 
	 * @param s The string that is going to be used for the search.
	 * @return A mapping of all of the equities that match the input string.
	 */
	public HashMap<String, ArrayList<String>> searchEquity(String s){
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		result.putAll(this.searchStock(s));
		result.putAll(this.searchIndex(s));
		
		return result;
		
	}
	
	/**
	 * addUpdateEquity adds a stock object, or an indexes composition of stocks,
	 * to the list of stocks to be updated.
	 *  
	 * @param s The Equity to be added to the update list.
	 */
	public void addUpdateEquity(Equity s){
		//If stock is in market
		if ( Market.stocks.containsKey(s.getName()) || Market.indices.containsKey(s.getName()) ){
			if ( s instanceof Stock ){
				Market.obStocks.add( (Stock) s);
				this.addObserver( (Stock) s);
				return ;
			}
			//s must be an index..
			s = (Index) s;

			for ( Equity p : s.getChildren() ){
				if ( Market.stocks.containsKey(p.getName()) )
					Market.obStocks.add( (Stock) p);
			}
		}
		return;
		

	}
	
	/**
	 * Accessor for the collection of stocks being updated.
	 *  
	 * @return obStocks The collection of stocks that are being updated by the system.
	 */
	protected ArrayList<Stock> getObStocks(){
		return Market.obStocks;
	}

	/**
	 * addEquity registers a new equity within the system, available for a user to add to
	 * their portfolio.
	 * 
	 * The first index of the input collection is the stock's ticker symbol. 
	 * The second index of the input collection is the stock's name. 
	 * The third index of the input collection is the stock's price. 
	 * The remaining indices of the collection specify the index/sector that the stock belongs to, if any.
	 *            
	 * @param equity A collection of the fields of a given stock.           
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
	
	/**
	 * setPrice allows for a stock stored in the mapping of stocks to their information
	 * to have their price field updated.
	 * 
	 * @param ticker The stock identifier of the stock whose price is to change.
	 * @param price The updated price of the stock.
	 */
	protected void setPrice(String ticker, double price){
		if ( Market.getStocks().containsKey(ticker) ) {
			Market.getStocks().get(ticker).set(priceIndex, Double.toString(price));
		}
	}
	
	/**
	 * doneUpdating is a flagging method to allow the inner thread class to
	 * notify the Market instance to that all of the stocks have been updated,
	 * and that the Market should notify all of the equities that depend on price changes.
	 */
	protected void doneUpdating(){
		setChanged();
		notifyObservers();
	}
	
	/**
	 * isIndex  checks if the given string passed is an index maintained in the system.
	 * 
	 * @param s The name of the index.
	 * @return true if s is a name of an index, false otherwise.
	 */
	public boolean isIndex(String s){
		if ( Market.getIndices().containsKey(s) )
			return true;
		return false;
	}
	
	/**
	 * isStock checks if the passed string is a stock maintained in the system.
	 * 
	 * @param s The name of the stock.
	 * @return true if s is a stock, false otherwise.
	 */
	public boolean isStock(String s){
		if ( Market.getStocks().containsKey(s) )
			return true;
		return false;
	}
	
	/**
	 * getDow handles the issues of creating an instance of the DJIA separately.
	 * 
	 * @param shares The number of shares to have for the DJIA.
	 * @return An instance of DJIA.
	 */
	public DJIA getDow(int shares){
		DJIA dow = new DJIA(shares);
		for(String value : Market.getIndices().get("DOW")){
			Stock stock = new Stock(0, value);
			dow.addChild(stock);
		}
		return(dow);
	}
	
	/**
	 * Accessor for the price of the given stock.
	 * 
	 * @param ticker The identifying string of the stock to get the price of.
	 * @return The price of the passed ticker symbol's corresponding stock.
	 */
	public double getPrice(String ticker){
		if(Market.getStocks().containsKey(ticker)){
			return Double.parseDouble(Market.getStocks().get(ticker).get(priceIndex));
		}
		return 0.0;
	}
	
	/**
	 * Accessor for the price of a given index.
	 * 
	 * @param name The identifying string of the index to get the price of.
	 * @return The price of the passed string's corresponding index.
	 */
	public double getIndexPrice(String name){
		if ( !Market.getIndices().containsKey(name) )
			return 0.0;
		if(name.equals("DOW")){
			Index dow = getDow(0);
			return (dow.getPrice());
		}
		double sum = 0;
		for ( String key : Market.getIndices().get(name) ) {
			sum += Double.parseDouble(Market.getStocks().get(key).get(priceIndex));
		}
		
		double result = sum / (Market.getIndices().get(name).size());
		return Math.round( result * 100.0 ) / 100.0;
	}
	
	/**
	 * getStocks allows for static access of the static stock mapping.
	 * 
	 * @return The static mapping of stocks to their respective fields.
	 */
	public static HashMap<String, ArrayList<String>> getStocks(){
		return Market.stocks;
	}
	
	/**
	 * getIndices allows for static access of the static index mapping.
	 * 
	 * @return The static mapping of indices to their respective fields.
	 */
	public static HashMap<String, ArrayList<String>> getIndices(){
		return Market.indices;
	}
	
	/**
	 * getEquities allows for  access of a merge between the static mappings of indices and stocks.
	 * 
	 * @return The mapping of all system equities to their associated fields.
	 */
	public HashMap<String, ArrayList<String>> getEquities(){
		HashMap<String, ArrayList<String>> merged = new HashMap<String, ArrayList<String>>();
		merged.putAll(Market.stocks);
		merged.putAll(Market.indices);
		return(merged);
	}
	
	/**
	 * getStockDescriptor simply allows access to the full name of a stock, identified by 
	 * a ticker symbol.
	 * 
	 * @param ticker The ticker symbol of the stock to retrieve the description of. 
	 * @return The description of the given stock.
	 */
	public String getStockDescriptor(String ticker){
		String str = "";
		try{
			str = Market.stocks.get(ticker).get(nameIndex);
		}
		catch(Exception e){}
		return (str);
	}
	
	/******************************** StockUpdate ********************************/
	
	/**
	 * StockUpdate is a private inner class of Market that simply allows for updating of
	 * stocks. StockUpdate alerts the Market to update all stocks that depend on updates
	 * in prices.
	 * 
	 * @authors Sultan Mira, Hunter Caskey
	 */
	private class StockUpdate extends Thread {
		
		/**
		 * run is defined by the Thread superclass.
		 * 
		 * run will keep executing while the application is running, using the YahooAPI utility
		 * class to update all of the stocks in Market's obStocks list.
		 */
		@Override
		public void run(){
			while ( true ){
				try {
					Thread.sleep(updateInterval * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (Stock s : new ArrayList<Stock>(obStocks)){
					setPrice(s.getName(), YahooAPI.getPrice(s.getName()));
				}
				doneUpdating();
			}
		}
	}
	/******************************** StockUpdate ********************************/

	/**
	 * Unit Tests in Main
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
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
			if(stock.equals("quit")){
				break;
			}
		}
		sc.close();
	}
}
