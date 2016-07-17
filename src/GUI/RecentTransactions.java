/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class RecentTransactions extends JPanel {

	private User user;
	/**
	 * Create the panel.
	 */
	public RecentTransactions(User user) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		this.user = user;
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("PANEL NAME"));
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel();
		
		return panel;
	}

	private JPanel bottom(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	private void assign(){
		
	}
}
