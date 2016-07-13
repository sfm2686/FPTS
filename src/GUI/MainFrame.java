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

	private JPanel contentPane;
	private User user;
	

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Main View");
		this.user = user;
		
		this.setLayout(new GridLayout(2, 2));
		
		this.add(topLeft());
		this.add(topRight());
		this.add(mainView());
		this.add(watchList());
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
	
	private JPanel watchList(){
		JPanel panel = new JPanel();
		
		TextArea area = new TextArea(30, 5);
		JScrollPane list = new JScrollPane(area);
		
		panel.add(list);
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
