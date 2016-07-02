/**
 * 
 */
package Simulation;

import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class defines the methods that all strategy classes should
 *          implement or could use from their super, this class.
 *
 */
abstract class SimulationStrategy {

	private double initValue;
	private double currentValue;

	public SimulationStrategy() {
	}

	// Getters, setters
	protected double getInitValue() {
		return this.initValue;
	}

	protected double getCurrentValue() {
		return this.currentValue;
	}

	protected void setInitValue(double n) {
		this.initValue = n;
	}

	protected void setCurrentValue(double n) {
		this.currentValue = n;
	}

	// Abstract methods
	public abstract ArrayList<Double> simulate(double growthRate, double value, int timeSteps, int interval);

}
