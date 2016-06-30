package Finance;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class Transaction implements Serializable {

	private Portfolio receiver;
	private Date transactionDate;
	
	public Transaction(Portfolio receiver){
		this.receiver = receiver;
		this.transactionDate = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
	}
	
	public Portfolio getReciever(){
		return this.receiver;
	}
	
	public Date getTransactionDate(){
		return this.transactionDate;
	}
	
	public abstract boolean Execute();
}
