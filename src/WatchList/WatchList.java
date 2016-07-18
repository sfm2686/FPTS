package WatchList;

import java.io.Serializable;
import java.util.Observable;
import java.util.ArrayList;

/**
 * The WatchList class maintains a collection of Equities and associated trigger 
 * values for each Equity, encapsulated as WatchListItems. Upon instantiation of this
 * class, a time interval (supplied by the user) is given to an inner thread class, whose
 * only responsibility is to alert the WatchList to update. This class also has a
 * custom iterator implemented, which traverses the collection in priority order.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class WatchList extends Observable implements Serializable{

	// Maintain a collection of WatchListItems
	private ArrayList<WatchListItem> items;
	
	/**
	 * Constructor for the WatchList class. Upon instantiation it runs a thread
	 * responsible for timing updates. 
	 * 
	 * @param time The user-specified time-interval for updating the WatchList.
	 */
	public WatchList(int time){
		items = new ArrayList<WatchListItem>();
		// Start the thread
		Alarm alarm = new Alarm(time);
		alarm.start();
	}
	
	/**
	 * Mutator method for adding a WatchListItem to the WatchList.
	 * @param n The element to add to the WatchList.
	 */
	public void addWatchListItem(WatchListItem n){
		this.items.add(n);
	}
	
	/**
	 * Mutator method for removing a WatchListItem from the WatchList.
	 * @param n The element to remove from the WatchList.
	 */
	public void removeWatchListItem(WatchListItem n){
		this.items.remove(n);
	}
	
	/**
	 * Pseudo-factory method for obtaining a WatchList's iterator.
	 * @return The iterator for WatchList.
	 */
	public WLPriorityIterator getIterator(){
		return new WLPriorityIterator();
	}
	
	/**
	 * refresh is called by an Alaram thread to instruct the WatchList to update the 
	 * state of it's WatchListItems.
	 */
	public void refresh(){
		
		Iterator iter = this.getIterator();
		Visitor v1 = new SetStateVisitor();
		
		while (!iter.isDone()){
			iter.currentItem().accept(v1);
			iter.next();
		}
		
		// Notify the GUI that the WatchListItems have updated.
		setChanged();
		notifyObservers();
	}
	
	//----------------------------------ALARM-----------------------------------//
	
	/**
	 * Private inner class whose responsibility is to update 
	 * the WatchList at the user defined interval.
	 * 
	 * @authors Sultan Mira, Hunter Caskey
	 */
	private class Alarm extends Thread {

		private int timeInterval;
		
		/**
		 * Constructor for the Alarm class.
		 * 
		 * @param timeInterval The interval that specifies how often the thread
		 * 					   should update the WatchList object.
		 */
		public Alarm(int timeInterval){
			this.timeInterval = timeInterval;
		}
		
		/**
		 * Specified by extending the thread class. 
		 */
		@Override
		public void run(){
			while ( true ){
				try {
					sleep(this.timeInterval * 1000); // Sleep for however many seconds the user specified.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refresh();
			}
		}
		
	}
	//----------------------------------ALARM-----------------------------------//
	
	
	//----------------------------------ITERATOR-----------------------------------//
	
	/**
	 * WLPriorityIterator is a private iterator for the WatchList class. 
	 * 
	 * The collection to be iterated over is traversed by priority of the element's 
	 * states.
	 * 
	 * @authors Sultan Mira, Hunter Caskey
	 */
	public class WLPriorityIterator implements Iterator{

		//**** Class Attributes ****//
		private int currentIndex = 0;
		private ArrayList<WatchListItem> sorted;
		
		/**
		 * Constructor for the WatchList's Iterator.
		 * 
		 * Initializes this class's private 'copy' of the data being iterated over, 
		 * and then uses the WatchList's items collection to populated the sorted variable.
		 */
		@SuppressWarnings(value = { "unchecked" })
		public WLPriorityIterator() {
			sorted = new ArrayList<WatchListItem>();
			this.sort((ArrayList<WatchListItem>) items.clone());
		}
		
		/**
		 * Stock method from GOF Iterator pattern. 
		 * 
		 * first returns the very first element in the structure that is being iterated over.
		 * 
		 * @return The element in in the collection corresponding to index 0.
		 */
		@Override
		public WatchListItem first() {
			return this.sorted.get(0);
		}


		/**
		 * Stock method from GOF Iterator pattern. 
		 * 
		 * next increases the index tracker of the list we are iterating over by one.
		 */
		@Override
		public void next() {
			this.currentIndex ++;
		}


		/**
		 * Stock method from GOF Iterator pattern.
		 *  
		 * currentItem simply returns the item corresponding to the current position
		 * of the iteration.
		 * 
		 * @return A WatchListItem.
		 */
		@Override
		public WatchListItem currentItem() {
			if ( !isDone() )
				return this.sorted.get(this.currentIndex);
			return null;
		}

		/**
		 * Stock method from GOF Iterator pattern. 
		 * 
		 * isDone checks whether the iterator object has fully gone through the 
		 * object structure being navigated.
		 * 
		 * @return A boolean value, true if the iteration is complete.
		 */
		@Override
		public boolean isDone() {
			return ( this.currentIndex == this.sorted.size() );
		}
		
		/**
		 * Helper function for sorting a copy of WatchList's collection of
		 * WatchListItems. This function sorts based upon priority, using the nextState 
		 * helper method.
		 * 
		 * @param copy A shallow copy of the WatchList's collections. 
		 */
		private void sort(ArrayList<WatchListItem> copy){
			WatchListItem.State state = WatchListItem.State.NowHigh;			
			int tempIndex;
			while(copy.size() > 0){
				tempIndex = -1;
				for(WatchListItem item : copy){
					if(item.getState() == state){
						tempIndex = copy.indexOf(item);
						break;
					}
				}
				if(tempIndex == -1){
					state = nextState(state);
				}
				else{
					sorted.add(copy.get(tempIndex));
					copy.remove(tempIndex);
				}
			}
		}
		
		/**
		 * nextState aids in the sorting algorithm by deciding what the next appropriate
		 * state to sort is. The WatchListItems are sorted by their state, and that order is:
		 * NowHigh, NowLow, WasHigh, WasLow, and Normal.
		 * 
		 * @param state The current type of WatchListItem's state that is being sorted.
		 * @return The state of WatchListItems to sort next.
		 */
		private WatchListItem.State nextState(WatchListItem.State state){
			if(state == WatchListItem.State.NowHigh){
				return(WatchListItem.State.NowLow);
			}
			else if(state == WatchListItem.State.NowLow){
				return(WatchListItem.State.WasHigh);
			}
			else if(state == WatchListItem.State.WasHigh){
				return(WatchListItem.State.WasLow);
			}			
			else if(state == WatchListItem.State.WasLow){
				return(WatchListItem.State.Normal);
			}
			else{
				return null;
			}
		}
		
	}
	
	//----------------------------------ITERATOR-----------------------------------//
	
}
