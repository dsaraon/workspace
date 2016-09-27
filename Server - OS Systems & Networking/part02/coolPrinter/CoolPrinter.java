package coolPrinter;

public class CoolPrinter extends Thread {
	int JobNumber= 0;
	public void run(){
		boolean isInRepairMode = false;
		while(!isInRepairMode){
			System.out.println("CoolPrinter starts printing");
			// while is not in RepairMode reads from the spooler and print
			try {
				KeepPrinting();
				//Suppose it takes 5 msec- we can make it random as well
				Thread.sleep(500);	
			} catch (InterruptedException e) {
				// we assume if the printing is interrupted is because of the service
				isInRepairMode = true;
				Reparing();
			}	
		}	
	}
	
	public void KeepPrinting(){
		//dummy code for start printing a job
		System.out.println("Printing job number: " + JobNumber);
		JobNumber++;
	}
	
	public void Reparing(){
		//dummy code for repair- 
		System.out.println("Printer is being serviced- not available");
		System.exit(0);
	}

}
