package DataInterface;

import java.io.*;

import Finance.Portfolio;
import Finance.User;

/**
 * @authors Sultan Mira, Hunter Caskey
 * 
 *          This class has only static methods that return either boolean to
 *          indicate success or failure of an operation for confirmation
 *          messages, or a User object to be checked against the user input in
 *          the Core subsystem. This class will not be able to save if the
 *          directory with the same name as in the constant field did not exist.
 *
 */
public class DBInterface {

	private static final String directory = "userData";
	private static final String extension = ".ser";

	public static boolean saveUserData(User user) {
		File directory = new File(DBInterface.directory);
		String fileName = user.getUserName() + DBInterface.extension;
		File file = new File(directory, fileName);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(user);
			oos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static User getUserData(String userName) {
		User user = null;
		File directory = new File(DBInterface.directory);
		String fileName = userName + DBInterface.extension;
		File file = new File(directory, fileName);
		if (file.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				user = (User) ois.readObject();
				ois.close();
			} catch (Exception e) {
			}
		}
		
		return user;
	}

	public static boolean deleteUserData(String userName) {
		File directory = new File(DBInterface.directory);
		String fileName = userName + DBInterface.extension;
		File file = new File(directory, fileName);
		return file.delete();
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// if(args.length == 2){
		// if(args[0].equals("-delete")){
		// boolean status = deleteUserData(args[1]);
		// if(status){
		// System.out.println("Successful deletion of User: " + args[1]);
		// return;
		// }
		// System.out.println("Unsuccessful deletion of User: " + args[1]);
		// return;
		// }
		// }
		User testUser = new User("user123", "password");
		saveUserData(testUser);
		User retrieval = getUserData("user123");
		System.out.println(retrieval);
		// deleteUserData(retrieval.getUserName());
	}

}
