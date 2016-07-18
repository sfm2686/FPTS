/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import Finance.User;
import WatchList.WatchList;
import javafx.scene.control.ScrollBar;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchListGUI extends JPanel {

	private ArrayList<WItemLabel> witems = new ArrayList<WItemLabel>();
	private JList labels = new JList();
	private JButton manage;
	private MainFrame mainFrame;
	private User user;
	
	/**
	 * Create the panel.
	 */
	public WatchListGUI(MainFrame mainFrame, User user) {
		this.mainFrame = mainFrame;
		this.user = user;
		
		this.setBorder(
	            BorderFactory.createTitledBorder("Watch List"));
		this.setLayout(new BorderLayout());
		
		DefaultListModel model = new DefaultListModel();
		for ( int i = 0; i < 40; i ++ ){
			model.addElement(new WItemLabel("APPLE"));
			model.addElement(new WItemLabel("GOOG"));
		}

		this.manage = new JButton("Manage");
		
		this.labels = new JList(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.labels);
		scrollPane.setPreferredSize(new Dimension(120, 700));
		this.add(this.manage, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(140, 600));
		
		this.assign();
	}

	private void assign(){
		this.manage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.refresh(new WatchManager(mainFrame, user));
			}
		});
	}
}
