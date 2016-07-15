package WatchList;

import Market.Market;

/**
 * 
 */

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class GetPriceVisitor implements Visitor {

	@Override
	public Object visit(WatchListItem w) {
		return (w.getEq().getPrice());
	}
}
