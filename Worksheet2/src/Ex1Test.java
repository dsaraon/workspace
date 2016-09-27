import static org.junit.Assert.*;

import org.junit.Test;


public class Ex1Test {

	/**
	 * MSc Borderline
	 */
	@Test
	public void ex1Test1() {
		System.out.println("Ex1 Public Test 1 - Pass: 45,45,60,10,MSc");
		Boolean result = Ex1.hasPassed(50, 50, 50, 50, "MSc");
		assertTrue(result);

	}

	/**
	 * MSc maximum marks case 
	 */
	@Test
	public void ex1Test2() {

		System.out.println("Ex1 Public Test 2 - Full: 100,100,100,100,MSc");
		boolean result = Ex1.hasPassed(100, 100, 100, 100, "MSc");
		assertTrue(result);
	}

	/**
	 * Zero values case
	 */
	@Test
	public void ex1Test3() {

		System.out.println("Ex1 Public Test 3 - zero ICY: 0,0,0,0,ICY");
		boolean result = Ex1.hasPassed(0, 0, 0, 0, "ICY");
		assertFalse (result);

	}
}
