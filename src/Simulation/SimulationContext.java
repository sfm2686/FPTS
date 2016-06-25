/**
 * 
 */
package Simulation;

import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimulationContext {

	private SimulationStrategy strategy;
	private Portfolio portfolio;
	
	//Helper methods
	private double getPortfolioValue(){
		return portfolio.getValue();
	}
	
	private void determineAlgo(String algo){
		
	}
	
	//Constructor
	public SimulationContext(double growthRate, 
			int timeSteps, TimeInterval interval,
			Portfolio portfolio){
		
		this.portfolio = portfolio;
		String placeHolder;
		if ( growthRate > 0 )
			placeHolder = "A strategy";
		else if ( growthRate < 0 )
			placeHolder = "Another strategy";
		else
			placeHolder = "No-growth strategy";
	}1
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
