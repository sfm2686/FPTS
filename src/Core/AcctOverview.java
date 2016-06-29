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
		System.out.println("\tQuit (enter: 0 )");
		
	}

	/* (non-Javadoc)
	 * @see Core.State#execute()
	 */
	@Override
	void execute() {
		this.displayOptions();
		int in;
		System.out.print("Taking input: ");
		in = super.getSc().nextInt();
		
		//Displays ports, sets ports in context by port ID taken from user.
		//if input is valid next state is table[2][0] which is S3
		if ( in == 1 ){
			this.listPorts();
			in = super.getSc().nextInt();
			while  ( in <= super.getContext().getUserPorts().size() ){
				System.out.println("Invalid ID, please try again");
				this.listPorts();
				in = super.getSc().nextInt();
			}
			super.getContext().setPort(in);
			super.setNext(0);
		}
		else
			super.setNext(super.getContext().getTable()[this.id].length);
	}
	
	//Helper method for listing portfolios
	private void listPorts(){
		int i = 1;
		for ( Portfolio port : super.getContext().getUserPorts() )
			System.out.println(i++ + ": " + port);
		System.out.println("Select a portfolio to view by their number");
		System.out.print("Taking input: ");
	}

	/* (non-Javadoc)
	 * @see Core.State#transition()
	 */
	@Override
	int transition() {
		return super.getNext();
	}

	/* (non-Javadoc)
	 * @see Core.State#getID()
	 */
	@Override
	int getID() {
		return this.id;
	}

}
