/**
 * 
 */
package GUI;

import java.awt.TextArea;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchList extends JPanel implements Observer {

	/**
	 * Create the panel.
	 */
	public WatchList() {
		
		TextArea area = new TextArea(30, 5);
		JScrollPane list = new JScrollPane(area);
		
		this.add(list);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
