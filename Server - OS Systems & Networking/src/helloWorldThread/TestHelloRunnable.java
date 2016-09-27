package helloWorldThread;

public class TestHelloRunnable {
	public static void main(String[] args) {
		HelloRunnable ht = new HelloRunnable();
		Thread t = new Thread(ht);
		t.start();
		}

}
