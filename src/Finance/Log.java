package Finance;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class is responsible for logging the transactions that happen to a 
 * portfolio. This class stores an arraylist of type transaction and lists them
 * when needed. This class also makes it possible to support undo transaction
 * functionality.
 *
 */
public class Log implements Serializable {

	private ArrayList<Transaction> log;
	private String portfolioName;
	
	public Log(String portfolioName){
		this.log = new ArrayList<Transaction>();
		this.portfolioName = portfolioName;
	}
	
	public void log (Transaction change){
		this.log.add(change);
	}
	
	public String toString(){
		String str = "Log for Portfolio: " + this.portfolioName + "\n";
		for(Transaction item : this.log){
			str += "\t" + item.toString() + "\n";
		}
		return str;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
