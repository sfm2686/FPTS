package Core;

import java.util.ArrayList;
import Finance.Portfolio;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class represents the user in the system. The class contains everything
 * that the user owns, portfolios, username, password. This class doesn not
 * do much on its own but it is very important for some states and the DBinterface
 * to write and read data from the database.
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
	
	protected void addPort(Portfolio port){
		this.portfolios.add(port);
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
