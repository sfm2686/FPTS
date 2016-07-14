/**
 * 
 */
package Market;

import java.util.Scanner;

import Finance.Stock;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class StockUpdate extends Thread {

	private int timeInterval;
	
	public StockUpdate(int timeInterval){
		this.timeInterval = timeInterval;
	}
	
	@Override
	public void run(){
		while ( true ){
			try {
				sleep(this.timeInterval * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Stock s : Market.getMarketInstance().getObStocks()){
				Market.getMarketInstance().setPrice(s.getName(), YahooAPI.getPrice(s.getName()));
			}
			Market.getMarketInstance().doneUpdating();
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("please enter a time interval: ");
		int time = sc.nextInt();
		StockUpdate test = new StockUpdate(time);
		test.start();
		while(true){
			System.out.print("TYPE WHILE THE OTHER THEAD IS RUNNING: ");
			sc.nextLine();
		}

	}

}
