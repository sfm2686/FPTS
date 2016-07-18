package Simulation;

import java.util.*;

/**
 * SimulationStrategy defines the methods that all strategy classes must
 * implement or could use from their super, this class.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
abstract class SimulationStrategy {

	/****** Class Attributes ******/
	private double initValue;
	private double currentValue;

	/****** Class Methods ******/

	public SimulationStrategy() {}

	/**
	 * Accessor for the initial value of a simulation.
	 * 
	 * @return A double value as the total value of a portfolio to simulate.
	 */
	protected double getInitValue() {
		return this.initValue;
	}

	/**
	 * Accessor for the current value of a portfolio, during or after a simulation.
	 * 
	 * @return The current value of the portfolio in the simulation.
	 */
	protected double getCurrentValue() {
		return this.currentValue;
	}

	/**
	 * Mutator for the initial value of a portfolio.
	 * 
	 * @param n The new initial value of a portfolio to use in a simulation.
	 */
	protected void setInitValue(double n) {
		this.initValue = n;
	}

	/**
	 * Mutator for the current value of a portfolio.
	 * 
	 * @param n The new, current value of a portfolio assigned during a simulation.
	 */
	protected void setCurrentValue(double n) {
		this.currentValue = n;
	}

	/**
	 * Given the supplied parameters, simulate the growth/decay of a portfolio
	 * and return the simulation results as a collection of points.
	 * 
	 * @param growthRate The desired growth rate for a portfolio.
	 * @param value The initial value of the portfolio to simulate.
	 * @param timeSteps The total number of steps for the simulation to run.
	 * @param interval The value of each time step, ex. a day, month, or year.
	 * @return An ArrayList containing the value of the portfolio for each time step in a simulation.
	 */
	public abstract ArrayList<Double> simulate(double growthRate, double value, int timeSteps, int interval);

}
