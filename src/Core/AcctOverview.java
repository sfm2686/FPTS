/**
 * 
 */
package Core;

import DataInterface.DBInterface;
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
		System.out.println("\tMake a new Portfolio (enter: 1)");
		System.out.println("\tView portfolios (enter: 2)");
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
		while ( !isValid(0, 2, in) ){
			System.out.println("Invalid input. Please try again");
			this.displayOptions();
			in = getSc().nextInt();
		}
		
		//Displays ports, sets ports in context by port ID taken from user.
		//if input is valid next state is table[2][0] which is S3
		if ( in == 2 ){
			this.listPorts();
			in = getSc().nextInt();
			while  (isValid(1, getContext().getUserPorts().size() - 1, in)){
				System.out.println("Invalid ID, please try again");
				this.listPorts();
				in = getSc().nextInt();
			}
			getContext().setPort(in - 1);
			setNext(in - 1);
		}
		else if ( in == 1 ){
			boolean fail;
			String name = "";
			do {
				fail = false;
				System.out.print("Please enter a Portfolio name: ");
				name = getSc().next();
				for ( Portfolio port : getContext().getUserPorts() )
					if ( port.getName().equalsIgnoreCase(name) ){
						fail = true;
						System.out.println("Invalid Portfolio name, please try again");
					}
			}
			while (fail);
			getContext().getUser().addPort(new Portfolio(name));
			setNext(in - 1);
			if(DBInterface.saveUserData(getContext().getUser())){
				System.out.println("Portfolio saved successfully.");
			}
		}
		else //Quiting..
			super.setNext(getContext().getTable()[this.id].length - 1);
	}
	
	//Helper method for listing portfolios
	private void listPorts(){
		int i = 1;
		for ( Portfolio port : getContext().getUserPorts() )
			System.out.println(i++ + ": " + port.getName());
		System.out.print("Select a portfolio to view by their number (integer): ");
	}

	@Override
	int getID() {
		return this.id;
	}

}
