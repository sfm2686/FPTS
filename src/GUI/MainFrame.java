
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
	

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Main View");
		this.user = user;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.weighty = 1;
		
		this.add(topLeft(), c);
		
		c.anchor = GridBagConstraints.NORTH;
		this.add(topRight(), c);
		this.add(mainView());
		this.add(new WatchList());
	}
	
	private JPanel topLeft(){
		JPanel panel = new JPanel(new FlowLayout());
		String[] items = {"Account Overview", "Simulation", "Transaction Menu", "View Log"
		                  , "View Recent Transaction", "Logout"};
		
		JComboBox<String> menu = new JComboBox<>(items);
		panel.add(menu);
		
		return panel;
	}
	
	private JPanel topRight(){
		JPanel panel = new JPanel();
		
		JLabel currentUser = new JLabel("Logged in as: " + this.user.getUserName());
		panel.add(currentUser);
		
		return panel;
	}

	
	private JPanel mainView(){
		JPanel panel = new JPanel();
		JTextPane acctOverview = new JTextPane();
		acctOverview.setText("TESTING ACCOUNT OVERVIEW RIGHT NOW");
		panel.add(acctOverview);
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
