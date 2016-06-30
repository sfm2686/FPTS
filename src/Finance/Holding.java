package Finance;


/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class defines what a subclass of type holding should have.
 * Cash accounts, Equities, and Indices extend this class.
 *
 * Holding is the common interface for CashhAcct and Equity.
 */
public interface Holding {
	
	public double getValue();
}
