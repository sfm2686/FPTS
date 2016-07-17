
/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import DataInterface.DBInterface;
import Finance.*;
import Transaction.*;
import WatchList.WatchList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class MainFrame extends JFrame {

	private User user;
	private JButton watchList, undo, redo, logout;
	private JPanel mainPanel, watchListPanel;
	
	//Menu attrs
	private JComboBox<String> menu;
	private final String[] menuItems = {"Account Overview", "Simulation", "Transaction Menu",
			"View Log", "View Recent Transactions"};
	

	/**
	 * Create the frame.
	 */
	public MainFrame(User user, JPanel mainPanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Main View");
		this.user = user;
		this.mainPanel = mainPanel;
		
		this.setLayout(new BorderLayout());
		
		this.add(top(), BorderLayout.NORTH);
		this.add(this.mainPanel, BorderLayout.CENTER);
//		this.add(mainView(), BorderLayout.CENTER);
		
		//FOR NOW MAKING A NEW WATCHLIST INSTEAD OF USING THE USER'S ..
		this.watchListPanel = new WatchListGUI((new WatchList(0)));
		this.add(this.watchListPanel, BorderLayout.EAST);
		this.assign();

	}

	private JPanel top(){
		JPanel panel = new JPanel(new FlowLayout());
		
		this.menu = new JComboBox<>(this.menuItems);
		panel.add(menu);
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel currentUser = new JLabel("Logged in as: " + this.user.getUserName());
		panel.add(currentUser);
		
		this.watchList = new JButton("Watch List");
		this.logout = new JButton("Logout");
		this.undo = new JButton("Undo");
		this.redo = new JButton("Redo");

		panel.add(this.undo);
		panel.add(this.redo);
		panel.add(this.watchList);
		panel.add(this.logout);
		//panel.setBackground(new Color(50000));
		return panel;
	}
	
	private JPanel mainView(){
		JPanel panel = new JPanel();
		JTextPane acctOverview = new JTextPane();
		acctOverview.setText("TESTING ACCOUNT OVERVIEW RIGHT NOW");
		panel.add(acctOverview);
		panel.setBackground(new Color(12000));
		panel.setSize(new Dimension(500, 700));
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
				
				switch(menu.getSelectedIndex()) {
				case 0: mainPanel = new AcctOverview(user);
					break;
				case 1: mainPanel = new SimulationSettings(MainFrame.this, user);
					break;
				case 2: mainPanel = new TransactionMenu(user);
					break;
				case 3: mainPanel = new LogView(user);
					break;
				case 4: mainPanel = new RecentTransactions(user);
					break;
				}
				//End case
				refresh(mainPanel);
			}
		});
		
		//Logout
		this.logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String w = "Are you sure you want to save changes and logout?";
				if (JOptionPane.showConfirmDialog(null, w, "Logout", 
					    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
					    == JOptionPane.YES_OPTION){
					
						DBInterface.saveUserData(user);
						//TODO
						// CLOSE USER SESSION
						user = null;
						Login login = new Login();
						login.setVisible(true);
						dispose();
					}
					else
						return ;
			}
		});
		
		this.watchList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	
	@Override
	public String toString(){
		return "MainFrame";
	}
}
