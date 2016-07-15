/**
 * 
 */
package GUI;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class LogView extends JPanel {

	/**
	 * Create the panel.
	 */
	public LogView() {
		this.add(new JLabel("VIEW LOG"));
		this.setSize(new Dimension(500, 700));
	}

}
