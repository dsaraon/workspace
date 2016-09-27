import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit tests for Worksheet 2_2  
 */
public class Worksheet2Test {

	@Test
	/**
	 * Ex1 - balanced tree
	 */
	public void ex1test1() {
		Tree t1 = new Tree(20, 
						
							//left subtree
							new Tree(6,
									 new Tree(3),new Tree(9)), 
		                     
		                     //right subtree
		                     new Tree(22, 
		                    		  new Tree(17),new Tree()));
			
		assertTrue(Worksheet2.isHeightBalanced(t1));
	}
	
	@Test
	/**
	 * Ex1 - unbalanced tree, left heavy.
	 */
	public void ex1test2() {
		Tree t1 = new Tree(15, 

				//left subtree
				new Tree(3,
						new Tree(1),new Tree(4,new Tree(),new Tree(5,new Tree(),new Tree(7,new Tree(),new Tree(8))))), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));

		assertFalse(Worksheet2.isHeightBalanced(t1));
	}
	

	@Test
	/**
	 * Ex1 - unbalanced tree, right heavy.
	 */
	public void ex1test3() {
		Tree t1 = new Tree(15, 

				//left subtree
				new Tree(3,
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree(23,new Tree(22,new Tree(21),new Tree()),new Tree(27,new Tree(),new Tree()))));

		assertFalse(Worksheet2.isHeightBalanced(t1));
	}
	
	@Test
	/**
	 * Ex2 - search tree is binary 
	 */
	public void ex2test1() {
		Tree t1 = new Tree(15, 

				//left subtree
				new Tree(3,
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));

		assertTrue(Worksheet2.isSearchTree(t1));
	}
	
	@Test
	/**
	 * Ex2 - search tree is not binary 
	 */
	public void ex2test2(){
		Tree t1 = new Tree(10, 
				//left subtree
				new Tree(), 
				
				//right subtree
				new Tree(20, new Tree(9), new Tree()));
		
		assertFalse(Worksheet2.isSearchTree(t1));
	}
	
	@Test
	/**
	 * Ex2 search tree is not binary
	 * 20 and 17 are greater than 26 
	 */
	public void ex2test3() {

		Tree t1 = new Tree(26, 

				//left subtree
				new Tree(3,
						new Tree(5),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));

		assertFalse(Worksheet2.isSearchTree(t1));
	}
	
	@Test
	/**
	 * Ex2 - search tree is not binary 
	 * 19 is to the right of 20 
	 */
	public void ex2test4() {

		Tree t1 = new Tree(5, 

				//left subtree
				new Tree(3,
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree(19)));

		assertFalse(Worksheet2.isSearchTree(t1));
	}
	
	
	@Test
	/**
	 * Ex3 - insertion of 3 violates height-balance property  
	 * Testing RR rotation 
	 */
	public void ex3test1() {
		
		Tree t1 = new Tree(20, 
						
						    //left subtree    				
						    new Tree(10, 
								     new Tree(),new Tree()), 
						
						    //right subtree
						    new Tree());
	
		Tree t2 = Worksheet2.insertHB(3, t1);
		
		assertTrue( Worksheet2.isHeightBalanced(t2));		
	}
	
	@Test
	/**
	 * Ex3 - inserting 16 violates the height-balance property
	 * RR rotation again but not on the root
	 */
	public void ex3test2() {

		Tree t1 = new Tree(15, 

				//left subtree    				
				new Tree(3, 
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));
		

		Tree t2 = Worksheet2.insertHB(16, t1);

		Tree expected =  new Tree(15,

				//left subtree
				new Tree(3, 
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		assertEquals(expected,t2);		
	}

	@Test
	/**
	 * Ex3 - inserting 23 violates the height-balance property
	 * Testing LL rotation
	 */
	public void ex3test3() {

		Tree t1 = new Tree(15, 

				//left subtree    				
				new Tree(3, 
						new Tree(),new Tree()), 

						//right subtree
						new Tree(20, 
								new Tree(),new Tree()));
		
		Tree t2 = Worksheet2.insertHB(23, t1);
		
		assertTrue( Worksheet2.isHeightBalanced(t2));
	}
	

	@Test
	/**
	 * Ex3 - inserting 24 violates the height-balance property
	 * Testing LR rotation
	 */
	public void ex3test4() {

		Tree t1 = new Tree(15, 

				//left subtree    				
				new Tree(3, 
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(20, 
								new Tree(),new Tree(27,new Tree(),new Tree())));

		Tree t2 = Worksheet2.insertHB(24, t1);

		Tree expected =  new Tree(15,

				//left subtree
				new Tree(3, 
						new Tree(1),new Tree(4)), 

						//right subtree
						new Tree(24, 
								new Tree(20), new Tree(27)));

		assertEquals(expected,t2);		
	}

	@Test
	/**
	 * Ex3 - inserting 6 violates the height-balance property
	 * Testing RL rotation.
	 */
	public void ex3test5() {

		Tree t1 = new Tree(15, 

				//left subtree    				
				new Tree(7, 
						new Tree(5,new Tree(),new Tree()),new Tree()), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));

		Tree t2 = Worksheet2.insertHB(6, t1);

		Tree expected =  new Tree(15,

				//left subtree    				
				new Tree(6, 
						new Tree(5),new Tree(7)), 

						//right subtree
						new Tree(20, 
								new Tree(17),new Tree()));

		assertEquals(expected,t2);		
	}
	
	
	@Test
	/**
	 * Ex4 - deleting 15 violates the height-balance property
	 */
	public void ex4test1() {
		Tree t1 =  new Tree(15, 

				//left subtree
				new Tree(3, 
						new Tree(2, new Tree(1),new Tree()), 

						//to the right of 3
						new Tree(4)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		Tree t2 = Worksheet2.deleteHB(t1, 15);

		Tree expected = new Tree(4,

				//left subtree 
				new Tree(2,
						new Tree(1),new Tree(3)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		assertEquals(expected,t2);
	}

	@Test
	/**
	 * Ex4 - deleting 3 violates the height-balance property
	 */
	public void ex4test2() {
		Tree t1 =  new Tree(15, 

				//left subtree
				new Tree(3, 
						new Tree(2, new Tree(1),new Tree()), 

						//to the right of 3
						new Tree(4)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		Tree t2 = Worksheet2.deleteHB(t1, 3);

		Tree expected = new Tree(15,

				//left subtree
				new Tree(2, 
						new Tree(1, new Tree(),new Tree()), 

							new Tree(4)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		assertEquals(expected,t2);
	}
	
	@Test
	/**
	 * Ex4 - deleting 17 violates the height-balance property
	 */
	public void ex4test3() {
		Tree t1 =  new Tree(15, 

				//left subtree
				new Tree(3, 
						new Tree(2, new Tree(1),new Tree()), 

						//to the right of 3
						new Tree(4)), 

						//right subtree
						new Tree(17, 
								new Tree(16), new Tree(20)));

		Tree t2 = Worksheet2.deleteHB(t1, 17);

		Tree expected = new Tree(15,

				//left subtree
				new Tree(3, 
						new Tree(2, new Tree(1),new Tree()), 

						//to the right
						new Tree(4)), 

						//right subtree
						new Tree(16, 
								new Tree(), new Tree(20)));

		assertEquals(expected,t2);
	}
	
	
		
	
	



}
