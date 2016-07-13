package Finance;

import java.io.Serializable;
import java.util.ArrayList;
import CSV.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class represents an index that contains equtieis. An index is
 *          the same as a sector. An index can be owned by a portfolio which is
 *          owned by a user. An index has a collection of portoflio that it can
 *          do some basic operations on such as display, get total price of the
 *          sector/index
 * 
 */
public class Index extends Equity implements Serializable {

	private ArrayList<Equity> children;
	
	public Index(int numShares, String name) {
		super.setNumShares(numShares);
		this.name = name; // name is a protected attributed in Equity 
		this.children = new ArrayList<Equity>();
	}
	
	@Override
	public String toString() {
		return "Index Holding: " + this.getName() + ", " + this.getNumShares() + " shares, current price: $"
				+ this.getPrice() + ", current value: " + this.getValue() + ".";
	}

	// Implement the Equity interface
	
	@Override
	public double getValue() {
		return (super.getNumShares() * this.getPrice());
	}
	
	@Override 
	public double getPrice(){
		double totalPrice = 0.0;
		int numChildren = 0;
		for(Equity child : this.children){
			++numChildren;
			totalPrice += child.getPrice();
		}
		if(numChildren != 0){
			return(totalPrice / numChildren);
		}
		return(0.0);
		 
	}
	
	@Override
	public void addChild(Equity node) {
		boolean present = false;
		for(Equity child : this.children){
			if(node.equals(child)){
				present = true;
			}
		}
		if(!present){
			this.children.add(node);
		}
	}

	@Override
	public void removeChild(Equity node) {
		int index = -1;
		for(int i = 0; i < this.children.size(); i++){
			if(node.equals(this.children.get(i))){
				index = i;
				break;
			}
		}
		if(index != -1){
			this.children.remove(index);
		}
	}
	
	/**
	 * Unit Tests for Index
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		IndexUtil indexRef = new IndexUtil("Tech Companies");
		StockUtil stock1 = new StockUtil("Apple", "APPL", "300");
		StockUtil stock2 = new StockUtil("Google", "GOOG", "200");
		StockUtil stock3 = new StockUtil("Microsoft", "MSFT", "1000");

		indexRef.addStock(stock1);
		indexRef.addStock(stock2);
		indexRef.addStock(stock3);

		Index testIndex = new Index(10, indexRef);

		int testCount = 6;
		int failCount = 0;

		if (testIndex.getName() != "Tech Companies") {
			++failCount;
		}
		if (testIndex.getNumShares() != 10) {
			++failCount;
		}
		if (testIndex.getPrice() != 500.00) {
			++failCount;
		}
		if (testIndex.getValue() != 5000.00) {
			++failCount;
		}
		testIndex.addShares(10);
		if (testIndex.getValue() != 10000.00) {
			++failCount;
		}
		testIndex.subtractShares(15);
		if (testIndex.getValue() != 2500.00) {
			++failCount;
		}

		System.out.println("Conducting unit tests for Index:\n" + (testCount - failCount) + " out of " + testCount
				+ " tests passed.");
	}

}
