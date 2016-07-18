package Simulation;

import java.util.*;

/**
 * SimulationContext delegates between different strategies depending on the
 * input/choice of the user. Once that is done it will simulate and
 * display the results one result at a time. The client of the class is
 * able to determine what invokes the display of the next result in the
 * next time step. The class returns the next result every time the
 * getNextResult method is called. The class is also able to simulate
 * off a simulation result. If the client of the class does not
 * simulate again the current value will be reset to the initial.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public class SimulationContext {

	/****** Class Attributes ******/
	
	private SimulationStrategy strategy;
	private ArrayList<Double> simResults;
	private double growthRate;
	private double initValue;
	private double currentValue;
	private int steps;
	private int count;
	private int intervalNum;
	private String interval;

	/****** Class Methods ******/
	
	/**
	 * Constrcutor for a SimulationContext object.
	 * 
	 * @param growthRate The desired market growth rate condition to be used in the simulation.
	 * @param value The value of the portfolio to simulate on.
	 * @param timeSteps The number of steps to be simulated.
	 * @param interval The 'value' of each step to be simulated.
	 * @param type The type of simulations to choose: bear, bull, or no-growth.
	 */
	public SimulationContext(double growthRate, double value, int timeSteps, String interval, String type) {

		this.growthRate = growthRate / 100;
		this.currentValue = value;
		this.initValue = value;
		this.steps = timeSteps;
		this.intervalNum = 1;
		this.interval = interval;

		if (type.equalsIgnoreCase("bear"))
			this.strategy = new BearMarketStrategy();
		else if (type.equalsIgnoreCase("bull"))
			this.strategy = new BullMarketStrategy();
		else if (type.equalsIgnoreCase("no-growth"))
			this.strategy = new NoGrowthMarketStrategy();
	}

	/**
	 * getInterval simply returns the String representation of the chosen time interval. 
	 * These value include: year, month, and day.
	 * 
	 * @return The String representation of the chosen time interval. 
	 */
	public String getInterval() {
		return this.interval;
	}

	/**
	 * Accessor for the initial value of the portfolio passed in. 
	 * Primarily used in testing of this subsystem.
	 * 
	 * @return The initial value of the portfolio supplied upon creation of this object.
	 */
	public double getInitValue() {
		return this.initValue;
	}

	/**
	 * resetValue simply rolls the simulated value of the portfolio back to the 
	 * originally supplied value.
	 */
	public void resetValue() {
		this.currentValue = this.initValue;
	}

	/**
	 * getNextResult allows for walking through the steps of a simulation.
	 * 
	 * @return The next point (portfolio value) in the simulation.
	 */
	public double getNextResult() {
		if (this.count == this.simResults.size()) {
			this.count = -1;
			return (double) count;
		}
		return (double) this.simResults.get(this.count++);
	}
	
	/**
	 * howManyLeft simply determines how many time steps are left in the simulation
	 * when walking through it step by step.
	 * 
	 * @return The number of simulation steps not yet walked through.
	 */
	public int howManyLeft(){
		return this.simResults.size() - count;
	}

	/**
	 * newSim allows for further simulation of a portfolio but with new input values.
	 * 
	 * @param growthRate The new growth rate to simulate the market with.
	 * @param timeSteps The new time steps to simulate with.
	 * @param interval The new time interval to simulate with.
	 * @param type The chosen strategy algorithm to simulate using.
	 */
	public void newSim(double growthRate, int timeSteps, String interval, String type) {
		this.growthRate = growthRate / 100;
		this.steps = timeSteps;
		this.intervalNum = 1;
		this.interval = interval;
		this.currentValue = (double) this.simResults.get(this.simResults.size() - 1);

		if (type.equalsIgnoreCase("bear"))
			this.strategy = new BearMarketStrategy();
		else if (type.equalsIgnoreCase("bull"))
			this.strategy = new BullMarketStrategy();
		else if (type.equalsIgnoreCase("no-growth"))
			this.strategy = new NoGrowthMarketStrategy();
	}

	/**
	 * simulate populates the attribute meant for containing simulation results
	 * with the chosen strategy object. 
	 */
	public void simulate() {
		this.simResults = this.strategy.simulate(this.growthRate, this.currentValue, this.steps, this.intervalNum);
		this.count = 0;
	}

	/**
	 * Testing for Subsystem functionality.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// Testing ..
		int timeSteps;
		int c;
		double initValue;
		double growthRate;
		double val;
		String interval;
		String result;
		String type;

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter time interval(year, month, day): ");
		interval = input.next();
		System.out.print("Please enter time steps(integer): ");
		timeSteps = input.nextInt();
		System.out.print("Please enter the portfolio's value(double): ");
		initValue = input.nextDouble();
		System.out.print("Please enter the type of simulation(Bear, Bull, No-growth): ");
		type = input.next();
		System.out.print("Please enter the growth rate(%): ");
		growthRate = input.nextDouble();
		System.out.println("Assuming all of the information provided is correct, starting simulation...");

		SimulationContext test = new SimulationContext(growthRate, initValue, timeSteps, interval, type);
		test.simulate();

		System.out.println("\nPlease press 'Enter' to view next value");
		c = 1;
		val = test.getNextResult();
		while (val != -1) {
			if (input.nextLine().isEmpty())
				System.out.print(test.getInterval() + " " + c++ + ": " + val);
			else {
				System.out.println("Stopping simulation result display");
				break;
			}
			val = test.getNextResult();
		}

		while (true) {
			System.out.print("\nWould you like to simulate again(yes, no): ");
			result = input.next();
			if (result.equalsIgnoreCase("yes")) {
				System.out.print("Please enter time interval(year, month, day): ");
				interval = input.next();
				System.out.print("Please enter time steps(integer): ");
				timeSteps = input.nextInt();
				System.out.print("Please enter the type of simulation(Bear, Bull, No-growth): ");
				type = input.next();
				System.out.print("Please enter the growth rate(%): ");
				growthRate = input.nextDouble();
				test.newSim(growthRate, timeSteps, interval, type);
				test.simulate();
				System.out.println("\nPlease press 'Enter' to view next value");
				c = 1;
				val = test.getNextResult();
				while (val != -1) {
					if (input.nextLine().isEmpty())
						System.out.print(test.getInterval() + " " + c++ + ": " + val);
					else {
						System.out.println("Stopping simulation result display");
						break;
					}
					// val = test.getNextResult();
				}
			} else { // Reset value back..
				System.out.println("Your portfolio's value is reset back to: " + test.getInitValue());
				break;
			}
		}
		System.out.println("End of testing for simulationContext");
		input.close();
	}
}
