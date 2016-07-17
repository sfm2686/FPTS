/**
 * 
 */
package GUI;

import java.awt.*;
import javax.swing.*;

import Transaction.*;

import Finance.CashAcct;
import Finance.Portfolio;
import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AcctOverview extends MainPanel {

	
	/**
	 * Create the panel.
	 */
	public AcctOverview(User user) {
		super(null, user);
		
		this.add(this.top(), BorderLayout.NORTH);
		this.add(this.middle(), BorderLayout.WEST);
		this.add(this.bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Account Overview"));
		return panel;
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(new JLabel("Portfolios: "), BorderLayout.NORTH);
		

		//TESTING .. 
		testingPorts();
		
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
		scrollPane.setPreferredSize(new Dimension(650, 650));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		return panel;
	}

	protected JPanel bottom(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	protected void assign(){
		
	}
	
	private void testingPorts(){

		Client c = new Client(getUser());
		for ( int i = 0; i < 5; i ++ ){
			getUser().addPort(new Portfolio("port" + i));
			//System.out.println("i == " + i);
		}
	//	System.out.println("user.getPorts().size() == " + this.user.getPorts().size());
		
		for ( int i = 0; i < 5; i ++ ){
			c.createCash(getUser().getPorts().get(i), "cash" + i, ( i + 2 ) * 4);
			c.createCash(getUser().getPorts().get(i), "cash" + i + 1, ( i + 2 ) * 4);
		}
	}
}
