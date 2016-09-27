package Ex2;


public class BinarySearchPerson {
	
    private boolean empty;
    private Person value;
    private BinarySearchPerson left, right;

    /**
     * Creates a new BinaryTree whose root value is value and left and right
     *  
     */
    public BinarySearchPerson(Person value, BinarySearchPerson left, BinarySearchPerson right) {
        this.empty = false; 
        this.value = value; 
        this.left = left; 
        this.right = right;
    }
	    
    /**
     * Creates an empty tree
     */
    public BinarySearchPerson() {
        this.empty = true;
    }
	    
    /**
     * returns true if this tree is empty (eg nil)
     */
    public boolean getEmpty() {
        return this.empty;
    }
    
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	public void setLeft(BinarySearchPerson left) {
		this.left = left;
	}

	public void setRight(BinarySearchPerson right) {
		this.right = right;
	}
	    
    /**
     * gets the root value of this tree
     */
    public Person getValue() {
        if (getEmpty()) {
            throw new IllegalStateException(
                                            "Trying to access root of an empty tree");
        }
        return this.value;
    }
	    
    public void setValue(Person value) {
		this.value = value;
	}

	/**
     * gets the left subtree of this node
     */
    public BinarySearchPerson getLeft() {
        if (getEmpty()) {
            throw new IllegalStateException(
                                            "Trying to access subtree of an empty tree");
        }
        return this.left;
    }
	    
    /**
     * gets the right subtree of this node
     */
    public BinarySearchPerson getRight() {
        if (getEmpty()) {
            throw new IllegalStateException(
                                            "Trying to access subtree of an empty tree");
        }
        return this.right;
    }
    
	/**
	 * Method inserts a new item into the BinarySearchTree with value p as an Object of type Person
	 * Uses auxiliary method 
	 * sets the value of first item to p if the tree is empty  
	 *    
	 * @param p Object of type Person, to be inserted into the tree
	 * @param bt The tree into which the person is inserted
	 * @return new tree with the same root as the tree passed as parameter and with the person inserted
	 */
    public static BinarySearchPerson insert(Person p, BinarySearchPerson bt){
    	if(bt.getEmpty()){
    		bt.setEmpty(false);
    		bt.setValue(p);
    		return bt;
    	}else{
    		 insertAux(p,bt);
    		 return bt;
    	}
    	
    }
    
    /**
     * Auxiliary method to insert person into tree according to lexicon. Only inserts, of void type. 
     * case1 : Person already exists, does nothing
     * case2 : string name is bigger, goes to right subtree
     * case3 : found insertion point, sets immediate root's right reference to new Person object 
     * case4 : string name is smaller, goes to left subtree
     * case5 : found insertion point, sets immediate root's left reference to new Person object
     * 
     * @param p Object of type Person, to be inserted into the tree
     * @param bt  The tree into which the person is inserted
     */
    private static void insertAux(Person p, BinarySearchPerson bt){
    	
    	if(bt.getValue().getName().compareTo(p.getName()) == 0 ){
    		return ;
    	}else if(bt.getValue().getName().compareTo(p.getName()) < 0 && bt.getRight().getEmpty() == false){
    		insert(p, bt.getRight());
    	}else if(bt.getValue().getName().compareTo(p.getName()) < 0 && bt.getRight().getEmpty() == true){
    		bt.setRight(new BinarySearchPerson(p, new BinarySearchPerson(), new BinarySearchPerson()));
    	}else if(bt.getValue().getName().compareTo(p.getName()) > 0 && bt.getLeft().getEmpty() == false){
    		insert(p, bt.getLeft());
    	}else{
    		bt.setLeft(new BinarySearchPerson(p, new BinarySearchPerson(), new BinarySearchPerson()));
    	}
    }
    
   /**
    * Method to lookupAge of a Person object in the tree with string name
    * Uses auxiliary method to pass reference of calling object and traverse the tree
    * 
    * @param n String name to find object of type Person
    * @return the age of the person, -1 if the person does not exist in the tree
    */
    public int lookupAge(String n){
    	return lookupAgeAux(n,this);
    	
    }
    
    /**
     * Traverses through the tree to find Person with name n  
     * 
     * @param n String name to find object of type Person
     * @param bt reference to the calling tree
     * @return the age of the person, -1 if the person does not exist in the tree 
     */
    private static int lookupAgeAux(String n, BinarySearchPerson bt){
    	if(bt.getEmpty()){
    		return -1;
    	}else if(bt.getValue().getName().compareTo(n) == 0 ){
    		return bt.getValue().getAge();
    	}else if(bt.getValue().getName().compareTo(n) < 0){
    		return lookupAgeAux(n, bt.getRight());
    	}else{ 
    		return lookupAgeAux(n, bt.getLeft());
    	}
    }
  
    //For testing purposes
    public static void main(String args[]){
    	Person p = new Person("John", "M",32);
		Person p1 = new Person("Alex", "M",15);   
		Person p2 = new Person("Tom", "M",24);   
		Person p3 = new Person("Bill", "M",23);  
		Person p4 = new Person ("Jackson", "F", 50);
		Person p5 = new Person ("Yousef", "M", 19);
		
	
		BinarySearchPerson bt = new BinarySearchPerson(p,new BinarySearchPerson(),new BinarySearchPerson());
		BinarySearchPerson bt1 = BinarySearchPerson.insert(p1, bt);
		BinarySearchPerson bt2 = BinarySearchPerson.insert(p2, bt1);
		BinarySearchPerson bt3 = BinarySearchPerson.insert(p3, bt2);
		BinarySearchPerson bt4 = BinarySearchPerson.insert(p4, bt3);
		BinarySearchPerson bt5 = BinarySearchPerson.insert(p5, bt4);

		
		
		//testing root
		System.out.println(bt.getValue().getName());
		
		//testing the structure 
		System.out.println(bt4.getLeft().getRight().getRight().getEmpty());
		
		//testing method lookupAge
		System.out.println(bt4.lookupAge("Jackson"));
		System.out.println(bt4.lookupAge("Tom"));
		System.out.println(bt4.lookupAge("Alex"));
		System.out.println(bt4.lookupAge("John"));
		System.out.println(bt4.lookupAge("Bill"));
		
		System.out.println("The age of yousef is :" + bt5.lookupAge("Yousef"));
		
		
		
	    
    }
}

