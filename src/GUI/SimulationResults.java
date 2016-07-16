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
public class SimulationResults extends JFrame {

	private JButton next, done, simulateAgain;
	private SimulationContext sim;
	private DefaultListModel model;
	private JList results;
	private int count = 1;
	private JScrollPane sPane;
	private User user;
	
	/**
	 * Create the frame.
	 */
	public SimulationResults(SimulationContext sim, User user) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600, 600));
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.sim = sim;
		this.user = user;
		
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
		sPane.setPreferredSize(new Dimension(400, 200));
		panel.add(sPane, BorderLayout.WEST);
		return panel;
	}
	
	private JPanel bottom(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		this.next = new JButton("Next");
		this.done = new JButton("Close Window");
		this.simulateAgain = new JButton("Simulate Again");
		this.simulateAgain.setVisible(false);
		
		panel.add(this.next);
		panel.add(this.simulateAgain);
		panel.add(this.done);
		
		return panel;
	}
	
	private void assign(){
		this.next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addElement(sim.getInterval() + ++count +
						": " + sim.getNextResult());
				
				results.setModel(model);
				sPane.setViewportView(results);
				if ( sim.howManyLeft() == 0 ){
					next.setVisible(false);
					simulateAgain.setVisible(true);
				}
			}
		});
		
		this.done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
