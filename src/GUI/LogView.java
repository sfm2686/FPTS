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
public class LogView extends JPanel {

	private User user;
	/**
	 * Create the panel.
	 */
	public LogView(User user) {
		this.setSize(new Dimension(500, 700));
		this.setLayout(new BorderLayout());
		
		this.user = user;
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Log View"));
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel();
		
		System.out.println("USER.GETLOG()");
		System.out.println(user.getLog());
		if ( user.getLog() == null )
			return panel;
		DefaultListModel model = new DefaultListModel();
		String[] logA = user.getLog().toString().split(".");
		
		for ( String s : logA ){
			model.addElement(s);
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

}
