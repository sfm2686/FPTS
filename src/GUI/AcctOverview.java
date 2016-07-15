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
public class AcctOverview extends JPanel {

	/**
	 * Create the panel.
	 */
	public AcctOverview() {
		this.add(new JLabel("ACCOUNT OVERVIEW"));
		this.setSize(new Dimension(500, 700));
	}

}
