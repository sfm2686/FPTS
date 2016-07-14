/**
 * 
 */
package GUI;

import java.awt.Dimension;
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
		this.setBorder(
	            BorderFactory.createTitledBorder("Watch List"));
		TextArea area = new TextArea();
		JScrollPane list = new JScrollPane(area);
		this.add(list);

		this.setPreferredSize(new Dimension(100, 100));
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
