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
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import Finance.User;
import WatchList.*;
import javafx.scene.control.ScrollBar;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class WatchListGUI extends JPanel {

	private ArrayList<WItemLabel> witems = new ArrayList<WItemLabel>();
	private JList labels = new JList();
	private JButton addButton;
	private MainFrame mainFrame;
	private User user;
	private JComboBox<String> menu;
	private HashMap<String, WatchListItem> map;
	
	/**
	 * Create the panel.
	 */
	public WatchListGUI(MainFrame mainFrame, User user) {
		this.mainFrame = mainFrame;
		this.user = user;
		this.map = new HashMap<>();
		this.fill();
		
		this.setBorder(
	            BorderFactory.createTitledBorder("Watch List"));
		this.setLayout(new BorderLayout());
		
		DefaultListModel model = new DefaultListModel();
//		for ( int i = 0; i < 40; i ++ ){
//			model.addElement(new WItemLabel("APPLE"));
//			model.addElement(new WItemLabel("GOOG"));
//		}
		
		String[] temp = new String[this.map.keySet().size()];
		int i = 0;
		for ( String s : this.map.keySet() ){
			model.addElement(s);
			temp[i++] = s; 
		}
		
		this.addButton = new JButton("Add New");
		this.menu   = new JComboBox<>(temp);
		this.labels = new JList(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.labels);
		scrollPane.setPreferredSize(new Dimension(120, 700));
		this.add(this.addButton, BorderLayout.NORTH);
		this.add(this.menu, BorderLayout.CENTER);
		this.add(scrollPane, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(140, 500));
		
		this.assign();
	}

	private void fill(){
		Iterator it = user.getWatchList().getIterator();
		String s;
		while ( !it.isDone() ){
			s = it.currentItem().getEq().getName() +
					": " + it.currentItem().getEq().getPrice();
			this.map.put(s, it.currentItem());
			it.next();
		}
	}
	
	private void assign(){
		this.addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.refresh(new AddWLItem(mainFrame, user));
			}
		});
		
		this.menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.refresh(new ManageWLItem(mainFrame, user, 
						map.get(menu.getSelectedItem())));
			}
		});
	}
}
