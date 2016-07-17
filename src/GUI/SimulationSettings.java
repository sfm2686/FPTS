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
public class SimulationSettings extends JPanel {
	
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
	private User user;
	private double value = -1;
	private MainFrame mainFrame;
	private JSpinner stepS, gRateS;
	
	/**
	 * Create the panel.
	 */
	public SimulationSettings(MainFrame mainFrame, User u) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		this.user = u;
		this.mainFrame = mainFrame;
	
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	public SimulationSettings(MainFrame mainFrame, double value, User u) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		this.value = value;
		this.user = u;
		this.mainFrame = mainFrame;
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Simulation Setup"));
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel(new GridBagLayout());
		
		JLabel time  = new JLabel("Time-interval ");
		JLabel steps = new JLabel("Steps ");
		JLabel gRate = new JLabel("Growth-rate ");
		JLabel type  = new JLabel("Simulation type ");
		
		SpinnerModel t;
		
		Dimension spinD = new Dimension(100, 20);

		
		t = new SpinnerNumberModel(2, 0, 100, 1);
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
			String[] portsA = new String[this.user.getPorts().size()];
			for (int p = 0; p < this.user.getPorts().size(); p ++)
				portsA[p] = this.user.getPorts().get(p).getName() + ", Value: " 
				+ this.user.getPorts().get(p).getPortfolioValue();
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
		
		panel.add(type, c);
		c.gridx ++;
		panel.add(this.type, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(time, c);
		c.gridx ++;
		panel.add(this.timeInterval, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(steps, c);
		c.gridx ++;
		panel.add(this.stepS, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(gRate, c);
		c.gridx ++;
		panel.add(this.gRateS, c);
		
		return panel;
	}
	
	private JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.simulate = new JButton("Simulate");
		panel.add(this.simulate);
		
		return panel;
	}
	
	private void assign(){
		this.simulate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				String w = "SIMULATION RESULTS SHOULD POP UP INSTEAD OF DIS";
//				JOptionPane.showMessageDialog(new JFrame(), w, "Empty Field/s",
//						JOptionPane.ERROR_MESSAGE);
				
				double initValue, growthRate;
				int timeSteps;
				String timeInt, simType;
				if ( value == -1 ){
					initValue = user.getPorts().get(ports.getSelectedIndex()).getPortfolioValue();
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
				SimulationResults sim = new SimulationResults(mainFrame, simCon, user);
				SimulationSettings.this.mainFrame.refresh(sim);
			}
		});
	}
	
	@Override
	public String toString(){
		return "Simulation Settings";
	}
}
