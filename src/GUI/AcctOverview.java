/**
 * 
 */
package GUI;

import java.awt.*;
import javax.swing.*;

import Finance.CashAcct;
import Finance.Portfolio;
import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AcctOverview extends JPanel {

	private User user;
	/**
	 * Create the panel.
	 */
	public AcctOverview(User user) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		
		this.user = user;
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.WEST);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Account Overview"));
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(new JLabel("Portfolios: "), BorderLayout.NORTH);
		
		testingPorts();
		if ( this.user.getPorts().size() == 0 )
			return panel;
//		JList list = new JList(user.getPorts().toArray()); //data has type Object[]
//		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		list.setVisibleRowCount(-1);
//		list.setSize(new Dimension(450, 600));
		
		DefaultListModel model = new DefaultListModel();
		
		for ( Portfolio port : user.getPorts() ){
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

	private JPanel bottom(){
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	private void assign(){
		
	}
	
	private void testingPorts(){
		for ( int i = 0; i < 5; i ++ ){
			this.user.addPort(new Portfolio("port" + i));
			this.user.getPorts().get(i).addCashAccount(new CashAcct("cash" + i, ( i + 2 ) * 4));
			this.user.getPorts().get(i).addCashAccount(new CashAcct("cash" + i + 1, ( i + 2 ) * 4));
		}
	}
}
