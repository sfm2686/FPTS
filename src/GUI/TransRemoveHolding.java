package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Transaction.*;

import Finance.*;

/**
 * This panel is responsible for removing a holding from the user owned
 * collections. The panel displays all of the holdings that are owned by
 * the current logged in user as check-boxes and the user is able to check
 * the holdings they wish to remove.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class TransRemoveHolding extends MainPanel {

	/****** Class Attributes ******/
	private ArrayList<JCheckBox> checkBoxesCash, checkBoxesEq;
	private JButton remove;
	private HashMap<String, Object[]> cashMap, eqMap;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransRemoveHolding(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.checkBoxesCash = new ArrayList<>();
		this.checkBoxesEq = new ArrayList<>();
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
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Remove a Holding from a Portfolio"));
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
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;

		//Filling cash accounts first
		for ( String key : this.cashMap.keySet())
			this.checkBoxesCash.add(new JCheckBox(key));
	
		//Filling equities
		for ( String key : this.eqMap.keySet())
			this.checkBoxesEq.add(new JCheckBox(key));
		
		for ( JCheckBox j : this.checkBoxesCash ){
			panel.add(j, c);
			c.gridy ++;
		}
		
		for ( JCheckBox j : this.checkBoxesEq ){
			panel.add(j, c);
			c.gridy ++;
		}
		
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
		
		this.remove = new JButton("Remove");
		panel.add(this.remove);
		
		return panel;
	}
	
	/**
	 * This is a private helper method that fills and sortsthe HashMap variable.
	 */
	private void fill(){
		String label;
		for(Portfolio port : this.getUser().getPorts()){
			for(CashAcct acct : port.getCashAccounts()){
				label = port.getName() + ": " + acct.getName();
				this.cashMap.put(label, new Object[]{port, acct});
			}
			for(Equity eq : port.getEquities()){
				label = port.getName() + ": " + eq.getName();
				this.eqMap.put(label, new Object[]{port, eq});
			}
		}
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	protected void assign(){
		this.remove.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				Client client = new Client(getUser());
				Portfolio port;
				CashAcct cash;
				Equity eq;
				for ( JCheckBox box : checkBoxesCash) {
					if ( box.isSelected() ){
						port = (Portfolio)(cashMap.get(box.getLabel())[0]);
						cash = (CashAcct)(cashMap.get(box.getLabel())[1]);
						client.removeCash(port, cash.getName());
					}	
				}
				for ( JCheckBox box : checkBoxesEq) {
					if ( box.isSelected() ){
						port = (Portfolio)(cashMap.get(box.getLabel())[0]);
						eq = (Equity)(cashMap.get(box.getLabel())[1]);
						client.removeCash(port, eq.getName());
					}	
				}
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
		return "Remove Holding";
	}
}
