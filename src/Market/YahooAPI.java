package Market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class YahooAPI {
	
	/**
	 * Class Attributes
	 * Partial strings for the URL to Yahoo Financial Services in order to be able to insert a given ticker symbol.
	 */
	private static final String url1 = "http://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
	private static final String url2 = "%22)&env=store://datatables.org/alltableswithkeys";

	/**
	 * getPrice is used by the DOW class in order to retrieve a price for a given component stock. 
	 * @param tickerSymbol The identifier of a stock that is a component of the DOW.
	 * @return The ticker symbol's corresponding price.
	 * @throws IOException 
	 */
	protected static double getPrice(String tickerSymbol) {
		String url = url1 + tickerSymbol + url2;
		String xmlData = getXML(url);
		return parseXML(xmlData);
	}
	
	/**
	 * parseXML will return the price of a ticker symbol parsed from the XML file that the Yahoo Financial Service API returns.
	 * @param xml The given XML file in string form.
	 * @return The parsed price from a given XML file.
	 */
	private static double parseXML(String xml){
		if(!xml.contains("<LastTradePriceOnly>") || !xml.contains("</LastTradePriceOnly>")){
			return 0.0;
		}
		xml = xml.replace("</LastTradePriceOnly>", "<LastTradePriceOnly>");
		return Double.parseDouble(xml.split("<LastTradePriceOnly>")[1]);
	}
	
	/**
	 * getXML connects to Yahoo Financial Services API to request a ticker symbol's associated price in the form of an XML file.
	 * @param url The URL in string form that queries Yahoo for a stock's price.
	 * @return An XML file in string form containing a stock's price.
	 * @throws IOException
	 */
	private static String getXML(String url){
		BufferedReader in = null;
		StringBuilder response = null;;
		try{
			URL YahooURL = new URL(url);
			HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();
	
			// Set the HTTP Request type method to GET (Default: GET)
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
	
			// Created a BufferedReader to read the contents of the request.
			in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) { 
				response.append(inputLine + "\n");
			}
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		// response is the contents of the XML
		return(response.toString());
	}
	
	
	/**
	 * Tests in Main Method
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(YahooAPI.parseXML(YahooAPI.getXML(url1 + "AAPL" + url2)));
	}
}