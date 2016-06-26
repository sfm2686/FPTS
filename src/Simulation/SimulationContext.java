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
	private ArrayList simResults;
	private double growthRate;
	private double initValue;
	private double currentValue;
	private int steps;
	private int intervalNum;
	private String interval;
	
	private int count;
	
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
	
	private int determineInterval(String i){
		
		switch(i.toLowerCase()) {
		case "day"  : return 7;
		case "month": return 30;
		case "year" : return 365;
		}
		return -1;
	}
	
	private double getNextResult(){
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
		System.out.println("NEWSIM() IS CALLED ---------------------------");
	}
	
	public ArrayList<Double> simulate(){
		this.simResults = this.strategy.simulate(this.growthRate, this.currentValue,
				this.steps, this.intervalNum);
		System.out.println("SIMULATE() IS CALLED ...........................");
		System.out.println(this.simResults);
		return this.simResults;
	}
	
	public void testingDisplay(){
		Scanner scan = new Scanner(System.in);
		while ( this.count != -1 ){
			if ( scan.nextLine().isEmpty() && this.count != -1)
				System.out.print(this.getNextResult());
			else
				System.out.println("Simulation done.");
		}
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
		double initValue;
		double growthRate;
		String interval;
		String result;
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter time interval(year, month, day): ");
		interval = input.nextLine();
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
		test.testingDisplay();
		
		while ( true ){
			System.out.println("\nWould you like to simulate again?");
			System.out.print("Please type 'Yes' or 'No': ");
			result = input.nextLine();
			System.out.println("YOU ENTERED " + result);
			System.out.print("Please enter time interval(year, month, day): ");
			interval = input.nextLine();
			System.out.print("Please enter time steps(integer): ");
			timeSteps = input.nextInt();
			System.out.print("Please enter the growth rate(%): ");
			growthRate = input.nextDouble();
			test.newSim(growthRate, timeSteps, interval);
			test.simulate();
			System.out.println("\nPlease press 'Enter' to view next value");
			test.testingDisplay();

		}
		//System.out.println("End of testing for simulationContext");
	
	}

}
