/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class TransactionOverview extends State {
	
	private int id = 7;

	/**
	 * @param context
	 */
	public TransactionOverview(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("This state is NOT done yet, id = " + this.id);
	}

	/* (non-Javadoc)
	 * @see Core.State#transition()
	 */
	@Override
	int transition() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
