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
	
	public CSVParser(EquityBin bin){
		this.bin = bin;
	}
	
	public boolean startReading(){
		ArrayList<String> fields = new ArrayList<String>();
		try(FileInputStream fi = new FileInputStream(this.name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = 
					new BufferedReader(in);
		    for(String row; (row = br.readLine()) != null; ) {
		        fields = row.split("\",\"");
		    }
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	
	public static void main(String[] args){
		CSVParser test = new CSVParser(new EquityBin());
	}
}
