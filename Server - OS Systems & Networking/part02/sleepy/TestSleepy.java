package sleepy;

public class TestSleepy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sleepy s = new Sleepy();
		//create a new thread and start it
		Thread t = new Thread(s);
		t.start();
		
		//Let Sleepy have a 2 second nap
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		//Lets wake him up
		System.out.println("Waking Sleepy from the main thread :-)");
		//interrupt the thread t
		t.interrupt();
		//reaching to the end of main
		System.out.println("End is now terminationg");
	}

}
