/**
 * 
 */
package Core;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public abstract class State {
	
	private Context context;
	
	public State(Context context){
		this.context = context;
	}
	
	protected Context getContext(){
		return this.context;
	}
	
	abstract void displayOptions();
	abstract void execute();
	abstract int transition();
}
