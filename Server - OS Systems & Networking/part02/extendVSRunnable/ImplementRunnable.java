package extendVSRunnable;
/*
 * Notice that,   you can not invoke the method setName() as there is no thread
 */
public class ImplementRunnable  implements Runnable {

	public void run() {
		System.out.println("This thread implemetns Runnable");
		// the next line wont work - there is no Thread object yet
		//setName("ImplementRunnable");
	}
}
