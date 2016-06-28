package CSV;
import java.io.*;
import java.net.URL;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class CSVParser {

	private String name = "./equities.csv";
	
	public CSVParser(){
		String [] fields;
		try(FileInputStream fi = new FileInputStream(this.name)) {
			InputStreamReader in = new InputStreamReader(fi);
			BufferedReader br = 
					new BufferedReader(in);
		    for(String row; (row = br.readLine()) != null; ) {
		        fields = row.split(",");
		        for ( int i = 0; i < fields.length; i ++ )
		        	System.out.println("Field " + (i + 1) + ": " + fields[i]);
		    }
		} catch (IOException e) {
			System.out.println("File not found!!");
			e.printStackTrace();
			return ;
		}
	}
	
	
	public static void main(String[] args){
		CSVParser test = new CSVParser();
	}
}
