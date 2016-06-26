/**
 * 
 */
package Simulation;

import java.util.ArrayList;

/**
 * @author Sultan Mira
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

	/* (non-Javadoc)
	 * @see Simulation.SimulationStrategy#steps()
	 */
	@Override
	public Double steps() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
