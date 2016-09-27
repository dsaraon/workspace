import static org.junit.Assert.*;

import org.junit.Test;

public class Ex2Test {

	/**
	 * PowerIter - simple
	 */
	@Test
	public void ex1PowerIterTest1() {
		double x = 2.0;
		int i = 5;
		double expected = 32.0;
		double result = Ex2.powerIter(x, i);
		// remember, third value is error bounds
		assertEquals(expected, result, 0.0001);
	}

	/**
	 * PowerIter - 0
	 */
	@Test
	public void ex1PowerIterTest2() {
		double x = 2.0;
		int i = 0;
		double expected = 1.0;
		double result = Ex2.powerIter(x, i);
		// remember, third value is error bounds
		assertEquals(expected, result, 0.0001);
	}

	/**
	 * PowerRec - simple
	 */
	@Test
	public void ex1PowerRecTest1() {
		double x = 2.0;
		int i = 5;
		double expected = 32.0;
		double result = Ex2.powerRec(x, i);
		// remember, third value is error bounds
		assertEquals(expected, result, 0.0001);
	}

	/**
	 * PowerIRec - 0
	 */
	@Test
	public void ex1PowerRecTest2() {
		double x = 2.0;
		int i = 0;
		double expected = 1.0;
		double result = Ex2.powerRec(x, i);
		// remember, third value is error bounds
		assertEquals(expected, result, 0.0001);
	}

	/**
	 * factorialIter - simple
	 */
	@Test
	public void ex1FactIterTest1() {
		int n = 5;

		int expected = 120;
		int result = Ex2.factorialIter(n);
		assertEquals(expected, result);
	}

	/**
	 * factorialIter - 0
	 */
	@Test
	public void ex1FactIterTest2() {
		int n = 0;

		int expected = 1;
		int result = Ex2.factorialIter(n);
		assertEquals(expected, result);
	}

	/**
	 * PowerIter - simple
	 */
	@Test
	public void ex1FactRecTest1() {
		int n = 5;

		int expected = 120;
		int result = Ex2.factorialRec(n);
		assertEquals(expected, result);
	}

	/**
	 * PowerIter - 0
	 */
	@Test
	public void ex1FactRecTest2() {
		int n = 0;

		int expected = 1;
		int result = Ex2.factorialRec(n);
		assertEquals(expected, result);
	}
	
	/**
	 * factorialIter - simple
	 */
	@Test
	public void ex1ExpIterTest1() {
		double x = 2.0;
		double thresh = 0.1;
		double expected = Math.exp(x);
		double  result = Ex2.expIter(x,thresh);
		
		assertEquals(expected, result, 0.2);
	}

	/**
	 * factorialIter - 0
	 */
	@Test
	public void ex1ExpIterTest2() {
		double x = 0;
		double thresh = 0.1;
		double expected = Math.exp(x);
		double  result = Ex2.expIter(x,thresh);
		assertEquals(expected, result, 0.1);
	}
	


	/**
	 * PowerIter - simple
	 */
	@Test
	public void ex1ExpRecTest1() {
		double x = 2.0;
		double thresh = 0.1;
		double expected = Math.exp(x);
		double  result = Ex2.expRec(x,thresh);
		assertEquals(expected, result, 0.2);
	}

	/**
	 * PowerIter - 0
	 */
	@Test
	public void ex1ExpRecTest2() {
		double x = 0;
		double thresh = 0.1;
		double expected = Math.exp(x);
		double  result = Ex2.expRec(x,thresh);
		assertEquals(expected, result, 0.1);
	}
	
	

}
