package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Finance.Equity;
import Finance.Index;
import Finance.Stock;
import Finance.User;
import Market.Market;
import WatchList.SetStateVisitor;
import WatchList.WatchListItem;

/**
 * This page is responsible for adding a new watch list item to the
 * list of watch list items that the user can view and manage.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class AddWLItem extends MainPanel {

	/****** Class Attributes ******/
	private JTextField highB, lowB;
	private JButton addButton;
	private JComboBox<String> equities;
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
	public AddWLItem(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.map = new HashMap<>();
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.WEST);
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
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Add a new watch list item"));
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
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		this.highB = new JTextField();
		this.highB.setPreferredSize(TEXTD);
		this.lowB  = new JTextField();
		this.lowB.setPreferredSize(TEXTD);
		
		this.fill();
		
		panel.add(new JLabel("Equity "), c);
		c.gridx ++;
		panel.add(this.equities, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Enter a high bound "), c);
		c.gridx ++;
		panel.add(this.highB, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Enter a low bound "), c);
		c.gridx ++;
		panel.add(this.lowB, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Note: Bounds can be left empty."), c);
		c.gridy ++;
		
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
		this.addButton = new JButton("Add");
		panel.add(this.addButton);
		return panel;
	}

	/**
	 * This is a private helper method that fills and sorts the HashMap variable.
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	private void fill(){
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
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double h = null;
				Double l = null;
				String w;
				try{
					if(!highB.getText().equals(""))
						h = Double.parseDouble(highB.getText());
					if(!lowB.getText().equals(""))
						l = Double.parseDouble(lowB.getText());
				}
				catch(Exception exc){
					w = "You must enter a numeric value.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally{ 
					
					if(h != null && l != null){
						if(h <= l){
							w = "The high bound must be greater than the lower bound.";
							JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					
					Equity equity = null;
					String selected = map.get((String)equities.getSelectedItem());
					if(Market.getMarketInstance().isIndex(selected)){
						if(selected.equalsIgnoreCase("DOW")){
							equity = Market.getMarketInstance().getDow(0);
						}
						else{
							equity = new Index(0, selected);
						}
					}
					else if(Market.getMarketInstance().isStock(selected))
						equity = new Stock(0, selected);

		
					WatchListItem item = new WatchListItem(equity);
					if(h != null)
						item.setHighBound(h);
					if(l != null)
						item.setLowBound(l);
					Market.getMarketInstance().addUpdateEquity(equity);
					SetStateVisitor v =  new SetStateVisitor();
					item.accept(v);
					getUser().getWatchList().addWatchListItem(item);
				}
				

				transition(new AcctOverview(getUser()));
			}
		});
	}

}
