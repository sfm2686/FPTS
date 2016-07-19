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

	/****** Class Constants ******/
	protected final Dimension TEXTD = new Dimension(120, 20);
	
	/****** Class Attributes ******/
	private MainFrame mainFrame;
	private User user;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class that is also being called by 
	 * all of its sub-classes to set the prams that are being passed, 
	 * it also sets up the default settings of every panel that all of the
	 * sub panels share. Such as the size and the layout.
	 * 
	 * @param mainFrame: MainFrame instance to be stored and used by 
	 * 					 most of the subclasses to transition to a new panel.
	 * @param user: The user instance to be stored and used by most of the subclasses
	 * 				to get user specific information to display. It can also be used
	 * 				to store new created objects in the user instance. This is the 
	 * 				currently logged in user.
	 */
	public MainPanel(MainFrame mainFrame, User user){
		this.mainFrame = mainFrame;
		this.user = user;
		this.setPreferredSize(new Dimension(1000, 750));
		this.setLayout(new BorderLayout());
	}
	
	/**
	 * Abstract method to be implemented by the subclasses
	 * for the top part of the panel. 
	 * 
	 * @return JPanel: to be represented with components.
	 */
	abstract protected JPanel top();
	
	/**
	 * Abstract method to be implemented by the subclasses
	 * for the middle part of the panel. 
	 * 
	 * @return JPanel: to be represented with components.
	 */
	abstract protected JPanel middle();
	
	/**
	 * Abstract bottom to be implemented by the subclasses
	 * for the middle part of the panel. 
	 * 
	 * @return JPanel: to be represented with components.
	 */
	abstract protected JPanel bottom();
	
	/**
	 * Abstract method for the subclasses to implement all of the
	 * action-listeners in for their components.
	 */
	abstract protected void assign();
	
	/**
	 * A helper method for the subclasses to use whenever there is a 
	 * need to get a drop-down menu that contains the current user's
	 * portfolios.
	 *  
	 * @return JComboBox: containing all of the current user's portfolios.
	 */
	@SuppressWarnings("rawtypes")
	protected JComboBox getPortDropdown(){
		String[] ports = new String[this.user.getPorts().size()];
		
		for (int p = 0; p < getUser().getPorts().size(); p ++)
			ports[p] = getUser().getPorts().get(p).getName();
		return new JComboBox<>(ports);
	}

	/**
	 * A getter method for the MainFrame instance.
	 * @return MainFrame: the current Main Frame instance.
	 */
	protected MainFrame getFrame(){
		return this.mainFrame;
	}
	
	/**
	 * This method is responsible for transition into a new Main Frame
	 * with the new MainPanel. This method calls refresh on MainFrame
	 * @param panel
	 */
	protected void transition(MainPanel panel){
		this.mainFrame.refresh(panel);
	}
	
	/**
	 * A getter method for the current user to be used by the subclasses.
	 * @return User: returns the current user that is logged in.
	 */
	protected User getUser(){
		return this.user;
	}
	
}
