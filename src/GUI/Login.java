package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 */

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Login extends JFrame {

	private JButton login, register;
	private JTextField usernameF;
	private JPasswordField passF;
	private JLabel passL, usernameL, welcomeL;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.setTitle("Login Page");
		
		this.setLayout(new BorderLayout());
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.assign();
	}
	
	private JPanel top(){
		JPanel panel = new JPanel();
		this.welcomeL = new JLabel("Welcome to FPTS");
		panel.add(welcomeL);
		return panel;
	}
	
	private JPanel middle(){
		JPanel panel = new JPanel(new GridLayout(3, 2));
		
		
		this.usernameL = new JLabel("Username: ");
		this.usernameF = new JTextField();
		
		panel.add(this.usernameL);
		panel.add(this.usernameF);
		
		this.passL = new JLabel("Password: ");
		this.passF = new JPasswordField();
		panel.add(this.passL);
		panel.add(this.passF);
		
		this.login = new JButton("Login");
		this.register = new JButton("Register");
		panel.add(this.login);
		panel.add(this.register);
		return panel;
	}
	
	private void assign(){
		this.usernameF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				usernameFieldAction(e);
			}
		});
		
		this.passF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordFieldAction(e);
			}
		});
		
		this.login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Login button clicked");
			}
		});
		
		this.register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Register button clicked");
			}
		});
	}
	
	private void usernameFieldAction(ActionEvent evt){
		System.out.println("IN USERNAME FIELD :" + evt.getActionCommand());
	}
	
	private void passwordFieldAction(ActionEvent evt){
		System.out.println("IN PASSWORD FIELD: " + evt.getActionCommand());
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
