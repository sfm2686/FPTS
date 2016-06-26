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

	//commented out for now, might not use
	/*
	//attrs to be used by subclasses
	private double growthRate;
	private int steps;
	private int interval;
	*/
	
	private double initValue;
	private double currentValue;
	
	public SimulationStrategy(){}
	
	//Getters, setters
	protected double getInitValue(){
		return this.initValue;
	}
	
	protected double getCurrentValue(){
		return this.currentValue;
	}
	
	protected void setInitValue(double n){
		this.initValue = n;
	}
	
	protected void setCurrentValue(double n){
		this.currentValue = n;
	}
	//Abstract methods
	public abstract ArrayList<Double> simulate(double growthRate, 
			double value, int timeSteps, int interval);
	
}
