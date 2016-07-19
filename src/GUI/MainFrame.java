
/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import Market.Market;
import DataInterface.DBInterface;
import Finance.*;
import Transaction.*;
import TransactionStorage.Log;
import TransactionStorage.UndoRedo;
import WatchList.WatchList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class MainFrame extends JFrame {

	private User user;
	private JButton save, undo, redo, logout;
	private JPanel mainPanel, watchListPanel;
	
	//Menu attrs
	private JComboBox<MainPanel> menu;
	private ArrayList<MainPanel> menuItems;
	/**
	 * Create the frame.
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
//		this.add(mainView(), BorderLayout.CENTER);
		
		this.watchListPanel = new WatchListGUI(this, this.user);
		this.add(this.watchListPanel, BorderLayout.EAST);
		this.assign();
	}
	
	private void fillMenu(){
		this.menuItems = new ArrayList<MainPanel>();
		this.menuItems.add(new AcctOverview(this.user));
		this.menuItems.add(new SimulationSettings(this, user));
		this.menuItems.add(new TransactionMenu(this, user));
		this.menuItems.add(new LogView(this.user));
		this.menuItems.add(new CreatePortfolio(this, user));
	}

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
	
	public void refresh(JPanel panel){
		MainFrame main = new MainFrame(this.user, panel);
		main.setVisible(true);
		this.dispose();
	}
	
	private void assign(){
		
		//Drop-down menu
		this.menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
					//TODO
					// CLOSE USER SESSION
					user = null;
					Login login = new Login();
					dispose();
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
	
	@Override
	public String toString(){
		return "Main Frame";
	}

	/**
	 * Launch the application.
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
