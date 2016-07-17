package Simulation;

import java.util.ArrayList;

/**
 * NoGrowthMarketStategy represents the no-growth type of the simulation strategy.
 * This type of strategy does not simulate any growth or decrease in the
 * value of the portfolio.
 * 
 * @author Sultan Mira, Hunter Caskey
 */
public class NoGrowthMarketStrategy extends SimulationStrategy {

	/**
	 * simulate has the responsibility of determining the results of a portoflio
	 * simulation with the supplied arguments.
	 * 
	 * @param growthRate The desired growth for this simulation, 0 in this case.
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
		for (int i = 0; i < (timeSteps * interval); i++)
			vals.add(super.getCurrentValue());
		return vals;
	}
}
