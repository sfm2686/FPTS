/**
 * 
 */
package WatchList;

import java.io.Serializable;

import Finance.Equity;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchListItem implements Serializable{

	
	public enum State {
				 //Comments are for the GUI, those are JLabel background colors..
		NowHigh, //Dark Red
		NowLow,  //Dark Green
		WasHigh, //Light Red
		WasLow,  //Light green
		Normal   //White
	}
	
	private Equity eq;
	private State state;
	private Double lowBound  = null; 
	private Double highBound = null;
	
	public WatchListItem(Equity eq){
		this.eq = eq;
	}
	
	public void setHighBound(double n){
		this.highBound = n;
	}
	
	public void setLowBound(double n){
		this.lowBound = n;
	}
	
	protected void accept(Visitor v){
		v.visit(this);
	}
	
	public double getLowBound(){
		return this.lowBound;
	}
	
	public double getHighBound(){
		return this.highBound;
	}
	
	public void clearLowBound(){
		this.lowBound = null;
	}
	
	public void clearHighBound(){
		this.highBound = null;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
