/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Transaction.*;

import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransRemove extends MainPanel {

	private Portfolio workingPort;
	private String[] CashAcctsList;
	private String[] equitiesList;
	private JCheckBox[] checkBoxesCash, checkBoxesEq;
	private JButton remove;

	
	/**
	 * Create the panel.
	 */
	public TransRemove(MainFrame mainFrame, User user, Portfolio port) {
		super(mainFrame, user);
		this.workingPort = port;
		
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
		
		int size = this.CashAcctsList.length;
		this.checkBoxesCash = new JCheckBox[size];

		//Filling cash accounts first
		for ( int i = 0; i < size; i ++ )
			this.checkBoxesCash[i] = new JCheckBox(this.CashAcctsList[i]);
		
		size = this.equitiesList.length;
		this.checkBoxesEq = new JCheckBox[size];
		//Filling equities
		for ( int i = 0; i < size; i ++ )
			this.checkBoxesEq[i] = new JCheckBox(this.equitiesList[i]);
		
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
		int size = workingPort.getCashAccounts().size();
		this.CashAcctsList = new String[size];

		for ( int c = 0; c < size; c ++ )
			this.CashAcctsList[c] = workingPort.getCashAccounts().get(c).getName();
			
		size = workingPort.getEquities().size();
		this.equitiesList = new String[size];
		
		for ( int e = 0; e < size; e ++ )
			this.equitiesList[e] = workingPort.getEquities().get(e).getName();
			
	}
	
	//Did not test equity removal yet ..
	protected void assign(){
		this.remove.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				Client client = new Client(getUser());
				
				for ( int c = 0; c < checkBoxesCash.length; c ++ )
					if ( checkBoxesCash[c].isSelected() )
						client.removeCash(workingPort, checkBoxesCash[c].getLabel());
				
				for ( int a = 0; a < checkBoxesEq.length; a ++ )
					if ( checkBoxesEq[a].isSelected() )
						client.removeEquity(workingPort, checkBoxesEq[a].getLabel());
				
				transition(new AcctOverview(getUser()));

			}
		});
	}
	
	@Override
	public String toString(){
		return "Remove Cash Account";
	}
}
