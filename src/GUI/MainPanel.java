package GUI;

import java.awt.*;
import javax.swing.*;
import Finance.User;

/**
 * MainPanel serves as template for the GUI for this application. It provides 
 * information that should be common to all subclasses, and it enforces a few methods
 * so that the GUI has a uniform look and feel.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
abstract class MainPanel extends JPanel{

	protected final Dimension TEXTD = new Dimension(120, 20);
	
	private MainFrame mainFrame;
	private User user;
	
	public MainPanel(MainFrame mainFrame, User user){
		this.mainFrame = mainFrame;
		this.user = user;
		this.setPreferredSize(new Dimension(1000, 750));
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
