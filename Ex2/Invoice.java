package Ex2;
public class Invoice implements Payable {

    /* Invoice implements Payable, that is, we have to give an
     * implementation of the method getPaymentAmount. That's done
     * returning the number of items times the cost per item.
     */

	private int pricePerItem;
	private int quantity;
	private String description;
	private String idNumber;
	private int dueDate;
	
	public Invoice(int pricePerItem, int quantity, String description,
			String idNumber, int dueDate) {
	
		this.pricePerItem = pricePerItem;
		this.quantity = quantity;
		this.description = description;
		this.idNumber = idNumber;
		this.dueDate = dueDate;
	}

	public int getDueDate() {
		return dueDate;
	}
	
	public int dueDate() {
		return getDueDate();
	}

	public void setDueDate(int dueDate) {
		this.dueDate = dueDate;
	}

	public int getPricePerItem() {
		return pricePerItem;
	}

	public void setPricePerItem(int pricePerItem) {
		this.pricePerItem = pricePerItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public String toString() {
		return "Invoice for " + description + 
		    " (id=" + idNumber
		    + ") " + quantity + 
		    " item(s) at a price of " + pricePerItem +  " per piece.\n" +
		    "due at " + this.getDueDate();
	}

	public double paymentAmount(){
		return (this.getPricePerItem() * this.getQuantity());
	}
	
}