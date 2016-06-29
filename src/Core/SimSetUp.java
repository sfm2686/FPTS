/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SimSetUp extends State {
	
	private int id = 5;

	/**
	 * @param context
	 */
	public SimSetUp(Context context) {
		super(context);	}

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
		// TODO Auto-generated method stub
		
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
		return this.id;
	}

}
