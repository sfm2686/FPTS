package Market;

import java.io.*;
import java.util.ArrayList;

/**
 * CSVPareser is designed to read equities in from a constant CSV file and then supply
 * the Market class with that information, equity by equity.
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
public final class CSVParser {

	/****** Class Attributes ******/
	private final static String name = "./equities.csv";

	/****** Class Methods ******/

	private CSVParser() {}

	/**
	 * read simply parses the CSV file line by line, and initializes the Market class 
	 * based on that information.
	 * 
	 * @return true if the file was parsed without any error, false otherwise.
	 */
	public static boolean read() {
		ArrayList<String> fields;
		try (FileInputStream fi = new FileInputStream(name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = new BufferedReader(in);
			for (String row; (row = br.readLine()) != null;) {
				fields = new ArrayList<String>();
				for (String field : row.split("\",\""))
					fields.add(field.replace("\"", "").replace(" ", ""));
				Market.addEquity(fields); // Initialize the equity to Market.
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
