/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	private JButton simulate;
	private User user;
	
	private JSpinner timeS, stepS, gRateS, typeS;
	
	/**
	 * Create the panel.
	 */
	public SimulationSettings(User u) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		this.user = u;
	
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
		t = new SpinnerListModel(this.intervals);
		this.timeS = new JSpinner(t);
		this.timeS.setPreferredSize(spinD);
		
		t = new SpinnerNumberModel(2, 0, 100, 1);
		this.stepS = new JSpinner(t);
		this.stepS.setPreferredSize(spinD);
		
		t = new SpinnerNumberModel(0, 0, 100, 1);
		this.gRateS = new JSpinner(t);
		this.gRateS.setPreferredSize(spinD);
		
		t = new SpinnerListModel(this.types);
		this.typeS = new JSpinner(t);
		this.typeS.setPreferredSize(spinD);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(type, c);
		c.gridx ++;
		panel.add(this.typeS, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(time, c);
		c.gridx ++;
		panel.add(this.timeS, c);
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
				//public SimulationContext(double growthRate, double value, int timeSteps, String interval, String type) {
				String w = "SIMULATION RESULTS SHOULD POP UP INSTEAD OF DIS";
				JOptionPane.showMessageDialog(new JFrame(), w, "Empty Field/s",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}
}
