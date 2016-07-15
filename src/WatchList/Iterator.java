/**
 * 
 */
package WatchList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public interface Iterator {

	public WatchListItem first();
	public void next();
	public WatchListItem currentItem();
	public boolean isDone();
}
