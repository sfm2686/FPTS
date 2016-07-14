package Transaction;

import Finance.*;
import Market.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class acts as a driver for transactions. It takes one type of
 *          portfolio transaction and delegates between 8 of the concrete
 *          commands to have the operation done. Once every command is done with
 *          its operation it calls log in order for it to be logged.
 *
 */
public class Client {

	private User user;

	public Client(User user) {
		this.user = user;
	}
}