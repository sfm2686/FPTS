/**
 * 
 */
package Core;

import Finance.Portfolio;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class AcctOverview extends State {
	
	private int id = 2;

	/**
	 * @param context
	 */
	public AcctOverview(Context context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see Core.State#displayOptions()
	 */
	@Override
	void displayOptions() {
		System.out.println("Options:");
		System.out.println("\tView portfolios (enter: 1)");
		System.out.println("\tQuit (enter: 0)");
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		System.out.println("\n------Account Overview-----\n");
		this.displayOptions();
		int in;
		System.out.print("Taking input: ");
		in = getSc().nextInt();
		while ( in != 1 && in != 0){
			System.out.println("Invalid input. Please try again");
			this.displayOptions();
			in = getSc().nextInt();
		}
		
		//Displays ports, sets ports in context by port ID taken from user.
		//if input is valid next state is table[2][0] which is S3
		if ( in == 1 ){
			this.listPorts();
			in = super.getSc().nextInt() - 1;
			while  ( in <= getContext().getUserPorts().size() ){
				System.out.println("Invalid ID, please try again");
				this.listPorts();
				in = getSc().nextInt() - 1;
			}
			super.getContext().setPort(in);
			super.setNext(0);
		}
		else
			super.setNext(getContext().getTable()[this.id].length);
	}
	
	//Helper method for listing portfolios
	private void listPorts(){
		int i = 1;
		for ( Portfolio port : getContext().getUserPorts() )
			System.out.println(i++ + ": " + port);
		System.out.println("Select a portfolio to view by their number");
		System.out.print("Taking input: ");
	}

	@Override
	int getID() {
		return this.id;
	}

}
