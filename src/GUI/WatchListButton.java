/**
 * 
 */
package GUI;

import javax.swing.*;

import WatchList.WatchListItem;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchListButton extends JButton {
	
	private WatchListItem item;
	
	public WatchListButton(String name, WatchListItem item){
		super(name);
		this.item = item;
	}

}
