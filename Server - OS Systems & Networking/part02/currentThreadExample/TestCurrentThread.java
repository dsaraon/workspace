package currentThreadExample;

import java.util.Random;

public class TestCurrentThread {
	public static void main(String[] args) {
		Random RandomGenerator = new Random();
		System.out.println("Thread at the beginning is: " 
				+Thread.currentThread().getName());
		// spawn a new ThreadChangesName thread
		ThreadChangesName t = new ThreadChangesName();
		t.start();
		for(int i = 0; i<5; i++){;
		//let is sleep a bit randomly
		try {
			Thread.sleep(RandomGenerator.nextInt(5));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// This will be your main thread- will print main 
			System.out.println(Thread.currentThread().getName());
		}
	}
}
