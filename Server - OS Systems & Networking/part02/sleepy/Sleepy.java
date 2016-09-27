package sleepy;

public class Sleepy implements Runnable {

	public void run() {
		// this is the current timem
		long starttime = System.currentTimeMillis();
		//Sleepy sleeps for 5 seconds
		System.out.println("Sleepy: I am goint to sleep for 5 seconds.");
		//System.out.println("Current Time: " + starttime);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			//Print this if interrupted while sleeping
			long now = System.currentTimeMillis()- starttime;
			System.out.println("Sleepy been sleeping for: " + now);
			System.out.println("Sleepy: Can't believe you woke me up- ANGRY");		
			return;
		}
		System.out.println("I am not interrupted and now waking up");
	}

}
