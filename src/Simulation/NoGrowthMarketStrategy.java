/**
 * 
 */
package Simulation;

import java.util.ArrayList;

/**
 * @author Sultan Mira, Hunter Caskey
 * 
 * This class represents the no-growth type of the simulation strategy.
 * This type of strategy does not simulate any growth or decrease in the
 * value of the portfolio.
 *
 */
public class NoGrowthMarketStrategy extends SimulationStrategy {

	/* (non-Javadoc)
	 * @see Simulation.SimulationStrategy#simulate()
	 */
	@Override
	public ArrayList<Double> simulate(double growthRate, 
			double value, int timeSteps, int interval) {
		
		super.setInitValue(value);
		super.setCurrentValue(value);
		ArrayList vals = new ArrayList<Double>();
		for ( int i = 0; i < (timeSteps * interval); i ++ )
			vals.add(super.getCurrentValue());
		return vals;	
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
