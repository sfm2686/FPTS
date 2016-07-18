/**
 * 
 */
package GUI;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransWithdraw extends MainPanel {

	/**
	 * Create the panel.
	 */
	public TransWithdraw(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Withdraw money from a cash account"));
		return panel;
	}

	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Remove a Holding from a Portfolio"));
		return panel;
	}

	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Remove a Holding from a Portfolio"));
		return panel;
	}

	@Override
	protected void assign() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString(){
		return "Withdraw Cash";
	}

}
