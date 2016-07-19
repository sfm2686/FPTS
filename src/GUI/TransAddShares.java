package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Market.Market;
import Transaction.Client;

import javax.swing.*;

import Finance.*;

/**
 * This panel represents the views and controls of the add shares
 * to an equity transaction type. The panel validates the input of the
 * user, once all of the user input is valid, the panel creates a client
 * from the transaction package in order to complete the transaction.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class TransAddShares extends MainPanel {

	/****** Class Attributes ******/
	private JButton add;
	private JComboBox<String> ports, equities;
	private JSpinner shareS, dateS;
	private HashMap<String, String> map;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransAddShares(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		map = new HashMap<String, String>();
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Add a New Equity to a Portfolio"));
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		SpinnerModel t;
		Dimension spinD = new Dimension(150, 20);
		// For getting the number of shares
		t = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		this.shareS = new JSpinner(t);
		this.shareS.setPreferredSize(spinD);
		// For getting the acquisition date
		Date today = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
	    this.dateS = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		this.dateS.setPreferredSize(spinD);

		
	    // Add the portfolio selection
		this.ports = super.getPortDropdown();
		this.ports.setPreferredSize(spinD);
	    panel.add(new JLabel("Portfolio "), c);
	    c.gridx++;
	    panel.add(this.ports, c);
	    c.gridx = 0;
	    c.gridy++;

	    // Add the equity selection
	    panel.add(new JLabel("Equity "), c);
	    c.gridx++;
	    
	    ArrayList<String> info = new ArrayList<String>();
	    
	    String str;
	    for(String key : Market.getMarketInstance().getStocks().keySet()){
	    	str = key + ": " + Market.getMarketInstance().getStocks().get(key).get(0) + ", Price: " + Market.getMarketInstance().getStocks().get(key).get(1);
	    	info.add(str);
	    	this.map.put(str, key);
	    }
	    
	    Collections.sort(info);
	    
	    this.equities = new JComboBox(info.toArray());	    
	    info = new ArrayList<String>();
;	    for(String key : Market.getMarketInstance().getIndices().keySet()){
	    	str = key + ", Price: " + Market.getMarketInstance().getIndexPrice(key);
	    	info.add(str);
	    	this.map.put(str, key);
	    }
		Collections.sort(info);
		for(String st : info){
			this.equities.addItem(st);
		}
		
	    panel.add(equities, c);
	    c.gridx = 0;
	    c.gridy++;
	    
	    // Add the shares input
	    panel.add(new JLabel("Number of Shares "), c);
	    c.gridx++;
	    panel.add(this.shareS, c);
	    c.gridx = 0;
	    c.gridy++;
	    
	    // Add the date input
	    panel.add(new JLabel("Date Acquired"), c);
	    c.gridx++;
	    panel.add(this.dateS, c);
	    c.gridx = 0;
	    c.gridy++;
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.add = new JButton("Create Equity");
		panel.add(this.add);
		return (panel);
	}

	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client c = new Client(getUser());
				
				Portfolio port = getUser().getPorts().get(ports.getSelectedIndex());
				String eq = map.get(equities.getSelectedItem());
				int shares = (int)shareS.getValue();
				Date date = (Date)dateS.getValue();
				
				c.addShares(port, eq, shares, date);
				transition(new AcctOverview(getUser()));
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
		return("Add Equity Shares");
	}

}
