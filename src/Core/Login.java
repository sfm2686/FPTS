/**
 * 
 */
package Core;

import DataInterface.DBInterface;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class Login extends State{

	
	//State number in the look-up table
	private int id = 0;
	private String username;
	private String password;
	private User user;
	 /**
	 * 
	 */
	public Login(Context context) {
		super(context);
	} 


	@Override
	void displayOptions() {
		
		/*
		 * Can go to:
		 * 1. S1
		 * 2. S2
		 */
		System.out.println("Options ");
		System.out.println("\tRegister (enter: 1) ");
		System.out.println("\tLogin (enter: 2");
		System.out.print("Taking input: ");
	}
	
	@Override
	void execute() {
		System.out.println("\n------Login-----\n");

		this.displayOptions();
		int in = getSc().nextInt();
		while ( !isValid(1, 2, in) ){
			System.out.println("Invalid input, please try again");
			this.displayOptions();
			in = getSc().nextInt();
		}
		
		if ( in == 1 ){
			setNext(in - 1);
			return ;
		}
		else {
			this.logIn();
			getContext().setUser(this.user);
			setNext(in - 1);
		}
			
	}

	private void logIn(){
		this.getUserInfo();
		//Make believe hashing for now :P
		String checkPass = new StringBuilder(this.password).reverse().toString();
		while ( user == null && !this.user.getPass().equals(checkPass) ){
			System.out.println("Invalid input, please try again");
			this.getUserInfo();
			checkPass = new StringBuilder(this.password).reverse().toString();
		}
	}
	

	private void getUserInfo(){
		System.out.print("Enter your username: ");
		this.username = getSc().next();
		System.out.print("Enter your password: "); //SHOULD NOT BE VISIABLE ///////////////////////////////////
		this.password = getSc().next();
		this.user = DBInterface.getUserData(this.username);
	}

	@Override
	int getID() {
		return this.id;
	}
}
