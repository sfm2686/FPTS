package WebServices;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class RequestAPIExample {
	
	private Map<String, Double> dict = new HashMap<String, Double>();
	private double dowDivisor = 0.14602128057775;
	private double currentPrice = 0;
	
	private static void parser(){
		;
	}
	
	
	/**
	 * Getter method to retrieve the current price of the DJIA
	 * @return The current price of the DJIA.
	 */
	public double getPrice(){
		return 0.0;
	}
	
	/**
	 * Formula for calculating the DJIA is: DWIA = ∑(component stocks) / (DJIA divisor)
	 */
	private void setPrice(){
		double sum = this.getIndexSum();
		this.currentPrice = (sum / this.dowDivisor);
		return;
		
	}
	
	/**
	 * Helper function to return the sum of prices for each component stock in the DJIA.
	 * @return sum The sum of all prices for each component stock in the DJIA
	 */
	private double getIndexSum(){
		double sum = 0;
		for(double price : dict.values()){
			sum += price;
		}
		return sum;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		String url = "http://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22AAPL%22)&env=store://datatables.org/alltableswithkeys";

		// Create a URL and open a connection
		URL YahooURL = new URL(url);
		HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

		// Set the HTTP Request type method to GET (Default: GET)
		con.setRequestMethod("GET");
		con.setConnectTimeout(10000);
		con.setReadTimeout(10000);

		// Created a BufferedReader to read the contents of the request.
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) { 
			response.append(inputLine + "\n");
		}
		// MAKE SURE TO CLOSE YOUR CONNECTION
		in.close();

		// response is the contents of the XML
		System.out.println(response.toString());
	}
}
