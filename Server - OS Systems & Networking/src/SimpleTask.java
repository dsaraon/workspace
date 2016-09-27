

public class SimpleTask implements ISimpleTask{

	private int i;
	
	public SimpleTask(int i){
		this.i = i;
	}
	
	public int getI() {
		return i;
	}
	
	@Override
	public void run() {
		System.out.println(this.getI());		
	}
	

}
