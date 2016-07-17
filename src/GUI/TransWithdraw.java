/**
 * 
 */
package GUI;

import java.awt.BorderLayout;

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
	public TransWithdraw(MainFrame mainFrame, User user, Portfolio port) {
		super(mainFrame, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	@Override
	protected JPanel top() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JPanel middle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JPanel bottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void assign() {
		// TODO Auto-generated method stub
		
	}

}
