package WatchList;

import java.io.Serializable;
import Market.Market;
import Finance.Equity;


/**
 * WatchListItem is the encapsulation of an Equity object along with 'trigger values' to
 * represent a high and low bound for an equities price. A State enumeration is also
 * encapsulated in order to intuitively convey how an equity's current price relates
 * to the low and high bounds. Instances of this class are stored within WatchList 
 * as a collection.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class WatchListItem implements Serializable{

	/**
	 * An enumeration to easily convey the state of the WatchListItem. These states are
	 * externally maintained by Visitor actors. 
	 * 
	 * @authors Sultan Mira, Hunter Caskey
	 */
	public enum State {
				 //Comments are for the GUI, those are JLabel background colors..
		NowHigh, //Dark Red
		NowLow,  //Dark Green
		WasHigh, //Light Red
		WasLow,  //Light green
		Normal   //White
	}
	
	/***** Class Attributes *****/
	
	private Equity eq;
	private State state;
	private Double lowBound  = null; 
	private Double highBound = null;
	
	/***** Class Methods *****/

	/**
	 * Constructor for a WatchListItem.
	 * 
	 * Initialized required data. Adds all the necessary stocks to be updated to Market's
	 * list of 'updateable' stocks.
	 * 
	 * @param eq The equity that a WatchListItem has the responsibility of evaluating.
	 */
	public WatchListItem(Equity eq){
		this.eq = eq;
		this.state = State.Normal;
		Market.getMarketInstance().addUpdateEquity(eq);
	}
	
	/**
	 * Setter methods for the high price bound. Set only if the new value is higher than
	 * the low bound, if any exists.
	 * 
	 * @param n The new value of the high price bound.
	 */
	public void setHighBound(double n){
		if(this.lowBound != null){
			if (n > this.lowBound){
				this.highBound = n;
			}
		}
	}
	
	/**
	 * Setter methods for the low price bound. Set only if the new value is lower than
	 * the high bound, if any exists.
	 * 
	 * @param n The new value of the low price bound.
	 */
	public void setLowBound(double n){
		if(this.highBound != null){
			if(n < this.highBound){
				this.lowBound = n;
			}
		}
	}
	
	/**
	 * Taken from GOF text.
	 * 
	 * accept serves a role in the double dispatch of the Visitor pattern. 
	 * Signals to a visitor that this object is to be operated upon.
	 * 
	 * @param v A visitor that will perform operations upon the WatchListItem.
	 */
	protected void accept(Visitor v){
		v.visit(this);
	}
	
	/**
	 * Accessor for this WatchListItem's lower price bound.
	 * 
	 * @return The lower price bound, or null if not defined.
	 */
	public double getLowBound(){
		return this.lowBound;
	}
	
	/**
	 * Accessor for this WatchListItem's higher price bound.
	 * @return The higher price bound, or null if not defined.
	 */
	public double getHighBound(){
		return this.highBound;
	}
	
	/**
	 * Setter to reset this WatchListItem's lower price bound.
	 */
	public void clearLowBound(){
		this.lowBound = null;
	}
	
	/**
	 * Setter to reset this WatchListItem's higher price bound.
	 */
	public void clearHighBound(){
		this.highBound = null;
	}
	
	/**
	 * Getter method this WatchListItem's watched equity.
	 * 
	 * @return This WatchListItem's watched equity.
	 */
	public Equity getEq(){
		return(this.eq);
	}
	
	/**
	 * Setter method for this object's state.
	 * 
	 * @param state The new state value to assign to this object's state attribute.
	 */
	public void setState(State state){
		this.state = state;
	}
	
	/**
	 * Getter method to obtain this WatchListItem's state enumeration.
	 * 
	 * @return This WatchListItem's state enumeration.
	 */
	public State getState(){
		return(this.state);
	}
	
	
	/**
	 * Generic toString method.
	 * 
	 * @return A string representation of a WatchListItem.
	 */
	@Override
	public String toString(){
		String str = this.eq.getName() + ", " + this.eq.getPrice();
		
		if ( this.highBound == null )
			str += ", High Bound Not Defined";
		else
			str += ", High Bound: " + this.highBound;
		
		if ( this.lowBound == null )
			str += ", Low Bound Not Defined";
		else
			str += ", Low Bound: " + this.lowBound;
		
		str += ", State: " + this.state;
		
		return str;
	}
}
