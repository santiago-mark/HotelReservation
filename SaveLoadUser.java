/**
 * Saves and load users from a file
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoadUser 
{
	private UserAccounts userAccounts;
	
	File data = new File("users.ser"); 
	
	/**
	 * Saves all users into a file
	 * @param list the list of users
	 * @throws FileNotFoundException file to be saved in was not found
	 */
	public void save(UserAccounts accounts) throws FileNotFoundException
	{
		userAccounts = accounts;
		
		FileOutputStream hotelData = new FileOutputStream(data);
		ObjectOutputStream users;
		
		try {
			users = new ObjectOutputStream(hotelData);
			users.writeObject(userAccounts); 
			users.close();
			hotelData.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the user accounts
	 * @return list of prior user accounts
	 * @throws Exception can't find file
	 */
	public UserAccounts load() throws Exception
	{
		FileInputStream hotelData = new FileInputStream(data);
		try	{
			ObjectInputStream input = new ObjectInputStream(hotelData);
			userAccounts = (UserAccounts) input.readObject();
			input.close();
			hotelData.close();
			
		} catch (IOException e){
			e.printStackTrace();
		}
		return userAccounts;
		
	}
}
