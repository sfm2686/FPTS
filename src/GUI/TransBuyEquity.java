package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Finance.*;
import Market.Market;
import Transaction.Client;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransBuyEquity extends MainPanel {

	/****** Class Attributes ******/
	private HashMap<String, Object[]> cashMap;
	private HashMap<String, String> eqMap;
	private JComboBox destPort, equities, cashAccts;
	private JSpinner shareS, dateS;
	private JButton buy;
	
	/****** Class Methods ******/
	
	/**
	 * @param mainFrame
	 * @param user
	 */
	public TransBuyEquity(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.cashMap = new HashMap<>();
		this.eqMap = new HashMap<>();
		this.fill();
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	/* (non-Javadoc)
	 * @see GUI.MainPanel#top()
	 */
	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Purchase Equity Shares with Funds from a Cash Account"));
		return panel;
	}

	/* (non-Javadoc)
	 * @see GUI.MainPanel#middle()
	 */
	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		
		// Initialize the portfolio dropdown menu
		this.destPort = super.getPortDropdown();
	
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
		
		// Create the alphabetical listing of equities
	    ArrayList<String> info = new ArrayList<String>();
	    String str;
	    for(String key : Market.getMarketInstance().getStocks().keySet()){
	    	str = key + ": " + Market.getMarketInstance().getStocks().get(key).get(0) + ", Price: " + Market.getMarketInstance().getStocks().get(key).get(1);
	    	info.add(str);
	    	this.eqMap.put(str, key);
	    }
	    Collections.sort(info);
	    this.equities = new JComboBox(info.toArray());	    
	    info = new ArrayList<String>();
	    for(String key : Market.getMarketInstance().getIndices().keySet()){
	    	str = key + ", Price: " + Market.getMarketInstance().getIndexPrice(key);
	    	info.add(str);
	    	this.eqMap.put(str, key);
	    }
		Collections.sort(info);
		for(String st : info){
			this.equities.addItem(st);
		}
		
		// Initialize the src cash account dropdown
		this.cashAccts = new JComboBox(this.cashMap.keySet().toArray());
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.EAST;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(new JLabel("Equity "), c);
		c.gridx++;
		panel.add(this.equities, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Destination Portfolio "), c);
		c.gridx++;
		panel.add(this.destPort, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Source Cash Account "), c);
		c.gridx++;
		panel.add(this.cashAccts, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Number of Shares "), c);
		c.gridx++;
		panel.add(this.shareS, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Date of Acquisition "), c);
		c.gridx++;
		panel.add(this.dateS, c);
		c.gridx = 0;
		c.gridy++;
		
		return panel;
	}

	/* (non-Javadoc)
	 * @see GUI.MainPanel#bottom()
	 */
	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.buy = new JButton("Buy Equity Shares");
		panel.add(this.buy);
		return (panel);
	}

	private void fill(){
		String str;
		this.cashMap = new HashMap<String, Object[]>();
		for (Portfolio port : getUser().getPorts()){
			for (CashAcct cash : port.getCashAccounts()){
				str = port.getName() + ": " + cash.getName() + ", Balance: " + cash.getBalance();
				this.cashMap.put(str, new Object[]{port, cash});
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see GUI.MainPanel#assign()
	 */
	@Override
	protected void assign() {
		this.buy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Client client = new Client(getUser());
				
				String equity = eqMap.get(equities.getSelectedItem());
				CashAcct cashAcct = (CashAcct)(cashMap.get((String)cashAccts.getSelectedItem())[1]);
				Portfolio dPort = getUser().getPorts().get(destPort.getSelectedIndex());
				Portfolio sPort = (Portfolio)cashMap.get((String)cashAccts.getSelectedItem())[0];
				Date date = (Date)dateS.getValue();
				
				int shares = (int)shareS.getValue();

				String w;
				double price = Market.getMarketInstance().getPrice(equity) * shares;
				if (cashAcct.getBalance() < price){
					w = "Insufficient funds in the source cash account.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				client.buyEquity(dPort, sPort, equity, cashAcct.getName(), shares, date);
				transition(new AcctOverview(getUser()));
			}
		});
		
	}
	
	@Override
	public String toString(){
		return "Buy Equity";
	}

}
