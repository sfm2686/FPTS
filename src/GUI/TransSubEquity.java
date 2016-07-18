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
	 * @param mainFrame
	 * @param user
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
	 * Add the label
	 */
	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Subtract Shares from an Equity"));
		return panel;
	}

	/**
	 * Actual components of panel
	 */
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
	 * Buttons of the panel
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
	 * Any action listener assignments
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
	
	@Override
	public String toString(){
		return("Subtract Equity Shares");
	}

}
