package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Finance.*;

/**
 * 
 */

/**
 * This panel is the menu display of all transaction types that
 * the user is able to select from.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class TransactionMenu extends MainPanel {
	
	/****** Class Attributes ******/
	private final MainPanel[] tranTypes = { new TransSubEquity(getFrame(), getUser()),
											new TransDepositCash(getFrame(), getUser()),
											new TransSellEquity(getFrame(), getUser()),
											new TransWithdraw(getFrame(), getUser()),
											new TransBuyEquity(getFrame(), getUser()),
											new TransCreateCash(getFrame(), getUser()),
											new TransCashTransfer(getFrame(), getUser()), 
											new TransAddShares(getFrame(), getUser()), 
											new TransRemoveHolding(getFrame(), getUser())
											};

	private JButton next;
	private JComboBox<MainPanel> trans;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransactionMenu(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
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
		panel.add(new JLabel("Transaction Menu"));
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

		panel.add(new JLabel("Transaction "), c);
		c.gridx ++;
		this.trans = new JComboBox<>(tranTypes);
		panel.add(trans, c);
		c.gridx = 0;
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
	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.next = new JButton("Next");
		panel.add(this.next);
		
		return panel;
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign(){
		this.next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				transition((MainPanel) trans.getSelectedItem()); 
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
		return "Transaction Menu";
	}
}
