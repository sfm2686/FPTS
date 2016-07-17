/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import Finance.User;

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
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
	}
	
	abstract protected JPanel top();
	abstract protected JPanel middle();
	abstract protected JPanel bottom();
	abstract protected void assign();
	
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
