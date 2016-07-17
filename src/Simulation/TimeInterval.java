package Simulation;

/**
 * This class was initially set to map the time interval specified in
 * SimulationContext to a value but it was not used when
 * SimulationContext was implemented. This enum might be used for
 * future refactoring/modifications. Which is why it will not be
 * deleted.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
public enum TimeInterval {
	day, 
	month, 
	year;
	
	/**
	 * Maps the to the enumeration corresponding integer value. 
	 * 
	 * @param t The input enumeration; either day, month, or year.
	 * @return The corresponding integer value, 1, 30, 365, or -1 for failure.
	 */
	public int mapInterval(TimeInterval t) {

		int time = -1;
		switch (t) {
			case day:
				time = 7;
				break;
			case month:
				time = 30;
				break;
			case year:
				time = 365;
				break;
		}
		return time;
	}
}
