/**
 * 
 */
package WatchList;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SetStateVisitor implements Visitor {

	@Override
	public Object visit(WatchListItem w) {
		WatchListItem.State state = w.getState();
		double high = w.getHighBound();
		double low = w.getLowBound();
		double price = w.getEq().getPrice();
		
		if (price > high)
			w.setState(WatchListItem.State.NowHigh);
		else if (price < low)
			w.setState(WatchListItem.State.NowLow);
		else if (state == WatchListItem.State.NowHigh && (price < high && price > low))
			w.setState(WatchListItem.State.WasHigh);
		else if (state == WatchListItem.State.NowLow && (price < high && price > low))
			w.setState(WatchListItem.State.WasLow);
		else
			w.setState(WatchListItem.State.Normal);
		
		return null;
	}
}
