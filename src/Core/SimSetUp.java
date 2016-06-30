/**
 * 
 */
package Core;

import java.util.Scanner;

import Simulation.SimulationContext;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 *This state prompts the user to input the information needed to apply a 
 *simulation on the current portfolio that the user selected in another state.
 *The state also asks the user for simulation algo type and passes it back to the
 *SimulationContext in the Context object that it has.
 */
public class SimSetUp extends State {
	
	private int id = 5;

	/**
	 * @param context
	 */
	public SimSetUp(Context context) {
		super(context);	}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Simulation Setup-----\n");
		
		int timeSteps;
		double initValue;
		double growthRate;
		String interval;
		String type;
		
		Scanner input = new Scanner(System.in);

		//Taking interval as a String
		System.out.print("Please enter time interval(year, month, day): ");
		interval = input.next();
		while ( !interval.equalsIgnoreCase("day") && !interval.equalsIgnoreCase("month") &&
				!interval.equalsIgnoreCase("year") ){
			System.out.println("Invalid input, please try again");
			System.out.print("Please enter time interval(year, month, day): ");
			interval = input.next();
		}
		
		//Taking time-steps as an int
		System.out.print("Please enter time steps(positive integer): ");
		timeSteps = input.nextInt();
		while ( timeSteps < 0 ){
			System.out.println("Invalid input, please try again");
			System.out.print("Please enter time steps(positive integer): ");
			timeSteps = input.nextInt();
		}
		
		//Setting init value to portfolio's value, double
		initValue = getContext().getPort().getPortfolioValue();
		
		//Taking simulation type as a String
		System.out.print("Please enter the type of simulation(Bear, Bull, No-growth): ");
		type = input.next();
		while ( !type.equalsIgnoreCase("bear") && !type.equalsIgnoreCase("bull") &&
				!type.equalsIgnoreCase("no-growth") ){
			System.out.println("Invalid input, please try again");
			System.out.print("Please enter the type of simulation(Bear, Bull, No-growth): ");
			type = input.next();
		}
		//No growth rate is needed if simulation type is no-growth
		if ( !type.equalsIgnoreCase("no-growth") ){
			System.out.print("Please enter a positive growth rate(%): ");
			growthRate = input.nextDouble();
			while ( growthRate < 0 ){
				System.out.println("Invalid input, please try again");
				System.out.print("Please enter a positive growth rate(%): ");
				growthRate = input.nextDouble();
			}
		}
		else
			growthRate = 0;
		
		getContext().setSim(growthRate, timeSteps, interval, type);
		getContext().getSim().simulate();
		setNext(0);
	}


	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		return this.id;
	}
}
