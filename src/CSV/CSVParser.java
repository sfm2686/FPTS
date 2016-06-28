package CSV;
import java.io.*;
import java.util.ArrayList;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CSVParser {

	private String name = "./equities.csv";
	private EquityBin bin;
	
	public CSVParser(){
		this.bin = EquityBin.getEquityBin();
	}
	
	public boolean startReading(){
		ArrayList<String> fields;
		try(FileInputStream fi = new FileInputStream(this.name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = 
					new BufferedReader(in);
		    for(String row; (row = br.readLine()) != null; ) {
		    	fields = new ArrayList<String>();
		        for ( String field : row.split("\",\"") )
		        	fields.add(field.replace("\"", ""));
		       bin.addEquity(fields);
		    }
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	
	public static void main(String[] args){
		CSVParser test = new CSVParser();
	}
}
