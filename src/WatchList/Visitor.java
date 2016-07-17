package WatchList;

/**
 * Taken from the GOF Visitor pattern. 
 * Visitor provides an interface for concrete visitors.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public interface Visitor {

	/**
	 * Each class that implements Visitor must have the visit method implemented. 
	 * This method should perform desired operations on the passed in WatchListItem.
	 * 
	 * @param w The WatchListItem that is part of the object structure being visited.
	 * @return A generic Java object, allows flexibility in defining concrete visitors.
	 */
	public Object visit(WatchListItem w);
}
