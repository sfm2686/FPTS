/**
 * 
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import javafx.scene.control.ScrollBar;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchList extends JPanel implements Observer {

	private ArrayList<WItemLabel> witems = new ArrayList<WItemLabel>();
	private JList labels = new JList();
	
	/**
	 * Create the panel.
	 */
	public WatchList() {
		this.setBorder(
	            BorderFactory.createTitledBorder("Watch List"));
		
		DefaultListModel model = new DefaultListModel();
		for ( int i = 0; i < 40; i ++ ){
			model.addElement(new WItemLabel("APPLE"));
			model.addElement(new WItemLabel("GOOG"));
		}

		this.labels = new JList(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.labels);
		scrollPane.setPreferredSize(new Dimension(120, 700));
		this.add(scrollPane);

		//this.setBackground(new Color(00));

		this.setPreferredSize(new Dimension(140, 600));
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
