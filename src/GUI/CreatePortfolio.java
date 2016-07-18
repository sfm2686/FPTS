package GUI;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Finance.Portfolio;
import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class CreatePortfolio extends MainPanel{

	
	/****** Class Attributes ******/

	private JTextField name;
	private JComboBox<String> ports;
	private JButton create;
	
	
	/****** Class Methods ******/
	
	
	/**
	 * @param mainFrame
	 * @param user
	 */
	public CreatePortfolio(MainFrame mainFrame, User user) {
		super(mainFrame, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	@Override
	protected JPanel top() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Create a new Portfolio"));
		return panel;
	}

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

	@Override
	protected JPanel bottom() {
		JPanel panel = new JPanel();
		this.create = new JButton("Create");
		panel.add(this.create);
		return panel;
	}

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
	
	@Override
	public String toString(){
		return "Create Portfolio";
	}

}
