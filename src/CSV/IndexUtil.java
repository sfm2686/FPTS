package CSV;

import java.util.ArrayList;

/*
 * @author Hunter Caskey
 */
public class IndexUtil extends EquityUtil {
 
	private ArrayList<StockUtil> stocks;
	
	public IndexUtil(String name){
		super.setName(name);
		this.stocks = new ArrayList<StockUtil>();
	}
	
	public void addStock(StockUtil stock){
		this.stocks.add(stock);
	}
	
	public int getNumStocks(){
		return stocks.size();
	}
	
	public ArrayList<StockUtil> getStocks(){
		return this.stocks;
	}
	
	public boolean hasStock(StockUtil stock){
		return stocks.contains(stock);
	}

	
	public double getPrice(){
		int numStocks = this.getNumStocks();
		double price = 0.0;
		
		if (numStocks == 0){
			return price;
		}
		for (StockUtil stock : this.stocks){
			price += stock.getPrice();
		}
		return (price/numStocks);
	}
	
	
	public static void main(String[] args){
		IndexUtil testIndex = new IndexUtil("Tech Companies");
		StockUtil stock1 = new StockUtil("Apple", "APPL", "300");
		StockUtil stock2 = new StockUtil("Google", "GOOG", "200");
		StockUtil stock3 = new StockUtil("Microsoft", "MSFT", "1000");

		testIndex.addStock(stock1);
		testIndex.addStock(stock2);
		testIndex.addStock(stock3);
		
		int testNum = 5;
		int failCount = 0;
		
		if (testIndex.getNumStocks() != 3){
			++failCount;
		}
		if (! testIndex.hasStock(stock1)){
			++failCount;
		}
		if (! testIndex.hasStock(stock2)){
			++failCount;
		}
		if (! testIndex.hasStock(stock3)){
			++failCount;
		}
		if (testIndex.getPrice() != 500.0){
			++failCount;
		}
		
		System.out.println("Conducting unit tests for IndexUtil:\n" + (testNum - failCount) + " out of " + testNum + " tests passed.");
	}
}
