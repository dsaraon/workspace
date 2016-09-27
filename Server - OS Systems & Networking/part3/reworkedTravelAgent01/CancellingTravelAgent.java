package reworkedTravelAgent01;

public class CancellingTravelAgent implements Runnable{
	private int ID;
	private Flight f;
	
	public CancellingTravelAgent(int id, Flight f) {
		super();
		ID = id;
		this.f = f;
	}

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Travel Agent " +ID+" wants to Cancel");
		f.CancelReservation();
		// cancellation takes five seconds
		try {
			//Thread.sleep(5000);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(ID + " is being interrpted!");
		}
		
	}

}
