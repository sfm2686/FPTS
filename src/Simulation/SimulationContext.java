/**
 * 
 */
package Simulation;
import Finance.Portfolio;
import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * Works with an input value not portfolio.
 * Input value should be replaced with a portfolio object when portfolio is 
 * Implemented.
 *
 */
public class SimulationContext {

	private SimulationStrategy strategy;
	private ArrayList simResults;
	private double growthRate;
	private double initValue;
	private double currentValue;
	private int steps;
	private int count;
	private int intervalNum;
	private String interval;
	
	/*
	//Portfolio commented out until it is implemented
	//private Portfolio portfolio;
	
	//Helper methods
	private double getPortfolioValue(){
		return portfolio.getValue();
	}
	*/
	
	//Constructor
	public SimulationContext(double growthRate, double value, 
			int timeSteps, String interval){
		
		//this.portfolio = portfolio;
		this.growthRate = growthRate / 100;
		this.currentValue = value;
		this.initValue = value;
		this.steps = timeSteps;
		this.intervalNum = 1;
		this.interval = interval;
		
		if ( growthRate > 0 )
			this.strategy = new BearMarketStrategy();
		else if ( growthRate < 0 )
			this.strategy = new BullMarketStrategy();
		else
			this.strategy = new NoGrowthMarketStrategy();		
	}
	
	public String getInterval(){
		return this.interval;
	}
	
	//Testing purposes..
	public double getInitValue(){
		return this.initValue;
	}
	
	public void restValue(){
		this.currentValue = this.initValue;
	}
	
	public double getNextResult(){
		if ( this.count == this.simResults.size() ){
			this.count = -1;
			return (double) count;
		}
		return (double) this.simResults.get(this.count++);
	}
	
	public void newSim(double growthRate, 
			int timeSteps, String interval){
		this.growthRate = growthRate / 100;
		this.steps = timeSteps;
		this.intervalNum = 1;
		this.interval = interval;
		this.currentValue = (double) this.simResults.get(this.simResults.size() - 1);
		
		if ( growthRate > 0 )
			this.strategy = new BearMarketStrategy();
		else if ( growthRate < 0 )
			this.strategy = new BullMarketStrategy();
		else
			this.strategy = new NoGrowthMarketStrategy();
	}
	
	public void simulate(){
		this.simResults = this.strategy.simulate(this.growthRate, this.currentValue,
				this.steps, this.intervalNum);
		this.count = 0;
		
		//Testing purposes ..
		System.out.println(this.simResults);
	}
	
	
	//-------------------------------------------------------------------------
	//                            MAIN
	//-------------------------------------------------------------------------
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Testing .. 
		int timeSteps;
		int c;
		double initValue;
		double growthRate;
		double val;
		String interval;
		String result;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter time interval(year, month, day): ");
		interval = input.next();
		System.out.print("Please enter time steps(integer): ");
		timeSteps = input.nextInt();
		System.out.print("Please enter the portfolio's value(double): ");
		initValue = input.nextDouble();
		System.out.print("Please enter the growth rate(%): ");
		growthRate = input.nextDouble();
		System.out.println("Assuming all of the information provided is correct, starting simulation...");
		
		SimulationContext test = new SimulationContext(growthRate, initValue, timeSteps, interval);
		test.simulate();
		
		System.out.println("\nPlease press 'Enter' to view next value");
		c = 1;
		val = test.getNextResult();
		while ( val != -1 ){
			if ( input.nextLine().isEmpty() )
				System.out.print(test.getInterval() + " " + c ++ + ": " + val);
			else {
				System.out.println("Stopping simulation result display");
				break;
			}
			val = test.getNextResult();
		}
		
		
		while ( true ){
			System.out.print("\nWould you like to simulate again(yes, no): ");
			result = input.next();
			if ( result.equalsIgnoreCase("yes") ){
				System.out.print("Please enter time interval(year, month, day): ");
				interval = input.next();
				System.out.print("Please enter time steps(integer): ");
				timeSteps = input.nextInt();
				System.out.print("Please enter the growth rate(%): ");
				growthRate = input.nextDouble();
				test.newSim(growthRate, timeSteps, interval);
				test.simulate();
				System.out.println("\nPlease press 'Enter' to view next value");
				c = 1;
				val = test.getNextResult();
				while ( val != -1 ){
					if ( input.nextLine().isEmpty() )
						System.out.print(test.getInterval() + " " + c ++ + ": " + val);
					else {
						System.out.println("Stopping simulation result display");
						break;
					}
					val = test.getNextResult();
				}
				
			}
			else { //Reset value back..
				System.out.println("Your portfolio's value is reset back to: " +
						test.getInitValue());
				break;
			}

		}
		System.out.println("End of testing for simulationContext");
	
	}

}
