package WatchList;

import Market.Market;

/**
 * Concrete Visitor to access the price of an equity within the WatchListItem class.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public class GetPriceVisitor implements Visitor {

	/**
	 * visit is defined in the Visitor interface. This method simply retrieves the 
	 * current price of the equity that is encapsulated within a WatchListItem object. 
	 * 
	 * @param w The current WatchListItem being visited in the object structure.
	 * @return A generic object, a Double in this case to reflect the equity's price.
	 */
	@Override
	public Object visit(WatchListItem w) {
		return (w.getEq().getPrice());
	}
}
