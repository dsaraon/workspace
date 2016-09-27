package reworkedTravelAgent02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Flight {
	private final int TotalNumberOfSeats =10;
	private int BookedTillNow;
	
	//create ONE single lock to protect the resources
	private Lock lok = new ReentrantLock();
	
	
	public Flight(int bookedTillNow) {
		super();
		BookedTillNow = bookedTillNow;
	}

	// this reserves a pair of seats
	public void ReserveSeat(){
		//obtaining lock 
		lok.lock();
		try {
			if (BookedTillNow <= TotalNumberOfSeats - 2) {
				this.BookedTillNow += 2;
				System.out.println("A pair of seats are booked");
			} else
				System.out.println("No seat available to book");
			System.out.println(this.toString());
		} finally  {
			//Release lock
			lok.unlock();
		}		
	}
	
	public void CancelReservation(){
		lok.lock();
		try {
			if (BookedTillNow >= 2) {
				this.BookedTillNow -= 2;
				System.out
						.println("A pair of seats which were booked are now canceled");
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
