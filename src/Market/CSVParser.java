package Market;

import java.io.*;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class is the parser that reads the equities.csv file and
 *          everytime a field is parsed, it is sent to EquityBin in order for it
 *          to be sorted there into indices and/or stocks. This class replaced
 *          the state machine for parsing the equities.csv file.
 *
 */
public final class CSVParser {

	private final static String name = "./equities.csv";

	private CSVParser() {}

	public static boolean read() {
		ArrayList<String> fields;
		try (FileInputStream fi = new FileInputStream(name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = new BufferedReader(in);
			for (String row; (row = br.readLine()) != null;) {
				fields = new ArrayList<String>();
				for (String field : row.split("\",\""))
					fields.add(field.replace("\"", "").replace(" ", ""));
				Market.addEquity(fields);
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(CSVParser.read());
	}
}
