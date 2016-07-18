/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import Finance.*;
import Transaction.Client;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransCreateCash extends MainPanel {

	/****** Class Attributes ******/

	private JTextField name, initialAmount;
	private JComboBox<String> ports;
	private JButton create;
	
	
	/****** Class Methods ******/
	
	/**
	 * Create the panel.
	 */
	public TransCreateCash(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}


	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Create Cash Account in a Selected Portfolio"));
		return panel;
	}

	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.EAST;
		c.gridx = 0;
		c.gridy = 0;
		

		this.name = new JTextField();
		this.name.setPreferredSize(TEXTD);
		this.initialAmount = new JTextField();
		this.initialAmount.setPreferredSize(TEXTD);
		this.ports = getPortDropdown();
		
		panel.add(new JLabel("Destination Portfolio "), c);
		c.gridx ++;
		panel.add(this.ports, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Name "), c);
		c.gridx ++;
		panel.add(this.name, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Amount "), c);
		c.gridx ++;
		panel.add(this.initialAmount, c);
		c.gridx = 0;
		c.gridy ++;
		
		return panel;
	}

	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.create = new JButton("Create");
		panel.add(this.create);
		return panel;
	}


	@Override
	protected void assign() {
		this.create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w;
				Double d = null;
				Portfolio port = getUser().getPorts().get(ports.getSelectedIndex());
				try{
					d = Double.parseDouble(initialAmount.getText());
				}
				catch(Exception exc){
					w = "You must enter a numeric value.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				finally{
					if(d != null){
						if(d <= 0){
							w = "You must enter a positive value.";
							JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					for ( CashAcct cash : port.getCashAccounts() )
						if ( name.getText().equalsIgnoreCase(cash.getName()) ){
							w = "A cash account with the same name is already owned.";
							JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
							return;
						}
				}
				
				Client client = new Client(getUser());
				client.createCash(port, name.getText(), d);
				transition(new AcctOverview(getUser()));
			}
		});
		
	}
	
	@Override
	public String toString(){
		return "Create Cash Account";
	}

}
