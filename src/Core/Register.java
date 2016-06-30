/**
 * 
 */
package Core;

import DataInterface.DBInterface;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Register extends State {
	
	private int id = 1;
	private String username;
	private String pw1, pw2;
	private User user;

	/**
	 * @param context
	 */
	public Register(Context context) {
		super(context);
	}

	/*
	 * Can go to:
	 * 1. S0
	 */
	
	//Used for a different purpose in this state.
	@Override
	void displayOptions() {
		System.out.print("Please enter a username: ");
		this.username = getSc().next();
		System.out.print("Please enter a password: ");
		this.pw1 = getSc().next();
		System.out.print("Confirm the password: ");
		this.pw2 = getSc().next();
	}
	
	private boolean isValidPass(String p1, String p2){
		if ( p1.equals(p2) )
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		this.displayOptions();
		while ( DBInterface.getUserData(this.username) != null &&
				!this.isValidPass(pw1, pw2) ){
			if ( DBInterface.getUserData(this.username) != null )
				System.out.println("Username already");
			if ( !this.isValidPass(pw1, pw2) )
				System.out.println("Passwords do not match, please try again");
			this.displayOptions();
		}
		String pass = new StringBuilder(pw1).reverse().toString();
		DBInterface.saveUserData(new User(this.username, pass));
		setNext(0);
	}

	@Override
	int getID() {
		return this.id;
	}

}
