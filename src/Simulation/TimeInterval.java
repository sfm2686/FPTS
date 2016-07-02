/**
 * 
 */
package Simulation;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class was initially set to map the time interval speicifed in
 *          SimulationContext to a value but it was not used when
 *          SimulationContext was implemented. This enum might be used for
 *          future refactoring/modifications. Which is why it will not be
 *          deleted.
 *
 */
public enum TimeInterval {
	day, month, year;

	// Maps to the enum, returns -1 if type not found.
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
