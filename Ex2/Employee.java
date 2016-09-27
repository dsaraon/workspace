package Ex2;
/** Employee implements Payable, that is, we have to give
 *  an implementation of the method getPaymentAmount. 
 */

public abstract class Employee implements Payable {
	
	private String firstName;
	private String lastName;
	private String nI;
	private int dayOfPayment;
	
	public Employee(String firstName, String lastName, 
			String nI, int dayOfPayment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nI = nI;
		this.dayOfPayment = dayOfPayment;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getnI() {
		return nI;
	}

	public void setnI(String nI) {
		this.nI = nI;
	}

    public abstract double paymentAmount();
    	
	public int getDayOfPayment() {
		return dayOfPayment;
	}
	
	public int dueDate() {
		return getDayOfPayment();
	}

	public void setDayOfPayment(int dayOfPayment) {
		this.dayOfPayment = dayOfPayment;
	}
	
	/**
	 * Abstract method implemented in subclasses
	 * 
	 * @param rate The rate with with the salaries are to be increased  
	 */
	public abstract void increaseSalary(double rate);
	
        @Override
	public String toString() {
                return String.format(" Name: %s, First Name: %s, NI: %s ", 
				     getLastName(),
				     getFirstName(),
				     getnI());
	}
}