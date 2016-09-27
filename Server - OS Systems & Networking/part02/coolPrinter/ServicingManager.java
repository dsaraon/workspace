package coolPrinter;

public class ServicingManager {
	public static void main(String[] args) {
		// start the Cool Printer
		CoolPrinter cp = new CoolPrinter();
		cp.start();
		
		// let it print for a while
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// now interrupt the Cool Printer for Service
		cp.interrupt();
	}

}
