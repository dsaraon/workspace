package flight_travelagent_04;
// Each travel agent runs as a thread booking two seats
public class TravelAgent extends Thread{
	static Object o = new Object();
	
	public void run() {
		booking();
		
	}

	void booking() {
		// would it work if I use non static as the next line?
		//synchronized (new Object()) {
		synchronized (o) {	
				// travel agent check if there is seat avaiable 
				if(Flight.BookedSeat <= Flight.TotalNumberofSeats -2){
					//take the details of the customer- suppose takes 10 msec
					try {
						sleep(100);
					} catch (InterruptedException e) {
						System.out.println("There is an error here");
					}
					//book the seat 
					Flight.BookedSeat += 2 ;
				}
				else
					; //do nothing
			}		
	}
	



}
