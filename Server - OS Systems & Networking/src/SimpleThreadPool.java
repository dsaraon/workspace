

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool implements ISimpleThreadPool{

	private static LinkedBlockingQueue<ISimpleTask> queue = new LinkedBlockingQueue<ISimpleTask>();
	private static HashSet<Thread> threadSet = new HashSet<Thread>();
	
	public SimpleThreadPool(){
		Thread t1 = new Thread(new SimplePoolThread());
		Thread t2 = new Thread(new SimplePoolThread());
		Thread t3 = new Thread(new SimplePoolThread());
		
		threadSet.add(t1);
		threadSet.add(t2);
		threadSet.add(t3);
	}
	
	public static LinkedBlockingQueue<ISimpleTask> getQueue() {
		return queue;
	}

	public static HashSet<Thread> getThreadSet() {
		return threadSet;
	}
	
	@Override
	public void start() {
		
		//starting threads
		for(Thread t: threadSet){
			t.start();
		}
	}

	@Override
	public void stop() {
		for(Thread t: threadSet){
			t.interrupt();;
		}
		System.exit(0);
	}

	@Override
	public void addTask(ISimpleTask task) {
		//add SimpleTask object into queue
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	


}
