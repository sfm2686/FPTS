package DataInterface;

import java.io.*;
import Finance.User;

/**
 * DBInterface has the responsibility of saving user data to the *.ser files, reading user data from
 * these files, as well as deleting whole users from the system's persisted storage. 
 *          
 * @authors Sultan Mira, Hunter Caskey
 */
public class DBInterface {

	/****** Class Attributes ******/
	private static final String directory = "userData";
	private static final String extension = ".ser";

	/****** Class Methods ******/

	/**
	 * saveUserData saves a user object to the database. As a design choice the user, and every object 
	 * that a user can 'own' implements the java.io.Serializable interface for clean saves.
	 * If the User is already registered within the database then any save will simply overwrite the
	 * previous file, effectively just saving the changes.
	 * 
	 * @param user The User object to be saved to the database.
	 * @return A boolean value indicating success of the save operation.
	 */
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

	/**
	 * getUserData retrieves a User object from the database, based on the parameter.
	 * If the user is not registered within the database, then null is returned.
	 * 
	 * @param userName A String representing a User's username attribute (the unique identifier for User objects).
	 * @return A User object if the username is registered with the system, null otherwise.
	 */
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

	/**
	 * deleteUserData simply removes the a User based on the specified username from the system completely.
	 * 
	 * @param userName A String representing a User's username attribute (the unique identifier for User objects).
	 * @return A boolean indicating the success of the deletion.
	 */
	public static boolean deleteUserData(String userName) {
		File directory = new File(DBInterface.directory);
		String fileName = userName + DBInterface.extension;
		File file = new File(directory, fileName);
		return file.delete();
	}

	/**
	 * Unit Tests for DBInterface
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String username = "user123";
		User testUser = new User(username, "password");
		System.out.println("Status of writing user to database: " + saveUserData(testUser));
		User retrieval = getUserData(username);
		try{
			System.out.println("User found within the database: \n" + retrieval.toString());
		}
		catch(NullPointerException e){
			System.out.println("Error: User not found.");
		}
		finally{
			System.out.println("Status of deleting user from database: " + deleteUserData(username));
		}
	}

}
