/**
 * 
 */
package Core;

import Finance.*;
import Simulation.SimulationContext;

/**
 * @authors Sultan Mira, Hunter Caskey
 * This class is the main driver of the state machine.
 * It delegates between state and changes them depending on the current state/input.
 *
 */
public class Context {

	private State[][] table;
	private Portfolio portfolio;
	//Private User user;
	
	public Context(){
		//Should set the user
	}
	
	public void setVars(){
		State S0  = new PortfolioOverview(this);
		State S1  = new DisplayAllHoldings(this);
		State S2  = new TransactionSelection(this);
		State S3  = new PromptAmount(this);
		State S4  = new AwaitingType(this);
		State S5  = new EquityInfo(this);
		State S6  = new CashInfo(this);
		State S7  = new AwaitingFundSrcType(this);
		State S8  = new PromptSrcAcct(this);
		State S9  = new Confirm(this);
		State S10 = new DisplayAllHoldingsAskAmount(this);
		State S11 = new PromptAction(this);
		State S12 = new PromptDest(this);
		State S13 = new SpecAcct(this);
		State S14 = new PromptCashDest(this);
		State S15 = new PromptSrcHolding(this);
		State S16 = new PromptSimSettings(this);
		State S17 = new ViewSimResults(this);
		State S18 = new DisplayLog(this);
		
		State[][] tempTable = { 
				{S1, S16, S18},           //0
				{S13, S15, S10, S2, S4},  //1
				{S3},                     //2
				{S3, S9},                 //3
				{S5, S6},                 //4
				{S5, S7},                 //5
				{S6, S7},                 //6
				{S9, S8},                 //7
				{S8, S9},                 //8
				{S0},                     //9
				{S10, S9},                //10
				{S9},                     //11
				{S11, S9},                //12
				{S14},                    //13
				{S9},                     //14
				{S12},                    //15
				{S17, S16},               //16
				{S16, S0},                //17
				{S0}                      //18
				};
		this.table = tempTable;
	}
	
	public void setPortfolio( Portfolio portfolio ){
		this.portfolio = portfolio;
	}
	
	public void goToNextState(State next){
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
