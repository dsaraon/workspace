import static org.junit.Assert.*;

import org.junit.Test;


public class Ex2Test {

	/**
	 * Token appears thrice
	 */
	@Test
	public void ex2Test1() {
		String[] stringArray = { "the", "cat", "the", "on", "the", "mat" };
		String token = "the";
		int expected = 3;
		System.out.println("Ex2 Public Test 1");
		int result = Ex2.countOccurences(token, stringArray);
		assertEquals(expected, result);

	}


	/**
	 * Null array
	 */
	@Test
	public void ex2Test3() {
		String[] stringArray = { };
		String token = "dog";
		int expected = 0;
		System.out.println("Ex2 Public Test 3");

		int result = Ex2.countOccurences(token, stringArray);
		assertEquals(expected, result);

	}

}
