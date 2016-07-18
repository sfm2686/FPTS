/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Finance.User;
import Simulation.SimulationContext;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimulationResults extends MainPanel {

	private JButton next, simulateAgain;
	private SimulationContext sim;
	private DefaultListModel model;
	private JList results;
	private int count = 1;
	private JScrollPane sPane;
	private double lastValue;
	
	/**
	 * Create the frame.
	 */
	public SimulationResults(MainFrame mainFrame, SimulationContext sim, User user) {
		super(mainFrame, user);
		this.sim = sim;
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.WEST);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Simulation Results"));
		return panel;
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		this.model = new DefaultListModel();
		model.addElement(sim.getInterval() + count +
				": " + sim.getNextResult());
		
		this.results = new JList(model);
		sPane = new JScrollPane();
		sPane.setViewportView(results);
		sPane.setPreferredSize(new Dimension(500, 650));
		panel.add(sPane, BorderLayout.WEST);
		return panel;
	}
	
	protected JPanel bottom(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		this.next = new JButton("Next");
		this.simulateAgain = new JButton("Simulate Again");
		this.simulateAgain.setVisible(false);
		
		panel.add(this.next);
		panel.add(this.simulateAgain);
		
		return panel;
	}
	
	protected void assign(){
		this.next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double temp = sim.getNextResult();
				model.addElement(sim.getInterval() + ++count +
						": " + temp);
				
				lastValue = temp;
				
				results.setModel(model);
				sPane.setViewportView(results);
				if ( sim.howManyLeft() == 0 ){
					next.setVisible(false);
					simulateAgain.setVisible(true);
				}
			}
		});
		
		this.simulateAgain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				transition(new SimulationSettings(getFrame(), lastValue, getUser()));
			}
		});
	}
	
	@Override
	public String toString(){
		return "Simulation Results";
	}

}
