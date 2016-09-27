package flight_travelagent_02;

public class Flight {
	//Number of seat which are booked at any point of time
	static int BookedSeat = 0;
	//total number of seats- let us say 10
	static final int TotalNumberofSeats = 10;
	static final int NoTravelAgents = 10;

	public static void main(String[] args) {
		//Ten travel agents are accessing the system to book
		TravelAgent [] ta = new TravelAgent[NoTravelAgents];
		for(int i =0; i<NoTravelAgents; i++){
			//this is our travel agent which is implemented as a thread
			ta[i] = new TravelAgent();
			//Travel agent starts to work
			ta[i].start();
		}
//		let us wait for all travel agents to do their booking (if possible)
		try {
			for (int i = 0; i < NoTravelAgents; i++) {
				ta[i].join();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		// print the reault
		System.out.println("--------------");
		System.out.println("Total Number Seats on the plane "+ TotalNumberofSeats);
		System.out.println("Number of seats booked "+ BookedSeat);


	}

}
