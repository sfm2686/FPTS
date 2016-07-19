package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Finance.*;
import Transaction.Client;

/**
 * This panel enables the user to sell an equity that they own.
 * The user is able to select how many shares of the selected equity
 * they would like to sell.
 * The panel validates all of the user input before completing the transaction.
 * The cash that results from selling equity shares in this panel goes to an owned
 * cash account by the current user.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class TransSellEquity extends MainPanel {

	/****** Class Attributes ******/
	private HashMap<String, Object[]> cashMap;
	private HashMap<String, Object[]> eqMap;
	@SuppressWarnings("rawtypes")
	private JComboBox equities, cashAccts;
	private JSpinner shareS;
	private JButton sell;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransSellEquity(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.cashMap = new HashMap<>();
		this.eqMap = new HashMap<>();
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
		panel.add(new JLabel("Sell Equity Shares and add the Funds to a Cash Account"));
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
		// Initial panel setup
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		// Add the equity selection
		panel.add(new JLabel("Equity "), c);
		c.gridx++;
		int i = 0;
		String[] equities = new String[this.eqMap.keySet().size()];
		for (String key : this.eqMap.keySet())
			equities[i++] = key;
		this.equities = new JComboBox(equities);
		panel.add(this.equities, c);
		c.gridx = 0;
		c.gridy++;
		
		// Add the cash account selection
		panel.add(new JLabel("Destination Cash Account "), c);
		c.gridx++;
		i = 0;
		String[] cashAccts = new String[this.cashMap.keySet().size()];
		for (String key : this.cashMap.keySet())
			cashAccts[i++] = key;
		this.cashAccts = new JComboBox(cashAccts);
		panel.add(this.cashAccts, c);
		c.gridx = 0;
		c.gridy++;
		
		// Add the number of shares selection
		Dimension spinD = new Dimension(150, 20);
		SpinnerModel t = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		this.shareS = new JSpinner(t);
		this.shareS.setPreferredSize(spinD);
		panel.add(new JLabel("Number of Shares "), c);
		c.gridx++;
		panel.add(this.shareS, c);
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
		this.sell = new JButton("Sell Equity Shares");
		panel.add(this.sell);
		return (panel);
	}

	/**
	 * This is a private helper method that fills and sortsthe HashMap variable.
	 */
	private void fill(){
		String str;

		for (Portfolio port : getUser().getPorts()){
			for (CashAcct cash : port.getCashAccounts()){
				str = port.getName() + ": " + cash.getName();
				this.cashMap.put(str, new Object[]{port, cash});
			}
			for (Equity eq : port.getEquities()){
				str = port.getName() + ": " + eq.getName() + ", Shares Owned: " + eq.getNumShares() + ", Sale Price: " + eq.getPrice();
				this.eqMap.put(str, new Object[]{port, eq});
			}
		}
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.sell.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Equity equity = (Equity)eqMap.get(equities.getSelectedItem())[1];
				CashAcct cashAcct = (CashAcct)(cashMap.get((String)cashAccts.getSelectedItem())[1]);
				Portfolio dPort = (Portfolio)cashMap.get((String)cashAccts.getSelectedItem())[0];
				Portfolio sPort = (Portfolio)eqMap.get((String)equities.getSelectedItem())[0];
				int shares = (int)shareS.getValue();

				String w;
				if (equity.getNumShares() < shares){
					w = "Insufficient shares for the given equity.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				Client client = new Client(getUser());
				client.sellShares(dPort, sPort, cashAcct.getName(), equity.getName(), shares);
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
		return "Sell Equity";
	}

}
