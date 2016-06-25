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
	protected double growthRate;
	protected double initValue;
	protected double currentValue;
	protected int steps;
	protected int interval;
	
	//Abstract methods
	public abstract ArrayList<Double> simulate();
	public abstract Double steps();
	
	//Getters and setters
}
