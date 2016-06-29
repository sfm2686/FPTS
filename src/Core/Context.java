/**
 * 
 */
package Core;

import Finance.*;
import Simulation.SimulationContext;
import java.util.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 * This class is the main driver of the state machine.
 * It delegates between state and changes them depending on the current state/input.
 *
 */
public class Context {

	public static final int QUIT = 13;
	private State[][] table;
	private Portfolio portfolio;
	private State current;
	private User user;
	
	public Context(){
		//STARTS ALL OTHER SUBSYSTEMS HERE..
		//CVS
		//DBINTERFACE
		//..
	}
	
	public void setVars(){
		State S0  = new Login(this);
		State S1  = new Register(this);
		State S2  = new AcctOverview(this);
		State S3  = new PortOverview(this);
		State S4  = new ViewLog(this);
		State S5  = new SimSetUp(this);
		State S6  = new SimView(this);
		State S7  = new TransactionOverview(this);
		State S8  = new DisplayHoldings(this);
		State S9  = new AskSrc(this);
		State S10 = new SpecifyHoldingType(this);
		State S11 = new GiveCashInfo(this);
		State S12 = new GiveEquityInfo(this);
		State S13 = new Logout(this);
		State S14 = new SpecifyEquity(this);
		State S15 = new Add(this);
		State S16 = new GetAmount(this);
		State S17 = new GetSharesPrice(this);
		State S18 = new AskSource(this);
		State S19 = new Subtract(this);
		State S20 = new AskAmountDest(this);
		State S21 = new AskNumPriceDest(this);
		
		State[][] tempTable = { 
				{S1, S2},                       //S0
				{S0, S13},                      //S1
				{S3, S13},                      //S2
				{S2, S4, S5, S7, S13},          //S3
				{S3, S13},                      //S4
				{S3, S6, S13},                  //S5
				{S3, S5, S13},                  //S6
				{S3, S8, S10, S15, S19, S13},   //S7
				{S3, S13},                      //S8
				{S3, S13},                      //S9
				{S11, S12, S13},                //S10
				{S3, S13},                      //S11
				{S14, S13},                     //S12
				{S3},                           //S13
				{S3, S13},                      //S14
				{S16, S17, S13},                //S15
				{S18, S13},                     //S16
				{S9, S13},                      //S17
				{S3, S13},                      //S18
				{S20, S21, S13},                //S19
				{S3, S13},                      //S20
				{S3, S13},                      //S21
				};
		
		this.table = tempTable;
		this.current = table[0][0];
		//this.goToNextState();
	}
	
	public State[][] getTable(){
		return this.table;
	}
	
	
	//For testing
	protected void setCurrent(State cur){
		this.current = cur;
	}
	
	protected ArrayList<Portfolio> getUserPorts(){
		return this.user.getPorts();
	}
	
	public void setPortfolio( Portfolio portfolio ){
		this.portfolio = portfolio;
	}
	
	public State getCurrent(){
		return this.current;
	}
	
	public void goToNextState(){
		while ( this.current.getID() != QUIT ){
			this.current.execute();
			this.current = this.table[current.getID()][current.getNext()];
		}
		this.current.execute();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = new Context();
		context.setVars();
	}

}
