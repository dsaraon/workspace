

public class SimplePoolThread extends Thread implements ISimplePoolThread{
	
	/**
	 *   An infinite loop to retrieve and perform tasks.
	 */
	@Override
	public void run() {
		while(true){
				try{
					//gets the queue head, waits if empty
					SimpleThreadPool.getQueue().take().run();;
				}catch(InterruptedException e){
					System.out.println("Thread has been interrupted");
					System.exit(0);
				}
		}
	
	}
}
