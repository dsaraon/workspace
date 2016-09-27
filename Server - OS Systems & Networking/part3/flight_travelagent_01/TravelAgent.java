package flight_travelagent_01;
// Each travel agent runs as a thread booking two seats
public class TravelAgent extends Thread{
	
	public void run() {
		// travel agent check if there is seat avaiable 
		if(Flight.BookedSeat <= Flight.TotalNumberofSeats -2){
			//take the details of the customer- suppose takes 100 msec
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
