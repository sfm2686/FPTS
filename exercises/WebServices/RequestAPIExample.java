package WebServices

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestAPIExample {
	public static void main(String[] args) throws IOException {
		String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22AAPL%22)&env=store://datatables.org/alltableswithkeys";

	// Create a URL and open a connecti
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
		response.append(inputLine);
	}
	// MAKE SURE TO CLOSE YOUR CONNECTION
	in.close();
	
	// response is the contents of the XML
	System.out.println(response.toString());
	}
}
