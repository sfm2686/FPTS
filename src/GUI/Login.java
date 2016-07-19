package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DataInterface.DBInterface;
import Finance.User;
import Market.Market;

/**
 * 
 */

/**
 * This JFrame class represents the login page. This class contains both
 * the views and the controls that validate the user input for login.
 * This class alsp creates popups with error messages for the user.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame {

	/****** Class Attributes ******/
	private JButton login, register;
	private JTextField usernameF;
	private JPasswordField passF;
	private final StringBuffer username, password;

	/****** Class Methods ******/

	/**
	 * Create the frame.
	 * This is the constructor of the login JFrame class.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Login Page");

		this.username = new StringBuffer();
		this.password = new StringBuffer();

		this.setLayout(new BorderLayout());
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Welcome to FPTS"));
		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	private JPanel middle(){
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;

		this.usernameF = new JTextField();
		this.usernameF.setPreferredSize(new Dimension(200, 20));

		panel.add(new JLabel("Username "), gbc);
		gbc.gridx ++;
		panel.add(this.usernameF, gbc);

		gbc.gridy ++;
		gbc.gridx = 0;

		panel.add(new JLabel(" "), gbc);
		gbc.gridy ++;

		this.passF = new JPasswordField();
		this.passF.setPreferredSize(new Dimension(200, 20));

		panel.add(new JLabel("Password "), gbc);
		gbc.gridx ++;
		panel.add(this.passF, gbc);

		return panel;
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the bottom or SOUTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	private JPanel bottom(){
		JPanel panel = new JPanel();

		this.login = new JButton("Login");
		this.register = new JButton("Register");
		panel.add(this.login);
		panel.add(this.register);

		return panel;
	}


	/**
	 * This is a private helper method that starts a dialog to take the time
	 * interval for updating stocks from the user at login.
	 */
	private void takeTime(){
		String w, s;
		Integer time = 0;
		boolean valid = false;
		while ( !valid ){
			s = JOptionPane.showInputDialog("Please enter a time interval, in seconds, for stocks to be updadted."
					+ " Default update time is 60s.");
			try {
				if ( s == null || s.equals("")){
					Market.getMarketInstance().setUpdateInterval(60);
					valid = true;
					return ;
				}
				time = Integer.parseInt(s);
				if ( time > 0 ){
					Market.getMarketInstance().setUpdateInterval(time);
					valid = true;
				}
				else 
					time = Integer.parseInt("THROW");
			} catch(Exception e) {
				w = "Please enter a positive integer value.";
				JOptionPane.showMessageDialog(new JFrame(), w, "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	private void assign(){
		this.usernameF.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				username.delete(0, username.length());
				username.append(usernameF.getText());
			}

			@Override
			public void focusGained(FocusEvent e) {
				;
			}
		});

		this.passF.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				password.delete(0, password.length());
				password.append(passF.getPassword());
			}

			@Override
			public void focusGained(FocusEvent e) {
				;
			}
		});

		this.login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String w;
				if ( username.length() == 0 || password.length() == 0 ){
					w = "Username or Password fields were empty. Please fill try again";
					JOptionPane.showMessageDialog(new JFrame(), w, "Empty Field/s",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				User user = DBInterface.getUserData(username.toString()); 

				if ( user == null ){
					w = "Username is not in the system. Please register or try again";
					JOptionPane.showMessageDialog(new JFrame(), w, "No User",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else if ( user.getPass().equalsIgnoreCase(password.toString()) ) {
					//Goes to MainFrame
					takeTime();
					MainFrame main = new MainFrame(user, new AcctOverview(user));
					main.setVisible(true);
					dispose();
				}
			}
		});

		this.register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registeration next = new Registeration();
				next.setVisible(true);
			}
		});
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
	}

}
