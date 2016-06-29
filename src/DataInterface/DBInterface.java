/**
 * 
 */
package DataInterface;

import java.io.File;
import java.io.IOException;

/**
 * @authors Sultan Mira, Hunter Caskey
 *
 */
public class DBInterface {
	
	private static final String dir = "userData";
	
	public boolean savePortfolio(){return true;}
	public boolean saveUser(){return true;}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("userData");
		file.mkdirs();
		File file2 = new File(file, "example.txt");
		file2.createNewFile(); 
		System.out.println("Complete");
	}

}
