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
 * This class represents the panel responsible for the display of
 * simulation results. This panel only has one button at a time.
 * The "Next" button is displayed when the results are done.
 * Once the results are done, meaning the number of steps is met for that
 * specific simulation, the "Next" button gets replaced with "Simulate Again"
 * button in order to give the user the option to simulate once again off the 
 * results of the original simulation.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class SimulationResults extends MainPanel {

	/****** Class Attributes ******/
	private JButton next, simulateAgain;
	private SimulationContext sim;
	@SuppressWarnings("rawtypes")
	private DefaultListModel model;
	@SuppressWarnings("rawtypes")
	private JList results;
	private int count = 1;
	private JScrollPane sPane;
	private double lastValue;
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public SimulationResults(MainFrame mainFrame, SimulationContext sim, User user) {
		super(mainFrame, user);
		this.sim = sim;
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.CENTER);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Simulation Results"));
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected JPanel middle(){
		JPanel panel = new JPanel();
		//panel.setLayout(new BorderLayout());
		
		this.model = new DefaultListModel();
		model.addElement(sim.getInterval() + count +
				": " + sim.getNextResult());
		
		this.results = new JList(model);
		sPane = new JScrollPane();
		sPane.setViewportView(this.results);
		sPane.setPreferredSize(new Dimension(650, 650));
		panel.add(sPane, BorderLayout.CENTER);
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
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
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign(){
		this.next.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
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
	
	/**
	 * The toString for this class.
	 * 
	 * @return: A string representation of this class.
	 */
	@Override
	public String toString(){
		return "Simulation Results";
	}

}
