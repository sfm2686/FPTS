package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Market.Market;
import DataInterface.DBInterface;
import Finance.*;
import Transaction.*;
import TransactionStorage.UndoRedo;

/**
 * This class represents the main frame that contains everything
 * the user can do/see in the whole app. The main frame does not use
 * the java built in method to "repaint" itself. Instead it does it by
 * making a new instance of itself and replacing the old window with the
 * new refreshed one. The class also contains the controls that go to different
 * mainPanel panels that contain the main functionality of the current page or the
 * state of the Main Frame. This class also contains watch list panel on the side
 * that is somewhat interactive with the user at all times.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/****** Class Attributes ******/
	private User user;
	private JButton save, undo, redo, logout;
	private JPanel mainPanel, watchListPanel;
	private JComboBox<MainPanel> menu;
	private ArrayList<MainPanel> menuItems;
	
	/****** Class Methods ******/
	
	/**
	 * Create the frame. Constructor for this class.
	 * 
	 * @param user: current logged in user.
	 * @param mainPanel: the main panel to be drawn in the main area 
	 * 					 of the main frame.
	 */
	public MainFrame(User user, JPanel mainPanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1100, 850));
		this.setTitle("Main View");
		this.user = user;
		this.mainPanel = mainPanel;
		this.fillMenu();
		this.setLayout(new BorderLayout());
		
		this.add(top(), BorderLayout.NORTH);
		this.add(this.mainPanel, BorderLayout.CENTER);
		
		this.watchListPanel = new WatchListGUI(this, this.user);
		this.add(this.watchListPanel, BorderLayout.EAST);
		this.assign();
	}
	
	/**
	 * Private helper method to fill the menu variable
	 * that contains what the main menu of the app displays.
	 */
	private void fillMenu(){
		this.menuItems = new ArrayList<MainPanel>();
		this.menuItems.add(new AcctOverview(this.user));
		this.menuItems.add(new SimulationSettings(this, user));
		this.menuItems.add(new TransactionMenu(this, user));
		this.menuItems.add(new LogView(this.user));
		this.menuItems.add(new CreatePortfolio(this, user));
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	private JPanel top(){
		JPanel panel = new JPanel(new FlowLayout());
		
		MainPanel[] temp = new MainPanel[this.menuItems.size()];
		
		for ( int i = 0; i < temp.length; i ++ )
			temp[i] = this.menuItems.get(i);
		this.menu = new JComboBox<MainPanel>(temp);
		panel.add(menu);
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel currentUser = new JLabel("Logged in as: " + this.user.getUserName());
		panel.add(currentUser);
		
		this.save = new JButton("Save");
		this.undo = new JButton("Undo");
		this.redo = new JButton("Redo");
		this.logout = new JButton("Logout");


		panel.add(this.save);
		panel.add(this.undo);
		panel.add(this.redo);
		panel.add(this.logout);
		//panel.setBackground(new Color(50000));
		return panel;
	}
	
	/**
	 * This method contains the refresh functionality.
	 * The method creates a new instance of the MainFrame class that has
	 * a new panel. Sets the new instance to visible and disposes the old one.
	 * When this method is called the main window of the app appears to be
	 * flashing as it refreshes.
	 * 
	 * @param panel: The new main panel to replace the old one in the refresh.
	 */
	public void refresh(JPanel panel){
		this.setVisible(false);
		this.dispose();
		MainFrame main = new MainFrame(this.user, panel);
		main.setVisible(true);
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	private void assign(){
		
		//Drop-down menu
		this.menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( user.getPorts().size() == 0 && 
						menu.getSelectedItem() instanceof SimulationSettings ){
					String w = "You have to own at least 1 portfolio to simulate.";
					JOptionPane.showMessageDialog(new JFrame(), w, "No Portfolio",
								JOptionPane.ERROR_MESSAGE);
						return ;
					}
				refresh((MainPanel)menu.getSelectedItem());
			}
		});
		
		//Logout
		this.logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w = "Are you sure you want to save changes and logout?";
				if (JOptionPane.showConfirmDialog(null, w, "Logout", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		
					UndoRedo stack = Invoker.getInvoker(user.getLog()).getUndoRedoStack();
					stack.clean(user.getLog());
					
					DBInterface.saveUserData(user);
					// CLOSE USER SESSION
					user = null;
					Login login = new Login();
					MainFrame.this.setVisible(false);
					MainFrame.this.dispose();
					login.setVisible(true);
				}
				else
					return ;
			}
		});
		
		this.undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInvoker(user.getLog()).getUndoRedoStack().undo();
				if ( mainPanel instanceof AcctOverview )
					refresh(new AcctOverview(user));
			}
		});
		
		this.redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Invoker.getInvoker(user.getLog()).getUndoRedoStack().redo();
				if ( mainPanel instanceof AcctOverview )
					refresh(new AcctOverview(user));
			}
		});
		
		this.save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w = "Are you sure you want to save all changes and update the log?";
				if (JOptionPane.showConfirmDialog(null, w, "Save", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		
					UndoRedo stack = Invoker.getInvoker(user.getLog()).getUndoRedoStack();
					stack.clean(user.getLog());	
					DBInterface.saveUserData(user);
					if ( mainPanel instanceof LogView )
						refresh(new LogView(user));
				}
				else
					return ;
			}
		});
	}
	
	/**
	 * The toString for this class.
	 * 
	 * @return: A string representation of this class.
	 */
	@Override
	public String toString(){
		return "Main Frame";
	}

	/**
	 * Testing purposes.
	 */
	public static void main(String[] args) {
		Market.getMarketInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User user = new User("TESTING", "nono");
					MainFrame frame = new MainFrame(user, new AcctOverview(user));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
