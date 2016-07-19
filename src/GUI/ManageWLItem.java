package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import WatchList.*;
import WatchList.WatchListItem.State;
import Finance.User;

/**
 * This class represents the views and the controls of managing a watch list
 * item for the user to configure. The options that the user has are:
 * current watch list item, edit current watch list item, and reset state 
 * of the current watch list item back to normal.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class ManageWLItem extends MainPanel {

	/****** Class Attributes ******/
	private WatchListItem item;
	private JButton remove, resetState, edit;
	private JTextField highB, lowB;
	
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
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

	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(this.InfoPanel(), BorderLayout.NORTH);
		panel.add(this.editPanel(), BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * This is a private helper method for the middle() method.
	 * This method does the edit part of the manage watch list item panel.
	 * @return JPanel: A JPanel containing all of the components needed for 
	 * 				   editing a watch list item.
	 */
	@SuppressWarnings("static-access")
	private JPanel editPanel(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
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
	
	/**
	 * This is a private helper method for the middle() method.
	 * This method does the information part of the manage watch list item panel.
	 * @return JPanel: A JPanel containing all of the components needed for 
	 * 				   displaying a watch list item.
	 */
	@SuppressWarnings("static-access")
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
			s += d.toString();
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

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
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
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
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
				else
					item.clearHighBound();
				if ( l != null )
					item.setLowBound(l);
				else
					item.clearLowBound();
				
				transition(new AcctOverview(getUser()));
			}
		});
		
		this.remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w = "Are you sure you want to remove the watch list item:" +
						item.getEq().getName() + " ?";
				if (JOptionPane.showConfirmDialog(null, w, "Save", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					getUser().getWatchList().removeWatchListItem(item);
					transition(new AcctOverview(getUser()));
				}
				else
					return ;
			}
		});
		
		this.resetState.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				item.setState(State.Normal);
				transition(new AcctOverview(getUser()));
			}
		});
	}
}
