/**
 * 
 */
package Simulation;

/**
 * @author muro
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
