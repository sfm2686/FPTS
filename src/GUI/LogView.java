/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Finance.Portfolio;
import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class LogView extends MainPanel {

	/**
	 * Create the panel.
	 */
	public LogView(User user) {
		super(null, user);
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	protected JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Log View"));
		return panel;
	}
	
	protected JPanel middle(){
		JPanel panel = new JPanel();
		
		if ( getUser().getLog() == null )
			return panel;
		DefaultListModel model = new DefaultListModel();
		String[] logA = getUser().getLog().toString().split("Portfolio Operated On:");
		
		for ( String s : logA ){
			model.addElement(s);
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

}
