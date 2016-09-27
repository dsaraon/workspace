package reworkedTravelAgent02;

public class TestIt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Flight f = new Flight(2);
		System.out.println(f.toString());
		ReservingTravelAgent rta = new ReservingTravelAgent(2,f);
		Thread t = new Thread(rta);
		t.start();
		
		
		CancellingTravelAgent cta= new CancellingTravelAgent(4,f);
		Thread tt = new Thread(cta);
		
		tt.start();
		

		

		

	}

}
