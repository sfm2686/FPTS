/**
 * 
 */
package Simulation;
import java.util.ArrayList;

/**
 * @author Sultan Mira, Hunter Caskey
 * 
 * This class represents the bear market strategy for simulating a portfolio
 * value over a specified time interval multiplied by time steps.
 * This type of strategy is decreasing.
 *
 */
public class BearMarketStrategy extends SimulationStrategy {

	/* (non-Javadoc)
	 * @see Simulation.SimulationStrategy#simulate()
	 */
	@Override
	public ArrayList<Double> simulate(double growthRate, 
			double value, int timeSteps, int interval) {
		
		super.setInitValue(value);
		super.setCurrentValue(value);
		ArrayList vals = new ArrayList<Double>();
		double result;
		for ( int i = 0; i < (timeSteps * interval); i ++ ){
			result = super.getCurrentValue() -
					(super.getCurrentValue() * Math.abs(growthRate));
			if (result < 0 )
				result = 0.0;
			super.setCurrentValue(Math.round(result * 100.0) / 100.0);
			vals.add(super.getCurrentValue());
		}
		return vals;
	}
}
