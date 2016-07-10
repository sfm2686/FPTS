/**
 * 
 */
package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Registeration extends JFrame {

	private JButton done, clear;
	private JPasswordField pass1, pass2;
	private JTextField usernameF;
	private JLabel usernameL, passL1, passL2;
	
	public Registeration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800, 800));
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
				System.out.println("Clear Clicked");
			}
		});
		
		this.done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Done CLicked");
			}
		});
		
		this.pass1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Password1: " + e.getActionCommand());
			}
		});
		
		this.pass2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Password2: " + e.getActionCommand());
			}
		});
		
		this.usernameF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Username: " + e.getActionCommand());
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
