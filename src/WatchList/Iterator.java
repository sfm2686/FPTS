package WatchList;

/**
 * Stock GOF Visitor pattern.
 * This interface defines the behavior for all concrete iterators to implement.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public interface Iterator {

	public WatchListItem first();
	public void next();
	public WatchListItem currentItem();
	public boolean isDone();
}
