/**
 * Prints out a simple receipt
 * @author Mark Santiago
 * CS 151 Fall 2014
 */

import java.util.ArrayList;

public class Simple implements Receipt //TODO CHANGE SYSTEM.OUT.PRINTLN
{
	private ArrayList<Reservation> reservationsOfTransaction; //all reservations made during this transaction
	
	public double calculateTotal(User user)
	{
		double total = 0;
		for (int i = 0; i < reservationsOfTransaction.size(); i++)
		{
			Reservation current = reservationsOfTransaction.get(i);
			total = total + current.getCost();
		}
		
		return total;
	}

	public String printReceipt(User user) 
	{
		String retVal = new String();
		retVal += "User ID: " + user.getID() + "\n";
		retVal += "Name: " + user.getName() + "\n";
		
		for (int i = 0; i < reservationsOfTransaction.size(); i++)
		{
			Reservation temp = reservationsOfTransaction.get(i);
			String room = temp.getRoom().getName();
			String start = temp.getStart().toString();
			String end = temp.getEnd().toString();
			retVal += room + " Check-In:" + start + "   Check Out:" + end + " " + "   Cost: " + String.valueOf(temp.getCost()) + "\n";
		}
		retVal += "\n";
		retVal += "Total Amount due: " + this.calculateTotal(user) + "\n";
		return retVal;
	}
	
	/**
	 * Gets the reservations made during this transaction
	 * @return list of reservations
	 */
	public ArrayList<Reservation> getReservationsOfTransaction()
	{
		return reservationsOfTransaction;
	}
	
	/**
	 * Sets the list of reservations made during a transaction
	 * @param newTransactions the new list of reservations
	 */
	public void setReservationsOfTransaction(ArrayList<Reservation> newTransactions)
	{
		reservationsOfTransaction = newTransactions;
	}
}
