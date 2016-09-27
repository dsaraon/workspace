import static org.junit.Assert.*;

import org.junit.Test;

/** @author Divyjyot Saraon
 * 
 * This class contains the test cases for Worksheet1 solutions.
 */
public class Worksheet1Test {
		
	@Test
	/**
	 * Ex1
	 * Testing a list with negative numbers - should return false
	 */
	public void ex1Test1() {
	List l1 = List.cons(11,List.cons(-6, (List.empty())));

		assertFalse(Worksheet1.allPositive(l1));	
	}
	
	@Test
	/**
	 * Ex1
	 * Testing a list with zero - should return true
	 */
	public void ex1Test2() {
	List l1 = List.cons(0,List.cons(6, (List.empty())));

		assertTrue(Worksheet1.allPositive(l1));
	}
	
	@Test
	/**
	 * Ex1
	 * Testing an empty list - should return true
	 */
	public void ex1Test3() {
	List l1 = List.empty();

		assertTrue(Worksheet1.allPositive(l1));
	}
	
	@Test
	/**
	 * Ex2
	 * Testing an unsorted list - should return false
	 */
	public void ex2Test1() {
		List l1 = List.cons(1,List.cons(2,List.cons(4, List.cons(3,  (List.empty())))));
		
		assertFalse(Worksheet1.sorted(l1));
	}
	
	@Test
	/**
	 * Ex2
	 * Testing a sorted list with duplicates appearing together - should return true
	 */
	public void ex2Test2() {
		List l1 = List.cons(1,List.cons(2,List.cons(3, List.cons(3,  (List.empty())))));
		
		assertTrue(Worksheet1.sorted(l1));
	}
	
	@Test
	/**
	 * Ex2
	 * Testing an empty list - should return true
	 */
	public void ex2Test3() {
		List l1 = List.empty();
		
		assertTrue(Worksheet1.sorted(l1));
	}
	
	@Test
	/**
	 * Ex3
	 * Given two sorted lists, testing for merging lists.
	 * All elements of the first list are greater than that of the second list.
	 * 
	 */
	public void ex3Test1() {
		List l1 = List.cons(20,List.cons(30,List.cons(50, List.cons(80,  (List.empty())))));
		List l2 = List.cons(5,List.cons(7,List.cons(8, List.cons(9,  (List.empty())))));	
		
		List merged = Worksheet1.merge(l1,l2);
		
		List expected = List.cons(5, List.cons(7, List.cons(8, List.cons(9, 
		List.cons(20, List.cons(30, List.cons(50, List.cons(80, List.empty()))))))));
	
		assertEquals(merged,expected);
	}
	
	@Test
	/**
	 * Ex3
	 * Given two sorted lists, testing for merging lists.
	 * One list is empty
	 * 
	 */
	public void ex3Test2() {
		List l1 = List.empty();
		List l2 = List.cons(2,List.cons(5,List.cons(5, List.cons(8,  (List.empty())))));
		
		List merged = Worksheet1.merge(l1,l2);
		
		List expected = List.cons(2, List.cons(5, List.cons(5, List.cons(8, List.empty()))));
	
		assertEquals(merged,expected);
	}
	
	@Test
	/**
	 * Ex4
	 * Removing duplicates from a list - the list is empty. Should return empty list.
	 * 
	 */
	public void ex4Test1()  {
		List l1 = List.empty();	
	
		List expected = List.empty();
		
		assertEquals(Worksheet1.removeDuplicates(l1), expected);
	}
	
	@Test
	/**
	 * Ex4
	 * Removing duplicates from a list - the list is made of the same node value. 
	 * Should return new list with just root node.
	 * 
	 */
	public void ex4Test2()  {
		List l1 = List.cons(5, List.cons(5, List.cons(5, List.cons(5, 
		List.cons(5, List.cons(5, List.cons(5, List.cons(5, List.empty()))))))));	
	
		List expected = List.cons(5, List.empty());
		
		assertEquals(Worksheet1.removeDuplicates(l1), expected);	
	}
	
	@Test
	/**
	 * Ex5 
	 * Testing for the mirrored tree. The tree has just one node. Should return a duplicate.
	 */
	public void ex5Test1() {
		Tree t1 = new Tree(5, new Tree(), new Tree());
	
		Tree expected =  new Tree(5, new Tree(), new Tree());
		
		assertEquals(Worksheet1.mirror(t1),expected);	
	}
	
	@Test
	/**
	 * Ex5 
	 * Testing for the mirrored tree. The tree passed as argument is empty. Should return a duplicate.  
	 */
	public void ex5Test2() {
		Tree t1 = new Tree();
	
		Tree expected =  new Tree();
		
		assertEquals(Worksheet1.mirror(t1),expected);	
	}
	
	@Test
	/**
	 * Ex5 
	 * Testing for the mirrored tree.A tree with no left subtrees. 
	 */
	public void ex5Test3() {
		Tree t1 = new Tree(5,new Tree(),new Tree(8,new Tree(), new Tree(16, new Tree(),
				new Tree(22, new Tree(),new Tree()))));
	
		Tree expected = new Tree(5,new Tree(8,new Tree(16, new Tree(22, 
				new Tree(),new Tree()),new Tree()),
				new Tree()), new Tree());
		assertEquals(Worksheet1.mirror(t1),expected);
		
	}
	
	@Test
	/**
	 * Ex6
	 * Testing showDescending method that returns the values as a String in descending order.
	 * Empty tree should return empty String. 
	 * 
	 */
	public void ex6Test1() {
		Tree t1 = new Tree();
			
		String expected = "";
		
		assertEquals(Worksheet1.showDescending(t1), expected );	
	}
	
	@Test
	/**
	 * Ex6
	 * Testing showDescending method that returns the values as a String in descending order.
	 * Tree containing duplicate values 
	 */
	public void ex6Test2() {
		Tree t1 = new Tree(5,new Tree(4,new Tree(1,new Tree(), new Tree()),
				new Tree(5,new Tree(), new Tree())),
				new Tree(8,new Tree(6,new Tree(),new Tree()),new Tree()));
			
		String expected = "8 6 5 5 4 1";
		
		assertEquals(Worksheet1.showDescending(t1), expected );	
	}
	
	@Test
	/**
	 * Ex6
	 * Testing showDescending method that returns the values as a String in descending order.
	 * Tree with no left subtrees. 
	 * 
	 */
	public void ex6Test3() {
		Tree t1 = new Tree(5,new Tree(),new Tree(8,new Tree(), new Tree(16, new Tree(),
				new Tree(22, new Tree(),new Tree()))));
			
		String expected = "22 16 8 5";
		
		assertEquals(Worksheet1.showDescending(t1), expected );
	}
	

	@Test
	/**
	 * Ex7
	 * Testing for the maximum value in a binary search tree 
	 * A tree with no right subtrees. Should return the root node value.
	 */
	public void ex7Test1() {
		Tree t1 = new Tree(15,new Tree(8,new Tree(6, new Tree(2, 
				new Tree(),new Tree()),new Tree()),
				new Tree()), new Tree());
		
		int expected = 15;
		
		assertEquals(Worksheet1.max(t1),expected);
	}
	
	@Test
	/**
	 * Ex7
	 * Testing for the maximum value in a binary search tree 
	 * A tree with only one node. Should return the root node value.
	 */
	public void ex7Test2() {
		Tree t1 = new Tree(15, new Tree(), new Tree());
		
		int expected = 15;
		
		assertEquals(Worksheet1.max(t1),expected);
	}
	
	@Test
	/**Ex8
	 * Deleting 8 from t1(node with one subtree) .
	 */
	public void ex8Test1() {
		Tree t1 = new Tree(5,new Tree(3,new Tree(1,new Tree(), new Tree()),
				new Tree(4,new Tree(), new Tree())),
				new Tree(8,new Tree(6,new Tree(),new Tree()),new Tree()));
	
		Tree expected = new Tree(5,new Tree(3,new Tree(1,new Tree(), new Tree()),
				new Tree(4,new Tree(), new Tree())),
				new Tree(6,new Tree(),new Tree()));
		
		assertEquals(Worksheet1.delete(t1, 8),expected);
	}
	
	@Test
	/**Ex8
	 * Deleting 3 from t1(node with two subtrees) .
	 */
	public void ex8Test2() {
		Tree t1 = new Tree(5,new Tree(3,new Tree(1,new Tree(), new Tree()),
				new Tree(4,new Tree(), new Tree())),
				new Tree(8,new Tree(6,new Tree(),new Tree()),new Tree()));
	
		Tree expected = new Tree(5,new Tree(1,new Tree(),
				new Tree(4,new Tree(), new Tree())),new Tree(8,
				new Tree(6,new Tree(),new Tree()), new Tree()));
		
		assertEquals(Worksheet1.delete(t1, 3),expected);
	}

	@Test
	/**Ex8
	 * Deleting 11 from t1(node with two subtrees - complex case)
	 * The node whose value substitutes the deleted item also has two subtrees. 
	 */
	public void ex8Test3() {
		Tree t1 = new Tree(15,new Tree(13, new Tree(11, new Tree(5, new Tree(), new Tree()), 
				new Tree(12,new Tree(), new Tree())),
				new Tree(14,new Tree(), new Tree())),
				new Tree(18,new Tree(16,new Tree(),new Tree()),new Tree()));
	
		Tree expected = new Tree(15,new Tree(13, new Tree(5, new Tree(), 
				new Tree(12,new Tree(), new Tree())),
				new Tree(14,new Tree(), new Tree())),
				new Tree(18,new Tree(16,new Tree(),new Tree()),new Tree()));
		
		assertEquals(Worksheet1.delete(t1, 11),expected);
	}

}
