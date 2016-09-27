package reworkedTravelAgent03;

public class TestIt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create a 10 seats flight with 6 seats already booked
		Flight f = new Flight(6);
		System.out.println(f.toString());
		
		
		// create three reserving travel agents
		ReservingTravelAgent rta1 = new ReservingTravelAgent(1,f);
		ReservingTravelAgent rta2 = new ReservingTravelAgent(2,f);
		ReservingTravelAgent rta3 = new ReservingTravelAgent(3,f);
		
		// create a 4th travel agent that cancels a pair of the reserved seats
		CancellingTravelAgent cta= new CancellingTravelAgent(4,f);
		
		// create your four threads
		Thread t1 = new Thread(rta1);
		Thread t2 = new Thread(rta2);
		Thread t3 = new Thread(rta3);
		Thread t4 = new Thread(cta);
		//and start them
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
