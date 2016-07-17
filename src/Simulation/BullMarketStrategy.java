package Simulation;

import java.util.*;

/**
 * BullMarketStrategy represents the bull market strategy for simulating a
 * portfolio's value over a specified time interval. This type of
 * strategy is increasing.
 *         
 * @author Sultan Mira, Hunter Caskey
 */
public class BullMarketStrategy extends SimulationStrategy {

	/**
	 * simulate has the responsibility of determining the results of a portoflio
	 * simulation with the supplied arguments.
	 * 
	 * @param growthRate The desired positive growth for this simulation.
	 * @param value The initial value of the portfolio being simulated on.
	 * @param timeSteps The number of steps that we would like to simulate for.
	 * @param interval The total value of each timeStep.
	 * @return An ArrayList containing the results of this simulation.
	 */
	@Override
	public ArrayList<Double> simulate(double growthRate, double value, int timeSteps, int interval) {

		super.setInitValue(value);
		super.setCurrentValue(value);
		ArrayList<Double> vals = new ArrayList<Double>();
		double result;
		for (int i = 0; i < (timeSteps * interval); i++) {
			result = super.getCurrentValue() + (super.getCurrentValue() * growthRate);
			super.setCurrentValue(Math.round(result * 100.0) / 100.0);
			vals.add(super.getCurrentValue());
		}
		return vals;

	}

}
