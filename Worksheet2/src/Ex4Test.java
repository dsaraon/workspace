import static org.junit.Assert.*;

import org.junit.Test;


public class Ex4Test {

	/*
	 * Example case - similar length strings 
	 */
	@Test
	public void ex4Test1() {
		String[] input = { "I", "am", "writing", "a", "static", "method", "for", "Bubble", "Sort", "algorithm", ".", "It", "is", "fun" };
		String[] expected = { 
				"I", 
				"a",
				"." ,
				"am", 
				"It", 
				"is", 
				"for", 
				"fun", 
				"Sort", 
				"Bubble", 
				"method", 
				"static", 
				"writing", 
		        "algorithm"  };

		System.out.println("Ex4 Public Test 1");
		String[] result = Ex4.bubbleSort(input);
		assertArrayEquals("Arrays do not match", expected, result);


	}


}

