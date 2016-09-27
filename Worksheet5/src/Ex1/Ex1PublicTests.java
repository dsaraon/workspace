package Ex1;

import static org.junit.Assert.*;

import org.junit.Test;

import Ex2.Person;


public class Ex1PublicTests {

	/**Testing a list with one element
	 * Should return true
	 */
	@Test
	public void ex1PublicTest1() {
		
		Person tom = new Person("Tom", "M", 22);
	
        
        DoublyLinkedList table = new DoublyLinkedList(tom, new DoublyLinkedList());
       assertTrue(DoublyLinkedList.check(table));
	
	}

	/**Testing a list with males and females
	 * Should return true
	 */
	@Test
	public void ex1PublicTest2() {
		Person tom = new Person("Tom", "M", 22);
		Person ann = new Person("Ann", "F", 21);
		Person joe = new Person("Joe", "M", 23);
		Person mary = new Person("Mary", "F", 22);
        Person tim = new Person("Tim", "M", 24);
        
        DoublyLinkedList table = new DoublyLinkedList(tom, new DoublyLinkedList(ann, 
        new DoublyLinkedList(joe, new DoublyLinkedList(mary, new DoublyLinkedList(tim, new DoublyLinkedList())))));
        
     
       assertTrue(DoublyLinkedList.check(table));
	
	}

	/**Testing a list with males only
	 * Should return false
	 */
	@Test
	public void ex1PublicTest3() {
		Person tom = new Person("Tom", "M", 22);
		Person joe = new Person("Joe", "M", 23);

        Person tim = new Person("Tim", "M", 24);
        
        DoublyLinkedList table = new DoublyLinkedList(tom, 
        new DoublyLinkedList(joe,new DoublyLinkedList(tim, new DoublyLinkedList())));
        
        
        //calling method with getRight().getLeft()
       assertFalse(DoublyLinkedList.check(table.getRight().getLeft()));
	
	}

	
	/**Testing a list with females only
	 * Should return false
	 */
	@Test
	public void  ex1PublicTest4() {
	
		Person ann = new Person("Ann", "F", 21);
		Person mary = new Person("Mary", "F", 22);
		Person joan = new Person("Joan","F",24);
		Person tom = new Person("Tom", "M", 22);

		DoublyLinkedList table = new DoublyLinkedList(ann, new DoublyLinkedList(mary, 
				new DoublyLinkedList(joan, new DoublyLinkedList(tom, new DoublyLinkedList()))));
		 
		
		//calling method with getRight().getRight()
		assertFalse(DoublyLinkedList.check(table.getRight().getRight()));

	}
	
	/**Testing a list with females only
	 * Should return true
	 */
	@Test
	public void  ex1PublicTest5() {
	
		Person ann = new Person("Ann", "F", 21);
		Person mary = new Person("Mary", "F", 22);
		Person joan = new Person("Joan","F",24);
		Person tom = new Person("Tom", "M", 22);

		DoublyLinkedList table = new DoublyLinkedList(ann, new DoublyLinkedList(mary, 
				new DoublyLinkedList(joan, new DoublyLinkedList(tom, new DoublyLinkedList()))));
		 
		
		//calling method with getRight().getRight().getRight() (Tom)
		assertTrue(DoublyLinkedList.check(table.getRight().getRight().getRight()));

	}

}
