package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;
import Finance.*;
import Transaction.Client;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransCashTransfer extends MainPanel {

	/****** Class Attributes ******/
	private HashMap<String, Object[]> map;
	private JButton transfer;
	private JComboBox<String> src, dest;
	private JTextField amount;
	
	/****** Class Methods ******/
	
	/**
	 * @param mainFrame
	 * @param user
	 */
	public TransCashTransfer(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		this.map = new HashMap<String, Object[]>();
		this.fill();
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	/**
	 * 
	 */
	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Conduct a Transfer Between Cash Accounts"));
		return panel;
	}

	/* (non-Javadoc)
	 * @see GUI.MainPanel#middle()
	 */
	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		String[] cashAccts = new String[this.map.keySet().size()];
		int i = 0;
		for(String str : this.map.keySet())
			cashAccts[i++] = str;
		this.src = new JComboBox<String>(cashAccts);
		this.dest = new JComboBox<String>(cashAccts);
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.EAST;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(new JLabel("Source Cash Account "), c);
		c.gridx++;
		panel.add(this.src, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Destination Cash Account "), c);
		c.gridx++;
		panel.add(this.dest, c);
		c.gridx = 0;
		c.gridy++;
		
		panel.add(new JLabel("Transfer Amount "), c);
		c.gridx++;
		this.amount = new JTextField();
		this.amount.setPreferredSize(TEXTD); // from main panel
		panel.add(this.amount, c);
		c.gridx = 0;
		c.gridy++;
		
		return panel;
	}

	/* (non-Javadoc)
	 * @see GUI.MainPanel#bottom()
	 */
	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.transfer = new JButton("Transfer");
		panel.add(this.transfer);
		return panel;
	}

	
	private void fill(){
		String label;
		for(Portfolio port : this.getUser().getPorts()){
			for(CashAcct acct : port.getCashAccounts()){
				label = port.getName() + ": " + acct.getName();
				this.map.put(label, new Object[]{port, acct});
			}
		}
	}
	
	//Did not test equity removal yet ..
	protected void assign(){
		this.transfer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				String srcSelect = src.getSelectedItem().toString();
				String destSelect = dest.getSelectedItem().toString();
				String w;
				if(srcSelect.equals(destSelect)){
					w = "You must select two different cash accounts to conduct a transfer.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Selection", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
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
					srcCash = (CashAcct)(map.get(src.getSelectedItem())[1]);
					if (srcCash.getBalance() < d){
						w = "Insufficient funds in source cash account.";
						JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				Portfolio srcPort = (Portfolio)map.get(src.getSelectedItem())[0];
				Portfolio destPort = (Portfolio)map.get(dest.getSelectedItem())[0];
				CashAcct destCash = (CashAcct)map.get(dest.getSelectedItem())[1];

				
				Client client = new Client(getUser());
				client.cashTransfer(destPort, srcPort, destCash.getName(), srcCash.getName(), d);
				transition(new AcctOverview(getUser()));
			}
		});
	}
	
	@Override
	public String toString(){
		return "Transfer Cash";
	}

}
