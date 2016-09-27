package interrptedExample;

public class InterruptedExample {
	public static void main(String[] args) {
		// check if the thread is interrupted
		System.out.println("Step 1: value of Thread.interrupted()is: "+ Thread.interrupted());
		//interrupt the current thread
		Thread.currentThread().interrupt();
		//check if the thread is interrupted
		System.out.println("Step 2: value of Thread.interrupted()is: "+ Thread.interrupted());
		// do it again
		//check if the thread is interrupted
		System.out.println("Step 3: value of Thread.interrupted()is: "+ Thread.interrupted());
	}

}
