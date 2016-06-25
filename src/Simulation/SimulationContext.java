/**
 * 
 */
package Simulation;
import Finance.Portfolio;
import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimulationContext {

	private SimulationStrategy strategy;
	
	/*
	//Portfolio commented out until it is implemented
	//private Portfolio portfolio;
	
	//Helper methods
	private double getPortfolioValue(){
		return portfolio.getValue();
	}
	*/
	
	private int determineInterval(String i){
		
		switch(i.toLowerCase()) {
		case "day"  : return 7;
		case "month": return 30;
		case "year" : return 365;
		}
		return -1;
	}
	
	//Constructor
	public SimulationContext(double growthRate, double value, 
			int timeSteps, String interval){
		
		//this.portfolio = portfolio;
		
		if ( growthRate > 0 )
			this.strategy = new BearMarketStrategy();
		else if ( growthRate < 0 )
			this.strategy = new BullMarketStrategy();
		else
			this.strategy = new NoGrowthMarketStrategy();
		
		int time = determineInterval(interval);
		
		//this.strategy.simulate(growthRate, timeSteps, time, value);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Testing .. 
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a value for the portfolio to simulate");
		System.out.println("Options are:\n'year'\n'month'\n'day'");
		System.out.print("Taking input: ");
		String interval = input.nextLine();
		System.out.print("Please enter an for time steps(integer): ");
		int timeSteps = input.nextInt();
		System.out.print("Please enter the portfolio's value(double): ");
		double initValue = input.nextDouble();
		System.out.print("Please enter the growth rate(double): ");
		double growthRate = input.nextDouble();
		System.out.println("Assuming all of the information provided is correct, starting simulation...");
		
		SimulationContext test = new SimulationContext(growthRate, initValue, timeSteps, interval);
		
		/*
		System.out.println("Testing input:");
		System.out.println("Interval   => " + interval);
		System.out.println("timeSteps  => " + timeSteps);
		System.out.println("initValue  => " + initValue);
		System.out.println("growthRate => " + growthRate);
		*/
		
	
	}

}
