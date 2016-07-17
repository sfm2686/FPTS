/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Finance.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransCreate extends MainPanel {

	private Portfolio workingPort;
	private ButtonGroup holdingType;
	private JButton create;
	private JRadioButton ca, eq;
	private JLabel typeSelected;
	private JPanel selectionPanel;
	private JComboBox<String> ports;
	private CardLayout cards = new CardLayout();
	
	//Only cash accounts for now
	private JTextField name, initialAmount;
	
	/**
	 * Create the panel.
	 */
	public TransCreate(MainFrame mainFrame, User user, Portfolio port) {
		super(mainFrame, user);
		
		this.workingPort = port;
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Creation"));
		return panel;
	}
	
	private JPanel caPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	private JPanel eqPanel(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	private JPanel innerTop(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		this.holdingType = new ButtonGroup();
		this.ca = new JRadioButton("Cash Account");
		this.eq = new JRadioButton("Equity");
		this.holdingType.add(ca);
		this.holdingType.add(eq);
		
		panel.add(ca, c);
		c.gridy ++;
		panel.add(eq, c);
		c.gridy ++;
		
		String[] portsA = new String[getUser().getPorts().size()];
		for (int p = 0; p < getUser().getPorts().size(); p ++)
			portsA[p] = getUser().getPorts().get(p).getName();
		this.ports = new JComboBox<>(portsA);
		
		panel.add(new JLabel("Destination Portfolio "), c);
		c.gridx ++;
		panel.add(this.ports, c);

		return panel;
	}
	
	private void innerMiddle(){
		this.selectionPanel = new JPanel();
		this.selectionPanel.setLayout(cards);
		Dimension fieldD = new Dimension(200, 20);
		
		JPanel temp = new JPanel();
		temp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = c.WEST;
		c.gridx = 0;
		c.gridy = 0;
		
		this.name = new JTextField();
		this.initialAmount = new JTextField();
		this.name.setPreferredSize(fieldD);
		this.initialAmount.setPreferredSize(fieldD);
		
		temp.add(new JLabel("Name "), c);
		c.gridx ++;
		temp.add(this.name, c);
		c.gridx = 0;
		c.gridy ++;
		
		temp.add(new JLabel(" "), c);
		c.gridy ++;
		
		temp.add(new JLabel("Initial Amount"), c);
		c.gridx ++;
		temp.add(this.initialAmount, c);
		
		this.selectionPanel.add(temp, "CA");
		
		temp.removeAll();
		c.gridx = 0;
		c.gridy = 0;
		temp.add(new JLabel("EQUITY SELECTED, UNDER CONSTRUCTION :P"), c);
		
		this.selectionPanel.add(temp, "EQ");
		
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.innerTop(), BorderLayout.NORTH);
		this.innerMiddle();
		panel.add(this.selectionPanel, BorderLayout.CENTER);
		
		return panel;
	}

	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.create = new JButton("Create");
		panel.add(this.create);
		
		return panel;
	}
	
	protected void assign(){
		
		this.ca.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(selectionPanel, "CA");
				selectionPanel.invalidate();
				selectionPanel.validate();
				selectionPanel.repaint();
			}
		});
		
		this.eq.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cards.show(selectionPanel, "EQ");				
			}
		});
		
		this.create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
