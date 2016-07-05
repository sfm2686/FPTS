/**
 * 
 */
package WebServices;

import java.util.*;
import java.io.*;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class DOW {

	private double currentPrice = 0.0;
	private double dowDivisor = 0.14602128057775; // Could not find an API to retrieve this dynamically
	private String[] stocks; 
	private String configPath = "stocks.txt";

	public DOW(){
		this.stocks = new String[30];
		this.initStocks();
	}

	
	/**
	 * initStocks reads a configuration files (stocks.txt) and populates the stocks
	 * array with the ticker symbols of the stocks that make of the DJIA.
	 */
	private void initStocks(){
		File file = new File(this.configPath);
		BufferedReader reader = null;
		try{
			reader =  new BufferedReader(new FileReader(file));
			String text = "";
			int i = 0;
			while((text = reader.readLine()) != null){
				this.stocks[i++] = text; 
			}
		}
		catch(Exception e){
			System.out.println("Parsing Error. System exiting.");
			System.exit(1);
		}
		finally{
			try{
				if (reader != null){
					reader.close();
				}
			}
			catch(IOException e){}
		}
	}

	/**
	 * Getter method to retrieve the current price of the DJIA, 
	 * to ensure the price is current it is reset prior to return.
	 * @return The current price of the DJIA.
	 */
	public double getPrice(){
		this.setPrice();
		return this.currentPrice;
	}

	/**
	 * Formula for calculating the DJIA is: DWIA = ∑(component stocks) / (DJIA divisor)
	 */
	private void setPrice(){
		double sum = this.getIndexSum();
		double price = (sum / this.dowDivisor);
		this.currentPrice = (Math.round(price * 100.0) / 100.0);
	}

	/**
	 * Helper function to return the sum of prices for each component stock in the DJIA.
	 * @return sum The sum of all prices for each component stock in the DJIA
	 */
	private double getIndexSum(){
		double sum = 0.0;
		for(String ticker : this.stocks){
			sum += APIInterface.getPrice(ticker);
		}
		return sum;
	}
	
	/**
	 * Helper method for testing purposes.
	 * @return
	 */
	private String listStockPrices(){
		String str = "\n";
		for(String stock : this.stocks){
			str += "\tTicker: " + stock + ", Price: " + APIInterface.getPrice(stock) + "\n";
		}
		return str;
	}

	public static void main(String args[]) throws InterruptedException{
		DOW dow = new DOW();
		
		Scanner input = new Scanner(System.in);
		int time = -1;
		do{
			System.out.print("How often would you like to update the price of the DJIA? (seconds): ");
			String in = input.next();
			try{
				time = Integer.parseInt(in);
				time = Math.abs(time);
			}
			catch(Exception e){
				System.out.println("Numeric integer values only.");
			}
		}while(time == -1);
		System.out.println("Displaying the value of the DOW and it's component stocks every " + time + " second(s).");
		input.close();
		
		while(true){
			System.out.println("Price of the DOW's component stocks:");
			System.out.println(dow.listStockPrices());
			System.out.println("Price of DOW: " + dow.getPrice());
			System.out.println("\n======================================\n");
			Thread.sleep(1000 * time);
		}
	}
}
