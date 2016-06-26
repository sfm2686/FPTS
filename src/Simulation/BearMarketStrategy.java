/**
 * 
 */
package Simulation;
import java.util.ArrayList;

/**
 * @author Sultan Mira
 *
 */
public class BearMarketStrategy extends SimulationStrategy {

	/* (non-Javadoc)
	 * @see Simulation.SimulationStrategy#simulate()
	 */
	
	public ArrayList<Double> simulate(double growthRate, 
			double value, int timeSteps, int interval) {
		
		super.setInitValue(value);
		super.setCurrentValue(value);
		ArrayList vals = new ArrayList<Double>();
		for ( int i = 0; i < (timeSteps * interval); i ++ ){
			super.setCurrentValue(super.getCurrentValue() * growthRate);
			vals.add(super.getCurrentValue());
		}
		return vals;
	}

	/* (non-Javadoc)
	 * @see Simulation.SimulationStrategy#steps()
	 */
	@Override
	public Double steps() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
