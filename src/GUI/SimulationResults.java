/**
 * 
 */
package GUI;

import java.awt.*;
import javax.swing.*;

import Simulation.SimulationContext;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimulationResults extends JFrame {

	private JButton next;
	private SimulationContext sim;
	
	/**
	 * Create the frame.
	 */
	public SimulationResults(SimulationContext sim) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.sim = sim;
		
	}

}
