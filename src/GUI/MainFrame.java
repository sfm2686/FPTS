
/**
 * 
 */
package GUI;

import java.awt.*;
import javax.swing.*;

import Finance.*;
import Transaction.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class MainFrame extends JFrame {

	private User user;
	private JButton watchList, undo, redo, logout;
	

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Main View");
		this.user = user;
		
//		this.setLayout(new GridBagLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		
//		c.weightx = 1;
//		c.weighty = 1;
//		c.anchor = GridBagConstraints.NORTH;
//		this.add(top(), c);
//		
//		
//		c.anchor = GridBagConstraints.NORTHEAST;
//		
//		c.anchor = GridBagConstraints.CENTER;
//		this.add(mainView(), c);
//		
//		c.anchor = GridBagConstraints.EAST;
//		this.add(new WatchList(), c);
		
		this.setLayout(new BorderLayout());
		
		this.add(top(), BorderLayout.NORTH);
		this.add(mainView(), BorderLayout.CENTER);
		this.add(new WatchList(), BorderLayout.EAST);
	}
	
	private JPanel top(){
		JPanel panel = new JPanel(new FlowLayout());
		String[] items = {"Account Overview", "Simulation", "Transaction Menu", "View Log"
		                  , "View Recent Transaction"};
		
		JComboBox<String> menu = new JComboBox<>(items);
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
		//panel.setBackground(new Color(12000));
		panel.setSize(new Dimension(500, 700));
		return panel;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(new User("testing..", "nono"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
