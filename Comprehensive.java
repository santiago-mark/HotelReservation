/**
 * Prints out a comprehensive receipt
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

public class Comprehensive implements Receipt
{
	
	public double calculateTotal(User user)
	{
		double total = 0;
		
		for (int i = 0; i < user.getReservations().size(); i++)
		{
			Reservation current = user.getReservations().get(i);
			total = total + current.getCost();
		}
		
		return total;
	}

	public String printReceipt(User user) 
	{
		String retVal = new String();
		retVal += "User ID: " + user.getID() + "\n";
		retVal += "Name: " + user.getName() + "\n";
		try
		{
		for (int i = 0; i < user.getReservations().size(); i++)
		{
			Reservation temp = user.getReservations().get(i);
			String room = temp.getRoom().getName();
			String start = temp.getStart().toString();
			String end = temp.getEnd().toString();
			retVal += room + " Check-In:" + start + "   Check Out:" + end + " " + "   Cost: " + String.valueOf(temp.getCost()) + "\n";
		}
		retVal += "\n";
		retVal += "Total Amount due: " + this.calculateTotal(user) + "\n";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return retVal;
	}
}


