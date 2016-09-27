import static org.junit.Assert.*;

import org.junit.Test;

import predictive.PredictivePrototype;


public class ModelTest {

	/**
	 * Normal test for setSignature
	 */
	@Test
	public void test1() {
		Model model = new Model();
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		StringBuffer sig2 = new StringBuffer();
		sig2.append("2");
		sig2.append("2");
		sig2.append("2");
		sig2.append("2");
		String expected = sig2.toString();
		String result = model.getSignature().toString();
		
		assertTrue(result.equals(expected));;
	}
	
	/**
	 * test for setSignature - testing '#'
	 */
	@Test
	public void test2() {
		Model model = new Model();
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("#");
		StringBuffer sig2 = new StringBuffer();
		sig2.append("2");
		sig2.append("2");
		sig2.append("2");
		sig2.append("2");
		String expected = sig2.toString();
		String result = model.getSignature().toString();
		
		assertFalse(result.equals(expected));;
	}

	/**
	 * test for setSignature - testing '0'
	 */
	@Test
	public void test3() {
		Model model = new Model();
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("2");
		model.setSignature("0");
		
		StringBuffer sig2 = new StringBuffer();

		String expected = sig2.toString();
		String result = model.getSignature().toString();
		
		assertTrue(result.equals(expected));;
	}
	
	/**
	 * test for setI - testing '*'
	 */
	@Test
	public void test4() {
		Model model = new Model();
		model.setSignature("2");
		model.incrementI();

		String expected = "b";
		String result = model.getArray().get(1);
		
		assertTrue(result.equals(expected));;
	}
	
	/**
	 * test for message testing '0'
	 */
	@Test
	public void test5() {
		Model model = new Model();
		
		model.setSignature("8");
		model.setSignature("7");
		model.setSignature("4");
		model.setSignature("8");
		model.setSignature("4");
		model.setSignature("2");
		model.setSignature("5");
		
		model.setMessage();
		model.setSignature("0");
		
		String expected = "trivial ";
		String result = model.getMessage().toString();

		assertTrue(result.equals(expected));
	}
	
	/**
	 * test for cyclic property of 'i'
	 */
	@Test
	public void test6() {
		Model model = new Model();
		
		model.setSignature("2");
		model.incrementI();
		model.incrementI();
		model.incrementI();
		
		String expected = "a";
		String result = model.getArray().get(0);

		assertTrue(result.equals(expected));
	}

}
