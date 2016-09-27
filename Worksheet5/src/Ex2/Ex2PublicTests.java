package Ex2;


//import ex1.Person;
import static org.junit.Assert.*;

import org.junit.Test;


public class Ex2PublicTests {

	/**
	 * Searching for Tom's age
	 */
	@Test
	public void ex2PublicTest1() {
		Person p = new Person("John", "M",32);
		Person p1 = new Person("Alex", "M",15);   
		Person p2 = new Person("Tom", "M",24);   
		Person p3 = new Person("Bill", "M",23);  
		Person p4 = new Person ("Jackson", "F", 50);  
	
		

		BinarySearchPerson bt = new BinarySearchPerson(p,new BinarySearchPerson(),new BinarySearchPerson());
		BinarySearchPerson bt1 = BinarySearchPerson.insert(p1, bt);
		BinarySearchPerson bt2 = BinarySearchPerson.insert(p2, bt1);
		BinarySearchPerson bt3 = BinarySearchPerson.insert(p3, bt2);
		BinarySearchPerson bt4 = BinarySearchPerson.insert(p4, bt3);
		
		int ageExpected = 24;  
		assertEquals(bt4.lookupAge("Tom"), ageExpected);
	}

	
	
	/**
	 * Searching for Jackson's age
	 */
	@Test
	public void ex2PublicTest2() {
		Person p = new Person("John", "M",32);
		Person p1 = new Person("Alex", "M",15);   
		Person p2 = new Person("Tom", "M",24);   
		Person p3 = new Person("Bill", "M",23);  
		Person p4 = new Person ("Jackson", "F", 50);  
	
		

		BinarySearchPerson bt = new BinarySearchPerson(p,new BinarySearchPerson(),new BinarySearchPerson());
		BinarySearchPerson bt1 = BinarySearchPerson.insert(p1, bt);
		BinarySearchPerson bt2 = BinarySearchPerson.insert(p2, bt1);
		BinarySearchPerson bt3 = BinarySearchPerson.insert(p3, bt2);
		BinarySearchPerson bt4 = BinarySearchPerson.insert(p4, bt3);
		
		int ageExpected = 50;  
		assertEquals(bt4.lookupAge("Jackson"), ageExpected);
	}

	/**
	 * Searching for a person
	 * that is not stored in the tree
	 */
	@Test
	public void ex2PublicTest3() {
		Person p = new Person("John", "M",32);
		Person p1 = new Person("Alex", "M",15);   
		Person p2 = new Person("Tom", "M",24);   
		Person p3 = new Person("Bill", "M",23);  
		Person p4 = new Person ("Jackson", "F", 50);  
	
		

		BinarySearchPerson bt = new BinarySearchPerson(p,new BinarySearchPerson(),new BinarySearchPerson());
		BinarySearchPerson bt1 = BinarySearchPerson.insert(p1, bt);
		BinarySearchPerson bt2 = BinarySearchPerson.insert(p2, bt1);
		BinarySearchPerson bt3 = BinarySearchPerson.insert(p3, bt2);
		BinarySearchPerson bt4 = BinarySearchPerson.insert(p4, bt3);
		
		int ageExpected = -1;
		
		assertEquals(bt4.lookupAge("Andrew"), ageExpected);
	}


}
