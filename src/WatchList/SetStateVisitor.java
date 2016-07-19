package WatchList;

/**
 * Concrete Visitor to reassess and reassign the state variable within the WatchListItem
 * class.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public class SetStateVisitor implements Visitor {

	/**
	 * visit is defined in the Visitor interface. This method analyzes the current state
	 * of the visited element, and reassigns it based on the trigger prices and the current
	 * prices of the Equity that the element has encapsulated.
	 * 
	 * @param w The current WatchListItem being visited in the object structure.
	 * @return A generic object, null in this case since the purpose of this visitor is to mutate, not access.
	 */
	@Override
	public Object visit(WatchListItem w) {
		Double high;
		Double low;
		WatchListItem.State state = w.getState();
		try{
			high = w.getHighBound();
		}
		catch(NullPointerException e){
			high = null;
		}
		try{
			low = w.getLowBound();
		}
		catch(NullPointerException e){
			low = null;
		}
		
		Double price = w.getEq().getPrice();
		
		// If there is both a high and low trigger defined
		if(high != null && low != null){
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
		}
		// If there is just a high trigger defines
		else if (high != null && low == null){
			if (price > high){
				w.setState(WatchListItem.State.NowHigh);
			}
			else if (state == WatchListItem.State.NowHigh && (price < high)){
				w.setState(WatchListItem.State.WasHigh);
			}
			else{
				w.setState(WatchListItem.State.Normal);
			}
		}
		// If there is just a low trigger defines
		else if (low != null && high == null){
			if (price < low){
				w.setState(WatchListItem.State.NowLow);
			}
			else if(state == WatchListItem.State.NowLow && (price > low)){
				w.setState(WatchListItem.State.WasLow);
			}
			else{
				w.setState(WatchListItem.State.Normal);
			}
		}
		// If there aren't any triggers defined
		else{
			w.setState(WatchListItem.State.Normal);
		}
	
		return null;
	}
}
