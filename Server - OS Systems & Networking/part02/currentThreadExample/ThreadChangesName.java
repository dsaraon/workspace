package currentThreadExample;

import java.util.Random;

// this thread changes its name five times
public class ThreadChangesName extends Thread {
	//Random to generate a value between 0 and 5
	private static Random RandomGenerator = new Random();
	public void run(){
		for(int i= 0; i<5; i++){
			// change the name of the thread
			System.out.println("Now setting a new name");
			setName("Current Thread will be called: " +i);
			//sleep for a random interval of time (in milisec)
			try {
				Thread.sleep(RandomGenerator.nextInt(5));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// get the name of the thread
			System.out.println(Thread.currentThread().getName());
		}
	}
}
