/**
 * 
 */
package Simulation;
import java.util.*;

/**
 * @author Sultan Mira, Hunter Caskey
 * 
 * This class represents the bull market strategy for simulating a portfolio's
 * value over a specified time interval.
 * This type of strategy is increasing.
 *
 */
public class BullMarketStrategy extends SimulationStrategy {

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
				result = super.getCurrentValue() +
						(super.getCurrentValue() * growthRate);
				super.setCurrentValue(Math.round(result * 100.0) / 100.0);
				vals.add(super.getCurrentValue());
			}
		return vals;
		
	}

}
