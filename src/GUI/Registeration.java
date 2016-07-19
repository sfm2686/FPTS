/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import DataInterface.DBInterface;
import Finance.User;

/**
 * This JFrame represents the registration page that users can
 * go to in order to make new accounts that are not in the system.
 * This class contains both the views and the controls of the registration
 * logic.
 * 
 * @authors Sultan Mira, Hunter Caskey
 *
 */
@SuppressWarnings("serial")
public class Registeration extends JFrame {
	
	/****** Class Attributes ******/
	private JButton done, clear;
	private JPasswordField pass1, pass2;
	private JTextField usernameF;
	
	private final StringBuffer usernameV, passwordV1, passwordV2;
	
	/****** Class Methods ******/
	
	/**
	 * Constructor for the Registration class.
	 * Sets up all of the default settings for a JFrame such as
	 * exit on close and size of the window.
	 */
	public Registeration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.usernameV = new StringBuffer();
		this.passwordV1 = new StringBuffer();
		this.passwordV2 = new StringBuffer();
		
		this.setTitle("Registeration Page");
		this.setLayout(new BorderLayout());
		
		this.add(top(), BorderLayout.NORTH);
		this.add(middle(), BorderLayout.CENTER);
		this.add(bottom(), BorderLayout.SOUTH);
		this.assign();
	}

	/**
	 * Helper method returns a panel that contains the
	 * components of the top or NORTH side of this main panel.
	 * 
	 * @return Populated panel.
	 */
	private JPanel top(){
		JPanel panel = new JPanel();
		panel.add(new JLabel("Registration"));
		return panel;
	}
	
	/**
	 * Helper method returns a panel that contains the
	 * components of the middle or CENTER side of this main panel.
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
		this.pass1 = new JPasswordField();
		this.pass1.setPreferredSize(new Dimension(200, 20));
		this.pass2 = new JPasswordField();
		this.pass2.setPreferredSize(new Dimension(200, 20));
		
		panel.add(new JLabel("Username "), gbc);
		gbc.gridx ++;
		panel.add(this.usernameF, gbc);
		
		gbc.gridy ++;
		gbc.gridx = 0;
		
		panel.add(new JLabel(" "), gbc);
		gbc.gridy ++;
		
		panel.add(new JLabel("Password "), gbc);
		gbc.gridx ++;
		panel.add(this.pass1, gbc);
		
		gbc.gridy ++;
		gbc.gridx = 0;
		
		panel.add(new JLabel(" "), gbc);
		gbc.gridy ++;
		
		panel.add(new JLabel("Confirm Password "), gbc);
		gbc.gridx ++;
		panel.add(this.pass2, gbc);

		
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
		this.done = new JButton("Register");
		this.clear = new JButton("Clear Fields");
		
		panel.add(this.done);
		panel.add(this.clear);
		return panel;
	}
	
	/**
	 * This method contains any action listeners for any components
	 * in this class that might need one.
	 */
	private void assign(){
		
		this.clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				usernameF.setText("");
				pass1.setText("");
				pass2.setText("");
			}
		});
		
		this.done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String w = "";
				if ( usernameV.toString().equals("") || pass1.equals("") || pass2.equals("") ){
					w = "One or more fields are empty. Please try again";
					JOptionPane.showMessageDialog(new JFrame(), w, "Bad Password",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
					
				User user = DBInterface.getUserData(usernameV.toString());
				if ( user != null ){
					w = "Username is already in the system. Please try another one";
					JOptionPane.showMessageDialog(new JFrame(), w, "Username Exists",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else if ( !passwordV1.toString().equalsIgnoreCase(passwordV2.toString()) ){
					w = "Passwords do not match. Please try again";
					JOptionPane.showMessageDialog(new JFrame(), w, "Bad Password",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				user = new User(usernameV.toString(), passwordV1.toString());
				DBInterface.saveUserData(user);
				w = "Your account has been created. Please login";
				JOptionPane.showMessageDialog(new JFrame(), w, "Success",
						JOptionPane.DEFAULT_OPTION);
				dispose();
				Login next = new Login();
				next.setVisible(true);
			}
		});
		
		this.usernameF.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				usernameV.delete(0, usernameV.length());
				usernameV.append(usernameF.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//Empty for now ..
			}
		});
		
		this.pass1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				passwordV1.delete(0, passwordV1.length());
				passwordV1.append(pass1.getPassword());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//Empty for now ..
			}
		});
		
		this.pass2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				passwordV2.delete(0, passwordV2.length());
				passwordV2.append(pass2.getPassword());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//Empty for now ..
			}
		});
	}

	/**
	 * Testing purposes.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registeration frame = new Registeration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
