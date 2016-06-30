package Core;

import java.util.ArrayList;
import Finance.Portfolio;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class User implements Serializable {

	private final String username;
	private String password;
	private ArrayList<Portfolio> portfolios;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
		this.portfolios = new ArrayList<Portfolio>();
	}
	
	public ArrayList<Portfolio> getPorts(){
		return this.portfolios;
	}
	
	public String getUserName(){
		return this.username;
	}
	
	protected String getPass(){
		return this.password;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
