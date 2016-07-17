package Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import Finance.Portfolio;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stock GOF Command Pattern
 * Stock GOF Composite Pattern
 * 
 * This abstract class defines the stubs needed by all concrete commands.
 * Behavior specifying the relationships between composite commands (macros) and
 * leaf command is specified, but implementation is left for subclasses.
 * 
 * @authors Sultan Mira, Hunter Caskey
 */
@SuppressWarnings("serial")
public abstract class Command implements Serializable {

	/****** Class Attributes ******/
	private Portfolio receiver;
	private Date transactionDate;

	/****** Class Methods ******/

	/**
	 * Constructor for a Command object.
	 * 
	 * @param receiver The receiver object that the command will perform operation on.
	 */
	public Command(Portfolio receiver) {
		this.receiver = receiver;
		this.transactionDate = Calendar.getInstance(TimeZone.getTimeZone("EST")).getTime(); // Set to local time
	}

	/**
	 * Accessor for retrieving the receiver of this command.
	 * 
	 * @return The receiver of this command.
	 */
	public Portfolio getReceiver() {
		return this.receiver;
	}

	/**
	 * Accessor for transaction date of this command.
	 * 
	 * @return The transaction date of this command.
	 */
	public Date getTransactionDate() {
		return this.transactionDate;
	}

	
	public abstract boolean execute(); // All commands shall have an execute method.
	
	// Define behavior for modifying composite relationships.
	public abstract void addChild(Command node);
	public abstract void removeChild(Command node);
	public abstract ArrayList<Command> getChildren();
}
