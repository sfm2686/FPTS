package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import Finance.User;
import WatchList.*;

/**
 * This is the Watch List panel on the right side of the main app window.
 * This panel contains 3 main components. A button that takes the user to a new
 * main panel to add a new watch list item.
 * The second main component in this panel is the drop-down menu that enables
 * the user to manage the selected watch list item.
 * The third main component is the display list of the current watch list items
 * along with their states.
 * This class implements Observer, the Observable is the watch list object
 * that is owned by the current logged in user.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class WatchListGUI extends JPanel implements Observer{

	/****** Class Attributes ******/
	@SuppressWarnings("rawtypes")
	private JList labels = new JList();
	private JButton addButton;
	private MainFrame mainFrame;
	private User user;
	private JComboBox<String> menu;
	private HashMap<String, WatchListItem> map;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor of this class. Calls the constructor of the super
	 * then it calls helper methods to initiate its components.
	 * 
	 * @param mainFrame: main frame instance to be passed and stored by the super.
	 * @param user: the user to be stored in the parent class and used
	 * 				to extract information to display/use.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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

	/**
	 * This is a private helper method that fills and sorts the HashMap variable.
	 * This method also uses the Visitor and the Iterators patterns that are defined
	 * in the WatchList Subsystem.
	 */
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
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
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
	 * 
	 * @param o: The subject of this observer(Not used).
	 * @param arg: Used to parameterizing(Not used).
	 */
	@Override
	public void update(Observable o, Object arg) {
		fill();
		this.setVisible(true);
		this.mainFrame.add(this, BorderLayout.EAST);
	}
}
