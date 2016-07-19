/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

import Finance.User;
import WatchList.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchManager extends MainPanel {

	/**
	 * Create the panel.
	 */
	public WatchManager(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.WEST);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}


	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Manage your watch list"));
		return panel;
	}

	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		WatchListItem item;
		Iterator it = getUser().getWatchList().getIterator();
		JPanel temp;
		while ( !it.isDone() ){
			item = it.currentItem();
			
			temp = new JPanel();
			temp.setLayout(new FlowLayout());
			temp.add(new JLabel(item.toString()));
			
			it.next();
		}
		
		return panel;
	}

	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		return panel;
	}


	@Override
	protected void assign() {
		// TODO Auto-generated method stub
		
	}

}
