import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javafx.scene.control.Button;

/**
 * 
 */

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class newLogin extends JFrame {

	private JPanel panel;
	private JButton login, register;
	private JTextField usernameF;
	private JPasswordField passF;
	private JLabel passL, usernameL, welcomeL;

	/**
	 * Create the frame.
	 */
	public newLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1000, 1000));
		GridLayout labels = new GridLayout(3, 1);
		GridLayout fields = new GridLayout(3, 1);
		
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(1000, 1000));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		
		
		this.login = new JButton("Login");
		this.register = new JButton("Register");
		this.welcomeL = new JLabel("Welcome to FPTS");
		this.usernameF = new JTextField();
		this.usernameL = new JLabel("Username: ");
		this.passF = new JPasswordField();
		this.passL = new JLabel("Password: ");
		
		this.panel.add(this.welcomeL);
		this.panel.add(this.login);
		this.panel.add(this.register);
		this.panel.add(this.usernameF);
		this.panel.add(this.usernameL);
		this.panel.add(this.passF);
		this.panel.add(this.passL);
		
		
		setContentPane(panel);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newLogin frame = new newLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
