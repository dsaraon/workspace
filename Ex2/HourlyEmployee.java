package Ex2;
public class HourlyEmployee extends Employee {

    /* This class inherits from the abstract superclass Employee.  We
     * specialise the toString method by indicating the hourly salary.
     * The getPaymentAmount just returns the hourly salary times the
     * numbers of hours worked.
     */
    private double hourlySalary;
    private int workedHoursLastMonth;
    
    public HourlyEmployee(String first, String last, 
			    String nIN, int dayOfPayment, double hourlySalary) {
	super(first, last, nIN, dayOfPayment);
	this.hourlySalary    = hourlySalary;
	workedHoursLastMonth = 0;
    }

    public void setWorkedHoursLastMonth(int hours){
	workedHoursLastMonth = hours;
    }

    public int getWorkedHoursLastMonth(){
	return workedHoursLastMonth;
    }

    public double getHourlySalary(){
	return hourlySalary;
    }
    
    /**
	 * This method overrides the abstract method in the superclass and increases the Hourlysalary  
	 */
    @Override
    public void increaseSalary(double rate){
		this.hourlySalary += getHourlySalary() * rate;
	}
    
    @Override
    public double paymentAmount() {
     	return getHourlySalary() * getWorkedHoursLastMonth();
    }

    
    @Override
    public String toString(){
	   return String.format("%s,\n hourly salary: %d, "
			+ "worked hours: %d, total salary: %d",
			     super.toString(), 
			     getHourlySalary(),
			     getWorkedHoursLastMonth(),
			     paymentAmount());
    }
}