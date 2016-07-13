package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DataInterface.DBInterface;
import Finance.User;

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
	
	private final StringBuffer username, password;
	/**
	 * Create the frame.
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
		
		return panel;
	}
	
	private JPanel bottom(){
		JPanel panel = new JPanel();
		
		this.login = new JButton("Login");
		this.register = new JButton("Register");
		panel.add(this.login);
		panel.add(this.register);
		
		return panel;
	}
	
	private void assign(){

		this.usernameF.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				username.delete(0, username.length());
				username.append(usernameF.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				; //Empty for now ..
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
				; //Empty for now ..
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
				boolean valid = true;
				User user = DBInterface.getUserData(username.toString()); 
				System.out.println("username: " + username.toString());
				
				if ( user == null ){
					System.out.println(user);
					w = "Username does not in the system. Please register or try again";
					JOptionPane.showMessageDialog(new JFrame(), w, "No User",
							JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else if ( user.getPass().equalsIgnoreCase(password.toString()) ) {
					
					//Code below should be replaced with transition to main page..
					MainFrame main = new MainFrame(user);
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
