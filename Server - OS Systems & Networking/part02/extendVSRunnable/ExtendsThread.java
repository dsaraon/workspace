package extendVSRunnable;
/*
 * Notice that, if you implement the Runnable,  you can not invoke the method setName() as there is no thread
 */
public class ExtendsThread extends Thread{
	public void run(){
		System.out.println("This extends Thread");
		setName("ExtendsThread");
	}	
}
