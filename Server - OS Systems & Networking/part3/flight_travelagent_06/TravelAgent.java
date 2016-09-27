package flight_travelagent_06;

import java.util.concurrent.locks.Lock;

// Each travel agent runs as a thread booking two seats
public class TravelAgent extends Thread{
	// add a new field to allow using locks
	Lock mylock;
	
	// create a constructor which uses the field
	public TravelAgent(Lock mylock) {
		super();
		this.mylock = mylock;
	}

	public void run() {
		//try and finallly
		try{
			//lock the thread
			mylock.lock();
			//run the booking
			booking();
		}
		//don't forget to unlock
		finally{
		mylock.unlock();
		}
	}

	private void booking() {
		//HERE: start of the run() method
		System.out.println("TravelAgent started booking. Number of booked: " + Flight.BookedSeat);
		// travel agent check if there is seat avaiable 		
		if(Flight.BookedSeat <= Flight.TotalNumberofSeats -2){
			//HERE: print when evaluation finishes
			System.out.println("Evaluation of if finished");
			//take the details of the customer- suppose takes 10 msec
			//HERE: travel agent booking
			
			try {
				sleep(100);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
			//book the seat 
			Flight.BookedSeat += 2 ;
			//HERE: print when an update is carried out
			System.out.println("Number of booked seats" + Flight.BookedSeat);
			
		}
		else
			; //do nothing
	}
	



}
