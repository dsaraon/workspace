package Ex1;
import Ex2.Person;


public class DoublyLinkedList {
	
	private boolean empty;
	private Person person;
	private DoublyLinkedList left;
	private DoublyLinkedList right;
	
	/**
	 * Constructor to create an empty DoublyLinkedList
	 */
	public DoublyLinkedList(){
		this.empty = true; 
	}
	
	/**
	 * Constructor to create a new DoublyLinkedList with all field variable values
	 * 
	 * @param value Object of type Person
	 * @param left	reference to left side of the list
	 * @param right	reference to the rest of the list 
	 */
	public DoublyLinkedList(Person value, DoublyLinkedList left, DoublyLinkedList right){
		this.person = value;
		this.left = left;
		this.right = right;
	}
	
	
	/**
	 * Constructor to create a new list item and append it to the beginning of the list
	 * 
	 * @param person Object of type Person
	 * @param right	link to the rest of the list which the new item is being appended to
	 */
	public DoublyLinkedList(Person person, DoublyLinkedList right) {
		this.empty = false;
		this.person = person;
		this.left = new DoublyLinkedList();
		this.right = right;
		this.getRight().setLeft(this);
	}
	
	/**
	 * Method cons - returns new DoublyLinkedList with new item 
	 * item is appended to the start of old list passed as a parameter
	 * 
	 * @param value Object of type Person
	 * @param right reference to old list to which the new item is added 
	 * @return a new DoublyLinkedList with the new item appended to the start and holding value of type Person
	 */
	public static DoublyLinkedList cons(Person value, DoublyLinkedList right){
		DoublyLinkedList list = new DoublyLinkedList();
		list.empty = false;
		list.person = value;
		list.left = new DoublyLinkedList();
		list.right = right;
		list.getRight().setLeft(list);
		return list;
	}
	
	
	 /**
     * Creates a new empty List
     */
    public static DoublyLinkedList empty() {
        return new DoublyLinkedList();
    }

	public boolean getEmpty() {
		return this.empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person value) {
		this.person = value;
	}

	public DoublyLinkedList getLeft() {
		return this.left;
	}

	public void setLeft(DoublyLinkedList left) {
		this.left = left;
	}

	public DoublyLinkedList getRight() {
		return this.right;
	}

	public void setRight(DoublyLinkedList right) {
		this.right = right;
	}

	@Override
	public String toString() {
    	return "[" + toStringAux() + "]";
    }
  
    
    public String toStringAux() {
       	if (getEmpty()) {
       		return "";
       	} else if (getLeft().getEmpty()){
       		return this.getPerson() + "\n" + getRight().toStringAux();
       	} else {
       	 	return this.getPerson() + "\n" + getRight().toStringAux();
       	}
    }
    
    /**
     * Equals method checks if two DoublyLinkedList are the same 
     * 
     * @param l1 DoublyLinkedList one
     * @param l2 DoublyLinkedList two
     * @return
     */
    public static boolean equals(DoublyLinkedList l1, DoublyLinkedList l2){
    	if (l1.getEmpty() && l2.getEmpty()) {
            return true;
	} else if (l1.getEmpty() || l2.getEmpty()) {
            return false;
	} else if ( l1.getPerson().equals(l2.getPerson()) &&
				l1.getLeft() == l2.getLeft() &&
				l1.getRight() == l2.getRight()) {
			return equals(l1.getRight(), l2.getRight());
	} else {
            return false;
	}
    }
    
    /**
     * Checks if a certain list item has the opposite gender to both of it's neighbors'
     * Compares the first and last element to one neighbor each
     * 
     * @param list the list which contains the element
     * @return boolean - true if the neighbours are of opposite gender, false otherwise 
     * @throws IllegalStateException
     */
    public static boolean check(DoublyLinkedList list)throws IllegalStateException{
    	if(list.getEmpty() == true){
    		throw new IllegalStateException();
    	}else if(list.getLeft().getEmpty() == true && list.getRight().getEmpty() == true){
    		return true;
    	}else if(list.getLeft().getEmpty() &&
    		     list.getPerson().getGender() != list.getRight().getPerson().getGender()){
    		return true;
    	}else if(list.getRight().getEmpty() && 
    			 list.getPerson().getGender() != list.getLeft().getPerson().getGender()){
    		return true;
    	}else
    		return (list.getPerson().getGender() != list.getRight().getPerson().getGender() &&
    				list.getPerson().getGender() != list.getLeft().getPerson().getGender() );
    }
	
	//For testing purposes
    public static void main(String args[]){
		
		Person tom = new Person("tom", "M", 20);
		Person ann = new Person("ann", "F", 20);
		Person joe = new Person("joe", "M", 20);
		Person mary = new Person("mary", "F", 20);
		Person tim = new Person("tim", "M", 20);
		
		DoublyLinkedList list1 = new DoublyLinkedList();
		list1 = cons(tom, cons(ann, cons(joe, cons(mary, cons(tim, empty())))));
		DoublyLinkedList list2 = list1;
		
		System.out.println(list1);
		System.out.println(equals(list1,list2));
	}
	
	
}	
