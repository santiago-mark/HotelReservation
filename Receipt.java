/**
 * Basic interface for a receipt
 * @author Mark Santiago
 * CS 151 Fall 2014
 */
public interface Receipt 
{
	//TODO CHANGE METHODS TO TAKE OUT SYSTEM.OUT.PRINTLN
	
	/**
	 * Calculates the total for the receipt
	 * @param user the user reserving
	 * @return total amount due
	 */
	public double calculateTotal(User user);
	
	/**
	 * Prints the receipt information
	 * @param user the user reserving
	 * @return receipt information
	 */
	public String printReceipt(User user);
}
