package CSV;
import java.io.*;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 * This class is the parser that reads the equities.csv file and everytime
 * a field is parsed, it is sent to EquityBin in order for it to be sorted there
 * into indices and/or stocks. This class replaced the state machine for parsing
 * the equities.csv file.
 *
 */
public class CSVParser {

	private String name = "./equities.csv";
	private EquityBin bin;

	public CSVParser(){}
	
	public boolean init(EquityBin bin){
		this.bin = bin;
		return this.startReading();
	}
	
	private boolean startReading(){
		ArrayList<String> fields;
		try(FileInputStream fi = new FileInputStream(this.name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = 
					new BufferedReader(in);
		    for(String row; (row = br.readLine()) != null; ) {
		    	fields = new ArrayList<String>();
		        for ( String field : row.split("\",\"") )
		        	fields.add(field.replace("\"", "").replace(" ", ""));
		       this.bin.addEquity(fields);
		    }
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args){
	}
}
