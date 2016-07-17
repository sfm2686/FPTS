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
public class SimulationResults extends JPanel {

	private JButton next, simulateAgain;
	private SimulationContext sim;
	private DefaultListModel model;
	private JList results;
	private int count = 1;
	private JScrollPane sPane;
	private User user;
	private double lastValue;
	private MainFrame mainFrame;
	
	/**
	 * Create the frame.
	 */
	public SimulationResults(MainFrame mainFrame, SimulationContext sim, User user) {
		this.setSize(new Dimension(500, 700));
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.sim = sim;
		this.user = user;
		this.mainFrame = mainFrame;
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Simulation Results"));
		return panel;
	}
	
	private JPanel middle(){
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
	
	private JPanel bottom(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		this.next = new JButton("Next");
		this.simulateAgain = new JButton("Simulate Again");
		this.simulateAgain.setVisible(false);
		
		panel.add(this.next);
		panel.add(this.simulateAgain);
		
		return panel;
	}
	
	private void assign(){
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
				mainFrame.refresh(new SimulationSettings(mainFrame, lastValue, user));
			}
		});
	}

}
