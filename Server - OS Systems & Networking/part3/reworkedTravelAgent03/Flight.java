package reworkedTravelAgent03;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Flight{
	private final int TotalNumberOfSeats =10;
	private int BookedTillNow;
	
	//create one lock to protect the resources
	private Lock lok = new ReentrantLock();
	
	// create a condition for evaluating if a pair of seats available
//	Add the new condition to the lock
	private Condition Availablity = lok.newCondition();
	
	
	public Flight(int bookedTillNow) {
		super();
		BookedTillNow = bookedTillNow;
	}

	// this reserves a pair of seats
	public void ReserveSeat() throws InterruptedException{
		lok.lock();
		try {
			// while no pair of seats are available i wait
			while(BookedTillNow > TotalNumberOfSeats - 2){
				System.out.println("No seat available, so I wait");
				Availablity.await();
			}
			//when the seats become available I book them
				this.BookedTillNow += 2;
				System.out.println("A pair of seats are booked");
			System.out.println(this.toString());
		} finally  {
			lok.unlock();
		}		
	}
	
	public void CancelReservation()throws InterruptedException{
		lok.lock();
		try {
			if (BookedTillNow >= 2) {
				this.BookedTillNow -= 2;
				System.out.println("A pair of seats which were booked are now canceled");
				System.out.println("I will notify all other threads");
				Availablity.signalAll();
			} else
				System.out.println("No seat available to book");
			System.out.println(this.toString());
		} finally  {
			lok.unlock();
		}		
	}
	
	public String toString(){
		String s ="\n";
		s+= "Total number of seats: " + this.TotalNumberOfSeats+"\n";
		s+= "Total number of seats: " + this.BookedTillNow+"\n";
		return s;
	}

}
