package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Finance.Portfolio;
import Finance.User;

/**
 * This page is responsible for creation of a Portfolio and adding it
 * to the collection of portfolios that the current logged in user owns.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class CreatePortfolio extends MainPanel{

	
	/****** Class Attributes ******/

	private JTextField name;
	private JButton create;
	
	
	/****** Class Methods ******/
	
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	public CreatePortfolio(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
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
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Create a new Portfolio"));
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@SuppressWarnings("static-access")
	@Override
	protected JPanel middle() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		this.name = new JTextField();
		
		panel.add(new JLabel("Name "), c );
		c.gridx ++;
		panel.add(this.name, c);
		this.name.setPreferredSize(TEXTD);
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.create = new JButton("Create");
		panel.add(this.create);
		return panel;
	}

	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	@Override
	protected void assign() {
		this.create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w;
				for ( Portfolio port : getUser().getPorts() )
					if ( port.getName().equalsIgnoreCase(name.getText()) ){
						w = "A Portfolio with the same name is already owned.";
						JOptionPane.showMessageDialog(new JFrame(), w, "No User",
								JOptionPane.ERROR_MESSAGE);
						return ;
					}
				if ( !name.getText().matches(".*[a-zA-Z]+.*") ){
					w = "Invalid name, it should contain at least one letter.";
					JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				getUser().addPort(new Portfolio(name.getText()));
				transition(new AcctOverview(getUser()));
			}
		});
	}
	
	/**
	 * The toString for this class.
	 * 
	 * @return: A string representation of this class.
	 */
	@Override
	public String toString(){
		return "Create Portfolio";
	}

}
