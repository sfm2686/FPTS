package GUI;

import java.awt.*;
import javax.swing.*;

import Transaction.*;

import Finance.Portfolio;
import Finance.User;

/**
 * This class is a representation of the Account overview 
 * functionality.
 * This class extends MainPanel.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
public class AcctOverview extends MainPanel {

	
	/****** Class Methods ******/
	
	/**
	 * Create the panel. Store the user in the parent class.
	 * The class passes null for the main frame to its parent class
	 * because it does not go anywhere.
	 * 
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public AcctOverview(User user) {
		super(null, user);
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.WEST);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Account Overview"));
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(new JLabel("Portfolios: "), BorderLayout.NORTH);
		
		if ( getUser().getPorts().size() == 0 )
			return panel;
		
		DefaultListModel model = new DefaultListModel();
		
		for ( Portfolio port : getUser().getPorts() ){
			model.addElement(port);
			model.addElement(" ");
		}

		JList list = new JList(model);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		scrollPane.setPreferredSize(new Dimension(950, 700));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	protected void assign(){
		
	}
	
	/**
	 * The toString for this class.
	 * 
	 * @return: A string representation of this class.
	 */
	@Override
	public String toString(){
		return "Account Overview";
	}
}
