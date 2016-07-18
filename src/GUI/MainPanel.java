/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Finance.User;
import Finance.Portfolio;


/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
abstract class MainPanel extends JPanel{

	private MainFrame mainFrame;
	private User user;
	
	public MainPanel(MainFrame mainFrame, User user){
		this.mainFrame = mainFrame;
		this.user = user;
		this.setPreferredSize(new Dimension(1000, 750));;
		this.setLayout(new BorderLayout());
	}
	
	abstract protected JPanel top();
	abstract protected JPanel middle();
	abstract protected JPanel bottom();
	abstract protected void assign();
	
	protected JComboBox getPortDropdown(){
		
		String[] ports = new String[this.user.getPorts().size()];
		
		for (int p = 0; p < getUser().getPorts().size(); p ++)
			ports[p] = getUser().getPorts().get(p).getName();
		return new JComboBox<>(ports);
	}

	
	protected MainFrame getFrame(){
		return this.mainFrame;
	}
	
	protected void transition(MainPanel panel){
		this.mainFrame.refresh(panel);
	}
	
	protected User getUser(){
		return this.user;
	}
	
}
