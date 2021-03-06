package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Transaction.*;

import Finance.*;

/**
 * This panel enables the current logged in user to withdraw a specified
 * amount of cash from an already owned cash account.
 * The panel validates all of the input before completing the transaction.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class TransWithdraw extends MainPanel {

	
	/****** Class Attributes ******/
	private HashMap<String, Object[]> map;
	private JButton withdraw;
	private JComboBox<String> src;
	private JTextField amount;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public TransWithdraw(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.map = new HashMap<String, Object[]>();
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
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Withdraw money from a cash account"));
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
		
		String[] cashAccts = new String[this.map.keySet().size()];
		int i = 0;
		for(String str : this.map.keySet())
			cashAccts[i++] = str;
		this.src = new JComboBox<String>(cashAccts);
		
		this.amount = new JTextField();
		this.amount.setPreferredSize(TEXTD);
		
		panel.add(new JLabel("Cash Account "), c);
		c.gridx ++;
		panel.add(this.src, c);
		c.gridx = 0;
		c.gridy ++;
		
		panel.add(new JLabel("Amount "), c);
		c.gridx ++;
		panel.add(this.amount, c);
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
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.withdraw = new JButton("Withdraw");
		panel.add(this.withdraw);
		return panel;
	}
	
	/**
	 * This is a private helper method that fills and sortsthe HashMap variable.
	 */
	private void fill(){
		String label;
		for(Portfolio port : this.getUser().getPorts()){
			for(CashAcct acct : port.getCashAccounts()){
				label = port.getName() + ": " + acct.getName()
				+ ", balance: " + acct.getBalance();
				this.map.put(label, new Object[]{port, acct});
			}
		}
	}

	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.withdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w;
				Double d = null;
				CashAcct srcCash;
				try{
					d = Double.parseDouble(amount.getText());
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
					else
						return ;
					srcCash = (CashAcct)(map.get(src.getSelectedItem())[1]);
					if (srcCash.getBalance() < d){
						w = "Insufficient funds in source cash account.";
						JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				

				Portfolio srcPort = (Portfolio)map.get(src.getSelectedItem())[0];
				Client client = new Client(getUser());
				client.withdrawCash(srcPort, srcCash.getName(), d);
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
		return "Withdraw Cash";
	}
}
