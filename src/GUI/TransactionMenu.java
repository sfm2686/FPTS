package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Finance.*;
import Transaction.*;
import sun.net.ftp.FtpClient.TransferType;

/**
 * 
 */

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransactionMenu extends MainPanel {

//	private final String[] tranTypes = {"Create Cash Account", "Add Equity", "Deposit Cash", 
//			"Withdraw Cash", "Remove Cash Account", "Remove Equity", "Transfer between Cash Account",
//			"Subtract Shares", "Buy Equity", "Sell Equity"};
	
	private final MainPanel[] tranTypes = { new TransAddShares(getFrame(), getUser()), new TransRemoveHolding(getFrame(), getUser())};

	private JButton next;
	private JComboBox<String> ports;
	private JComboBox<MainPanel> trans;
	
	/**
	 * Create the panel.
	 */
	public TransactionMenu(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Transaction Menu"));
		return panel;
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.EAST;
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

	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.next = new JButton("Next");
		panel.add(this.next);
		
		return panel;
	}
	
	protected void assign(){
		this.next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				transition((MainPanel) trans.getSelectedItem()); 
			}
		});
	}
}
