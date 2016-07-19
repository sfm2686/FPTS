package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import WatchList.*;
import Finance.Equity;
import Finance.Index;
import Finance.Stock;
import Finance.User;
import Market.Market;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class ManageWLItem extends MainPanel {

	private WatchListItem item;
	private JButton remove, resetState, edit;
	private JTextField highB, lowB;
	
	
	/**
	 * Create the panel.
	 */
	public ManageWLItem(MainFrame mainFrame, User user, WatchListItem item) {
		super(mainFrame, user);
		this.item = item;
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.CENTER);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Manage a WatchList Item"));
		return panel;
	}

	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(this.InfoPanel(), BorderLayout.NORTH);
		panel.add(this.editPanel(), BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel editPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		this.highB = new JTextField();
		this.highB.setPreferredSize(TEXTD);
		this.lowB = new JTextField();
		this.lowB.setPreferredSize(TEXTD);
		
		panel.add(new JLabel("Set High Trigger Price "), c);
		c.gridx ++;
		panel.add(this.highB, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Set Low Trigger Price "), c);
		c.gridx ++;
		panel.add(this.lowB, c);
		c.gridx = 0;
		c.gridy ++;
		
		return panel;
	}
	
	private JPanel InfoPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(new JLabel("Name: " + 
		this.item.getEq().getName()), c);
		c.gridy ++;
		
		panel.add(new JLabel("Current Price: " + 
		this.item.getEq().getPrice()), c);
		c.gridy ++;
		
		Double d;
		String s = "High Trigger: ";
		
		try {
			d = new Double(item.getHighBound());
			s += d;
		}
		catch(NullPointerException e) {
			s += "Not assigned";
		}
		finally {
			panel.add(new JLabel(s), c);
			c.gridy ++;
		}
		
		s = "Low Trigger: ";
		try {
			d = new Double(item.getLowBound());
			s += d;
		}
		catch(NullPointerException e) {
			s += "Not assigned";
		}
		finally {
			panel.add(new JLabel(s), c);
			c.gridy ++;
		}
		
		panel.add(new JLabel("Current State: " + 
		this.item.getState()), c);
		c.gridy ++;
		
		return panel;		
	}

	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.remove = new JButton("Remove");
		this.edit = new JButton("Edit");
		this.resetState = new JButton("Reset State");
		
		panel.add(this.edit);
		panel.add(this.remove);
		panel.add(this.resetState);
		return panel;
	}
	
	@Override
	protected void assign() {
		this.edit.addActionListener(new ActionListener() {
			
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
				}
				if ( h != null )
					item.setHighBound(h);
				if ( l != null )
					item.setLowBound(l);
				transition(new AcctOverview(getUser()));
			}
		});
	}
}
