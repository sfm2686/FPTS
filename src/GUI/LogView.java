/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import Finance.User;

/**
 * This class is the GUI representation of the Log owned by the user.
 * The page does not transition anywhere else, it just shows the information
 * in the log that the user owns.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class LogView extends MainPanel {

	/**
	 * Create the panel. Store the user in the parent class.
	 * The class passes null for the main frame to its parent class
	 * because it does not go anywhere.
	 * 
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public LogView(User user) {
		super(null, user);
		
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
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Log View"));
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		if ( getUser().getLog() == null )
			return panel;
		@SuppressWarnings("rawtypes")
		DefaultListModel model = new DefaultListModel();
		String[] logA = getUser().getLog().toString().split("Portfolio Operated On:");
		
		for ( String s : logA ){
			model.addElement(s);
		}

		@SuppressWarnings("rawtypes")
		JList list = new JList(model);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		scrollPane.setPreferredSize(new Dimension(650, 650));
		panel.add(scrollPane, BorderLayout.CENTER);
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
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
		return "View Log";
	}
}
