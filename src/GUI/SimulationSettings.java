package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Finance.User;
import Simulation.*;

/**
 * This panel is where the user is able to specify the settings of
 * the simulation they wish to perform on a selected portfolio.
 * The settings the user is able to specify are:
 * 	1. portoflio
 * 	2. simulation type
 * 	3. growth rate
 * 	4. time interval
 * 	5. number of steps
 * All of the input is auto-validated by the GUI swing components.
 * The user is unable to input invalid values here.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class SimulationSettings extends MainPanel {
	
	/*
	 * Bear - Negative
	 * Bull - Positive
	 * No-growth - 0
	 */
	
	/****** Class Attributes ******/
	private final String[] types = {"Bear", "Bull", "No-growth"};
	private final String[] intervals = {"Year", "Month", "Day"};
	private JComboBox<String> type = new JComboBox<>(this.types);
	private JComboBox<String> timeInterval = new JComboBox<>(this.intervals);
	private JComboBox<String> ports = null;
	private JButton simulate;
	private double value = -1;
	private JSpinner stepS, gRateS;
	
	/****** Class Methods ******/

	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public SimulationSettings(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.CENTER);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	/**
	 * This is a second constructor for this class.
	 * The only difference between this constructor and the first one is
	 * that this one takes an additional pram, the value. If the value was
	 * passed into the class it will not display a drop-down menu for the user
	 * to a select a portfolio, if this constructor was called then the new simulation
	 * will be bassed of an old one.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 * @pram value: the value to start the new simulation off.
	 */
	public SimulationSettings(MainFrame mainFrame, double value, User user) {
		super(mainFrame, user);
		this.value = value;
		
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
		panel.add(new JLabel("Simulation Setup"));
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@SuppressWarnings("static-access")
	@Override
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
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.simulate = new JButton("Simulate");
		panel.add(this.simulate);
		
		return panel;
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
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
	
	/**
	 * The toString for this class.
	 * 
	 * @return: A string representation of this class.
	 */
	@Override
	public String toString(){
		return "Simulation";
	}
}
