package Ex2;
import java.util.ArrayList;

public class Company implements Payable {

    private String name;
    private String url;
    private double revenue;
    private ArrayList<Employee> employees = new ArrayList<Employee>();


	public Company(String name, String url, double revenue) {
        this.name = name;
        this.url  = url;
        this.revenue = revenue;
    }

    public String getUrl() {
        return this.url;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name + " @: " + url;
    }

    public String toString(boolean verbose) {
        return name + " @: " + url + " revenue: " + revenue;
    }

    public boolean equals(Company company) {
    	return this.getName().equals(company.getName()) &&
    		   this.getUrl().equals(company.getUrl()) &&
    		   Math.abs(this.getRevenue() - company.getRevenue()) < 0.001;
    }
    
    
    public String getName() {
		return name;
	}
    
    
    public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public double fee() {
        if (this.getRevenue() <= 500000) {
            return 2000.0;
        } else if (this.getRevenue() <= 1000000) {
            return 0.005 * this.getRevenue();
        } else {
            return 0.004 * this.getRevenue();
        }
    }

	public double paymentAmount() {
		return (int) fee();
	};

	public int dueDate() {
		return 31;
    };
    
    
    public static double charges(Company[] companies) {
        double sum = 0;
        for (Company company : companies){
            sum += company.fee();
        }
        return sum;
    }
    

    /**	
     * This method iterates through the arrayList of employees and increases the salaries by the corresponding rate.  
     * 
     * @param rate The rate with with the salaries are to be increased
     */
    public void increaseSalaries(double rate){
    	for(Employee e: employees){
    		e.increaseSalary(rate); 
    	}
    }
    

    public static void main(String[] args) {
        
        Company c0 = new Company("a", "http://a", 2000000);
        Company c1 = new Company("b", "http://b", 20);
        Company c2 = new Company("c", "http://c", 700000);

        Company[] companies = {c0, c1, c2};

        System.out.println(c0.equals(c0));
        System.out.println(c0.equals(c1));
        System.out.println(c0);
        System.out.println(c0.toString(true));
        System.out.println(charges(companies));

    }

}