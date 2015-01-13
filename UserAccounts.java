import java.io.Serializable;
import java.util.ArrayList;

public class UserAccounts implements Serializable
{
	private ArrayList<User> userAccounts = new ArrayList<User>();

	/**
	 * Adds account to list
	 * @param user account to be added
	 */
	public void addAccount(User user)
	{
		userAccounts.add(user);
	}
	
	/**
	 * Gets list of all accounts
	 * @return account list
	 */
	public ArrayList<User> getAccounts()
	{
		return userAccounts;
	}
	
	/**
	 * Checks if the ID is not taken
	 * @param user
	 * @return
	 */
	public boolean possibleIDNum(User user) //need to add this to a different class?
	{
		boolean possible = true;
		String desiredID = user.getID();
		
		for (int i = 0; i < userAccounts.size(); i++)
		{
			User temp = userAccounts.get(i);
			
			if (temp.getID().equals(desiredID) || user.validIDNum(desiredID) == false)
			{
				possible = false; 
				break;
			}
		}
		return possible;
	}
	
	/**
	 * Sets list of user
	 * @param newUsers list of users
	 */
	public void setList(ArrayList<User> newUsers)
	{
		userAccounts = newUsers;
	}
}