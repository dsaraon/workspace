package restaurant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServingCustomers {
	public static void main(String[] args) {
		// Let us make four Tables for the group of customers
		Table table1 = new Table(1);
		Table table2 = new Table(2);		
		Table table3 = new Table(3);
		Table table4 = new Table(4);
		
		//create the pool of waiter (two waiters)
		ExecutorService waiters = Executors.newFixedThreadPool( 2 );
		waiters.execute(table1);
		waiters.execute(table2);
		waiters.execute(table3);
		waiters.execute(table4);		
	}
}
