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
public class SimulationSettings extends JPanel {

	/**
	 * Create the panel.
	 */
	public SimulationSettings() {
		this.add(new JLabel("SIMULATION SETTINGS"));
		this.setSize(new Dimension(500, 700));
	}

}
