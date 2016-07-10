package Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import Finance.Portfolio;
import java.io.Serializable;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This abstract class defines the stubs needed by all concrete
 *          commands. All commands are logged once they are done with their
 *          operation.
 *
 */
public abstract class Command implements Serializable {

	private Portfolio receiver;
	private Date transactionDate;

	public Command(Portfolio receiver) {
		this.receiver = receiver;
		this.transactionDate = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime();
	}

	public Portfolio getReciever() {
		return this.receiver;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public abstract boolean execute();
}