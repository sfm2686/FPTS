package Finance;

import java.util.ArrayList;
import java.io.Serializable;
import TransactionStorage.Log;
import WatchList.*;

/**
 *  The User class represents a user's account within the system. All of the user's persisted
 *  data is stored via their User object, so each username is a unique identifier. The User
 *  object is a aggregation of portfolios, and has ownership over a log and a watchlist.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	/****** Class Attributes ******/
	private final String username;
	private String password;
	private ArrayList<Portfolio> portfolios;
	private Log log;
	private WatchList watchList;

	/****** Class Methods ******/

	/**
	 * Constructor for a User object.
	 * 
	 * @param username A String representing the user's unique identifier.
	 * @param password A (hashed) String containing the user's login access code.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.portfolios = new ArrayList<Portfolio>();
		this.log = new Log();
		this.watchList = new WatchList(60); // Default the watchlist to update every minute.
	}

	/**
	 * This methods adds a portfolio to the user's collection of portfolios.
	 * This method will not add the portfolio if the user already owns
	 * a portfolio with the same name.
	 * 
	 * @param port The portfolio to be added to the user's collection.
	 */
	public void addPort(Portfolio port) {
		for ( Portfolio p : this.portfolios )
			if ( p.getName().equalsIgnoreCase(port.getName()) )
				return ;
		this.portfolios.add(port);
	}

	/**
	 * Accessor method for the user's collection of portfolios.
	 * @return The user's collection of portfolios.
	 */
	public ArrayList<Portfolio> getPorts() {
		return this.portfolios;
	}

	/**
	 * Accessor method for the user's username.
	 * @return A String representing the user's username.
	 */
	public String getUserName() {
		return this.username;
	}

	/**
	 * Accessor method for the user's hashed password.
	 * @return A String representing the user's hashed password.
	 */
	public String getPass() {
		return this.password;
	}
	
	/**
	 * Accessor method for the user's log.
	 * @return The user's log..
	 */
	public Log getLog(){
		return this.log;
	}
	
	/**
	 * Accessor method for the user's watchlist.
	 * @return The user's watchlist.
	 */
	public WatchList getWatchList(){
		return this.watchList;
	}
	
	/**
	 * Generic toString method.
	 * @return A String representation of the user object.
	 */
	public String toString(){
		String str = "\n";
		str += "Username: " + this.username + "\n";
		return str;
	}
}
