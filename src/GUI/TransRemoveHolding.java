/**
 * 
 */
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
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class TransRemoveHolding extends MainPanel {

	private ArrayList<JCheckBox> checkBoxesCash, checkBoxesEq;
	private JButton remove;
	private HashMap<String, Object[]> cashMap, eqMap;
	
	/**
	 * Create the panel.
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
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Removal"));
		return panel;
	}
	
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

	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.remove = new JButton("Remove");
		panel.add(this.remove);
		
		return panel;
	}
	
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
	
	//Did not test equity removal yet ..
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
	
	@Override
	public String toString(){
		return "Remove Cash Account";
	}
}
