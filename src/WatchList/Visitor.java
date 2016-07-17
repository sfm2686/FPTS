package WatchList;

/**
 * Taken from the GOF Visitor pattern. 
 * Visitor provides an interface for concrete visitors.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public interface Visitor {

	public Object visit(WatchListItem w);
}
