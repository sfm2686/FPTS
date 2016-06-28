/**
 * 
 */
package Finance;

import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class Equity implements Holding {

	
	abstract double getPrice( String name );
	
	//CHILD RELATED OPERATIONS
	
	abstract void removeChild(Stock child);
	abstract void addChild(Stock child);
	abstract ArrayList<Stock> getChildren();
	
}
