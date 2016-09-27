package restaurant;

import java.util.Random;

public class Table implements Runnable {
	// Number of the table
	private int TableNumber = 0;
	// the amount of time the Table needs to decide
	private int TimeToDecide;
	// Random number generator for the TimeToDecide 
	private static Random RandomTimeGenerator = new Random();
	public Table(int tableNumber) {
		super();
		TableNumber = tableNumber;
		TimeToDecide = RandomTimeGenerator.nextInt(1000);
	}

	public void run() {
		System.out.println("Table " + TableNumber + " is deciding");
		try {
			Thread.sleep(TimeToDecide);
			System.out.println("Table " + TableNumber + " has ordered...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Thread has been interrupted");
			System.exit(0);
		}
	}
}
