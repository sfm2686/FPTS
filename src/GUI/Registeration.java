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
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Registeration extends JFrame {

	private JButton done, clear;
	private JPasswordField pass1, pass2;
	private JTextField usernameF;
	private JLabel usernameL, passL1, passL2;
	
	private final StringBuffer usernameV, passwordV1, passwordV2;
	
	public Registeration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
		this.usernameV = new StringBuffer();
		this.passwordV1 = new StringBuffer();
		this.passwordV2 = new StringBuffer();
		
		this.setTitle("Registeration Page");
		this.setLayout(new BorderLayout());
		this.add(pan(), BorderLayout.CENTER);
		this.assign();
	}

	private JPanel pan(){
		JPanel panel = new JPanel(new GridLayout(18, 2));
		
		this.done = new JButton("Done");
		this.clear = new JButton("Clear Fields");
		this.usernameL = new JLabel("Username: ");
		this.usernameF = new JTextField();
		this.pass1 = new JPasswordField();
		this.pass2 = new JPasswordField();
		this.passL1 = new JLabel("Password: ");
		this.passL2 = new JLabel("Confirm Password: ");
		
		panel.add(this.usernameL);
		panel.add(this.usernameF);
		
		panel.add(this.passL1);
		panel.add(this.pass1);
		
		panel.add(this.passL2);
		panel.add(this.pass2);
		
		panel.add(this.done);
		panel.add(this.clear);
		
		return panel;
	}
	
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
				
				User user = DBInterface.getUserData(usernameV.toString());
				String w = "";
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
	 * Launch the application.
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

	/**
	 * Create the frame.
	 */

}
