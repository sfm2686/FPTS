/**
 * 
 */
package WatchList;

import java.io.Serializable;
import java.util.*;

import Finance.*;
import Market.*;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchList extends Observable implements Serializable{


	private ArrayList<WatchListItem> items;
	
	public WatchList(int time){
		//THREAD START..
		Alarm alarm = new Alarm(time);
		alarm.start();
		items = new ArrayList<WatchListItem>();
	}
	
	public void addWatchListItem(WatchListItem n){
		this.items.add(n);
	}
	
	public void removeWatchListItem(WatchListItem n){
		this.items.remove(n);
	}
	
	
	public WLPriorityIterator getIterator(){
		return new WLPriorityIterator();
	}
	
	public void refresh(){
		
		Iterator iter = this.getIterator();
		Visitor v1 = new SetStateVisitor();
		
		while (!iter.isDone()){
			iter.currentItem().accept(v1);
			iter.next();
		}
		
		setChanged();
		notifyObservers();
	}
	
	//----------------------------------ALARM-----------------------------------//
	private class Alarm extends Thread {

		private int timeInterval;
		
		public Alarm(int timeInterval){
			this.timeInterval = timeInterval;
		}
		
		@Override
		public void run(){
			while ( true ){
				try {
					sleep(this.timeInterval * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refresh();
			}
		}
		
	}
	//----------------------------------ALARM-----------------------------------//
	
	
	//----------------------------------ITERATOR-----------------------------------//
	private class WLPriorityIterator implements Iterator{

		private int currentIndex = 0;
		private ArrayList<WatchListItem> sorted;
		
		public WLPriorityIterator() {
			sorted = new ArrayList<WatchListItem>();
		}
		
		@Override
		public WatchListItem first() {
			return this.sorted.get(0);
		}


		@Override
		public void next() {
			this.currentIndex ++;
		}


		@Override
		public WatchListItem currentItem() {
			if ( !isDone() )
				return this.sorted.get(this.currentIndex);
			return null;
		}

		@Override
		public boolean isDone() {
			return ( this.currentIndex == this.sorted.size() );
		}
		
	}
	//----------------------------------ITERATOR-----------------------------------//

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
