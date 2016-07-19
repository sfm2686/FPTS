package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Transaction.Client;

import javax.swing.*;

import Finance.*;

/**
 * This panel enables the user to sell an equity that they own.
 * The user is able to select how many shares of the selected equity
 * they would like to sell.
 * The panel validates all of the user input before completing the transaction.
 * The cash that results from selling equity shares in this panel goes to outside
 * FPTS.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class TransSubEquity extends MainPanel {

	/****** Class Attributes ******/
	private JButton sub;
	private JComboBox<String> equities;
	private JSpinner shareS;
	private HashMap<String, Object[]> map;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransSubEquity(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.map = new HashMap<String, Object[]>();
		this.fill();
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
		panel.add(new JLabel("Subtract Shares from an Equity"));
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
		// Initial setup of the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;

		// Add the equity and portfolio selection
	    panel.add(new JLabel("Equity "), c);
	    c.gridx++;
		String[] equities = new String[this.map.size()];
		int i = 0;
		for (String key : this.map.keySet()){
			equities[i++] = key;
		}
		this.equities = new JComboBox(equities);
		panel.add(this.equities, c);
		c.gridx = 0;
	    c.gridy++;
		
		// Add the number of shares selection
	    panel.add(new JLabel("Number of Shares "), c);
	    c.gridx++;
		SpinnerModel t;
		Dimension spinD = new Dimension(150, 20);
		t = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		this.shareS = new JSpinner(t);
		this.shareS.setPreferredSize(spinD);
		panel.add(this.shareS, c);

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
		this.sub = new JButton("Subtract Equity Shares");
		panel.add(this.sub);
		return (panel);
	}
	
	private void fill(){
		for (Portfolio port : getUser().getPorts()){
			for (Equity eq : port.getEquities()){
				String str = port.getName() + ": " + eq.getName() + ", Number of Shares: " + eq.getNumShares();
				this.map.put(str, new Object[]{port, eq});
			}
		}
	}

	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.sub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client client = new Client(getUser());
				
				Portfolio port = (Portfolio)map.get(equities.getSelectedItem())[0];
				Equity eq = (Equity)map.get(equities.getSelectedItem())[1];
				int shares = (int)shareS.getValue();

				if (eq.getNumShares() < shares){
					String w = "Insufficient shares to subtract from equity.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				client.subtractEquity(port, eq.getName(), shares);
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
		return("Subtract Equity Shares");
	}

}
