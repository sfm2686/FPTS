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
public class WatchListGUI extends JPanel implements Observer{

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
		this.user.getWatchList().addObserver(this);

		
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
		scrollPane.setPreferredSize(new Dimension(120, 680));

		this.add(this.addButton, BorderLayout.NORTH);
		
		JPanel temp2 = new JPanel();
		temp2.setLayout(new BorderLayout());
		temp2.add(new JLabel("Manage:"), BorderLayout.NORTH);
		temp2.add(this.menu, BorderLayout.CENTER);
		
		this.add(temp2, BorderLayout.CENTER);
		
		this.add(scrollPane, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(140, 500));
		
		this.assign();
	}

	private void fill(){
		Iterator it = user.getWatchList().getIterator();
		String s;
		WatchListItem item;
		SetStateVisitor v = new SetStateVisitor();
		while ( !it.isDone() ){
			item = it.currentItem();
			item.accept(v);
			
			s = it.currentItem().getEq().getName() +
					": " + it.currentItem().getEq().getPrice() + ": " +
					it.currentItem().getState();
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
				WatchListItem item = map.get(menu.getSelectedItem());
				mainFrame.refresh(new ManageWLItem(mainFrame, user, item));
			}
		});
	}

	/**
	 * Everytime the model tells the GUI to update, refresh the look.
	 */
	@Override
	public void update(Observable o, Object arg) {
		fill();
		this.setVisible(true);
		this.mainFrame.add(this, BorderLayout.EAST);
	}
}
