/**
 * 
 */
package Simulation;
import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
abstract class SimulationStrategy {

	//attrs to be used by subclasses
	private double growthRate;
	private double initValue;
	private double currentValue;
	private int steps;
	private int interval;
	
	public SimulationStrategy(){}
	//Abstract methods
	public abstract ArrayList<Double> simulate(double growthRate, 
			int timeSteps, int interval, int value);
	
	public abstract Double steps();
	
	//Getters and setters
}
