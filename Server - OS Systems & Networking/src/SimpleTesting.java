

public class SimpleTesting implements ISimpleTask {

	private SimpleTask task; // initialize task object

	public SimpleTesting(SimpleTask task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.run();
	}

	public static void main(String[] args) {
		// Initialize thread pool
		SimpleThreadPool pool = new SimpleThreadPool();
		pool.start();
		// Create 20 tasks
		for (int i = 1; i <= 20; i++) {
			SimpleTask task = new SimpleTask(i);
			pool.addTask(new SimpleTesting(task));
		}
	}
	
}