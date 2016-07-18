package Finance;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Equity is an abstract class that defines the operations associated with owning an index
 * or equity. Equity serves as the 'component' element in the GOF Composite pattern.
 * Equity implements the java.io.Serializable interface so that objects of this type can
 * be persisted as user data within the database.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public abstract class Equity implements Serializable {

	/****** Class Attributes ******/
	protected String name;
	private int numShares;

	/****** Class Methods ******/
	
	
	/**
	 * addChild is a stock method in GOF Composite pattern.
	 * addChild will allow for composite elements to add a component to their composition.
	 * 
	 * @param node The component to add to a composite's composition.
	 */
	public abstract void addChild(Equity node);
	
	/**
	 * removeChild is a stock method in GOF Composite pattern.
	 * removeChild will allow for composite elements to remove a component from their composition.
	 * 
	 * @param node The component to remove from a composite's composition.
	 */
	public abstract void removeChild(Equity node);
	
	/**
	 * getChildren is a stock method in GOF Composite pattern.
	 * getChildren will allow for accessing a composite's children components.
	 * 
	 * @return A composite's children components.
	 */
	public abstract ArrayList<Equity> getChildren();

	/**
	 * getPrice will ensure that all equities are capable of supplying their own price.
	 * 
	 * @return An Equity's price per share.
	 */
	public abstract double getPrice();
	
	/**
	 * getValue simply returns the total wealth value of an equity object.
	 * 
	 * @return The monetary value of an equity object based on the system's price for it.
	 */
	public double getValue(){
		return(this.getPrice() * this.getNumShares());
	}
	
	/**
	 * Mutator for the number of shares of an equity.
	 * 
	 * @param shares The new number of shares now possessed by an equity. 
	 */
	protected void setNumShares(int shares) {
		this.numShares = shares;
	}

	/**
	 * Accessor for the number of shares of an equity.
	 * 
	 * @return The total number of shares possessed by this equity.
	 */
	public int getNumShares() {
		return this.numShares;
	}
	
//	public void setName(String name){
//		this.name = name;
//	}

	/**
	 * subtractShares allows for easy access of removing a specified number
	 * of shares from an equity.
	 * 
	 * @param numShares The number of shares to be removed from the equity.
	 * @return true if the operation is allowable, false if the equity doesn't possess enough shares.
	 */
	public boolean subtractShares(int numShares) {
		if (numShares <= this.numShares) {
			this.numShares -= numShares;
			return true;
		}
		return false;
	}

	/**
	 * addShares allows for easy access of adding a specified number
	 * of shares to an equity.
	 * 
	 * @param numShares The number of shares to be added to the equity.
	 */
	public void addShares(int numShares) {
		this.numShares += numShares;
	}
	
	/**
	 * Accessor for an equity's name.
	 * 
	 * @return The name of the equity.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * equals compares to equities to each other based on their name.
	 * 
	 * @param comparison The equity to compare to this equity.
	 * @return true if the two equities are equal, false otherwise.
	 */
	public boolean equals(Equity comparison){
		return(this.name.equals(comparison.getName()));
	}

}
