/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class SpecifyEquity extends State {
	
	private int id = 14;
	protected double price;
	protected int shares;

	/**
	 * @param context
	 */
	public SpecifyEquity(Context context) {
		super(context);
	}

	@Override
	void displayOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	int getID() {
		return this.id;
	}

}
