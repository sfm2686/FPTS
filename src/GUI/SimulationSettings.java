/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Finance.Portfolio;
import Finance.User;
import Simulation.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimulationSettings extends MainPanel {
	
	/*
	 * Bear - Negative
	 * Bull - Positive
	 * No-growth - 0
	 */
	private final String[] types = {"Bear", "Bull", "No-growth"};
	private final String[] intervals = {"Year", "Month", "Day"};
	private JComboBox<String> type = new JComboBox<>(this.types);
	private JComboBox<String> timeInterval = new JComboBox<>(this.intervals);
	private JComboBox<String> ports = null;
	private JButton simulate;
	private double value = -1;
	private JSpinner stepS, gRateS;
	
	/**
	 * Create the panel.
	 */
	public SimulationSettings(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.CENTER);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	public SimulationSettings(MainFrame mainFrame, double value, User user) {
		super(mainFrame, user);
		this.value = value;
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.CENTER);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Simulation Setup"));
		return panel;
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel(new GridBagLayout());

		
		SpinnerModel t;
		
		Dimension spinD = new Dimension(100, 20);

		
		t = new SpinnerNumberModel(2, 1, 100, 1);
		this.stepS = new JSpinner(t);
		this.stepS.setPreferredSize(spinD);
		
		t = new SpinnerNumberModel(0, 0, 100, 1);
		this.gRateS = new JSpinner(t);
		this.gRateS.setPreferredSize(spinD);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		if ( this.value == -1 ){
			
			JLabel port  = new JLabel("Portfolio ");
			String[] portsA = new String[getUser().getPorts().size()];
			for (int p = 0; p < getUser().getPorts().size(); p ++)
				portsA[p] = getUser().getPorts().get(p).getName() + ", Value: " 
				+ getUser().getPorts().get(p).getPortfolioValue();
			this.ports = new JComboBox<>(portsA);
			
			panel.add(port, c);
			c.gridx ++;
			panel.add(ports, c);
			c.gridx = 0;
			c.gridy ++;
		}
		else {
			panel.add(new JLabel("Starting value: " + this.value), c);
			c.gridy ++;
		}
		
		panel.add(new JLabel("Simulation type "), c);
		c.gridx ++;
		panel.add(this.type, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Time-interval "), c);
		c.gridx ++;
		panel.add(this.timeInterval, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Steps "), c);
		c.gridx ++;
		panel.add(this.stepS, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Growth-rate "), c);
		c.gridx ++;
		panel.add(this.gRateS, c);
		
		return panel;
	}
	
	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.simulate = new JButton("Simulate");
		panel.add(this.simulate);
		
		return panel;
	}
	
	protected void assign(){
		
		this.simulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double initValue, growthRate;
				int timeSteps;
				String timeInt, simType;
				if ( value == -1 ){
					initValue = getUser().getPorts().get(ports.getSelectedIndex()).getPortfolioValue();
				}
				else {
					initValue = value;
				}
				growthRate = (Integer) gRateS.getValue();
				timeSteps = (Integer) stepS.getValue();
				timeInt = (String) timeInterval.getSelectedItem();
				simType = (String) type.getSelectedItem();
				
				SimulationContext simCon = new SimulationContext(
						growthRate, initValue, timeSteps, timeInt, simType);
				simCon.simulate();
				SimulationResults sim = new SimulationResults(getFrame(), simCon, getUser());
				transition(sim);
			}
		});
	}
	
	@Override
	public String toString(){
		return "Simulation";
	}
}
