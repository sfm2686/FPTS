package Finance;

import java.io.Serializable;
import java.util.ArrayList;
import Market.Market;

/**
 * Index represents a market index, sector, or average that contains other equities.
 * An index can be owned by a portfolio which is owned by a user. 
 * Index serves as the Composite element of the GOF Compsoite pattern.
 * Index implements java.io.Serializable so that they can be persisted as user data 
 * within the database.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class Index extends Equity implements Serializable {

	/****** Class Attributes ******/
	protected ArrayList<Equity> children;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor for an Index object.
	 * 
	 * @param numShares The number of shares of the index to be owned.
	 * @param name The name of the index, e.g. "DOW".
	 */
	public Index(int numShares, String name) {
		super.setNumShares(numShares);
		this.name = name; // name is a protected attributed in Equity 
		this.children = new ArrayList<Equity>();
		for(String child : Market.getMarketInstance().getIndices().get(this.name)){
			this.addChild(new Stock(0, child));
		}
		Market.getMarketInstance().addUpdateEquity(this); // Dynamically update prices of owned equities
	}

	/**
	 * Generic toString method.
	 * @return A String representation of this Index object.
	 */
	public String toString() {
		return "Index Holding: " + this.getName() + ", " + this.getNumShares() + " shares, current price: $"
				+ this.getPrice() + ", current value: " + this.getValue() + ".";
	}

	/**
	 * getPrice is defined by the Equity interface.
	 * 
	 * getPrice dynamically calculates the price per share of an equity by cycling
	 * all of its child equities and obtaining the total sum price. This sum is then
	 * divided by the number of child equities the stock is composed of.
	 * 
	 * @return The dynamically allocated price per share of this index.
	 */
	@Override 
	public double getPrice(){
		double totalPrice = 0.0;
		int numChildren = 0;
		for(Equity child : this.children){
			++numChildren;
			totalPrice += child.getPrice();
		}
		if(numChildren != 0){
			double result = totalPrice / numChildren;
			return Math.round(result * 100.0) / 100.0;
		}
		return(0.0);	 
	}
	
	/**
	 * getChildren is defined by the Equity interface.
	 * 
	 * getChildren simply returns all of the Equity 'components' within the composite
	 * structure of this index.
	 * 
	 * @return A collection of the child elements of this index.
	 */
	@Override
	public ArrayList<Equity> getChildren() {
		return this.children;
	}
	
	/**
	 * addChild is defined by the Equity interface.
	 * 
	 * addChild allows for an equity to be added to the composite structure of 
	 * this index.
	 * 
	 * @param node The 'component' equity to add to the index's composition.
	 */
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

	/**
	 * removeChild is defined by the Equity interface.
	 * 
	 * removeChild allows for an equity to be removed from the composite structure of 
	 * this index.
	 * 
	 * @param node The 'component' equity to removed from the index's composition.
	 */
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
}
