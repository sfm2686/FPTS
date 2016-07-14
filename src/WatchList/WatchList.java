/**
 * 
 */
package WatchList;

import java.io.Serializable;
import java.util.*;

import Finance.Stock;
import Market.Market;
import Market.YahooAPI;


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
	
	public void refresh(){
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
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
