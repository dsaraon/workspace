package reworkedTravelAgent02;

public class ReservingTravelAgent implements Runnable {
	private int ID;
	private Flight f;
	
	public ReservingTravelAgent(int id, Flight f) {
		super();
		ID = id;
		this.f = f;
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("\n Travel Agent " +ID+" wants to reserve");
		f.ReserveSeat();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(ID + " is being interrpted!");
		}
		
	}
	
}
