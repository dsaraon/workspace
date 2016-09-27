package Ex1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class PatientExtendedTest {
	
	ArrayList<PatientExtended> listOfPatients = new ArrayList<PatientExtended>();
	 

	/**
	 * tests equals method for two different PatientExtended objects. All fields are unequal.
	 */
	@Test
	public void test1() {
		PatientExtended patient1 = new PatientExtended("Elena", new GregorianCalendar(1993, Calendar.OCTOBER, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.OCTOBER, 30));
		boolean expected = false;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * tests equals method for two similar PatientExtended objects
	 */
	@Test
	public void test2() {
		PatientExtended patient1 = new PatientExtended("Elena", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Elena", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = true;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}
		
	/**
	 * tests equals method for two different PatientExtended objects. Field 'name' is unequal.
	 */
	@Test
	public void test3() {
		PatientExtended patient1 = new PatientExtended("Elena", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = false;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * tests equals method for two different PatientExtended objects. Field 'dateOfBirth' is unequal.
	 */
	@Test
	public void test4() {
		PatientExtended patient1 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 28), 'M', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = false;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * tests equals method for two different PatientExtended objects. Field 'lastSeen' is unequal.
	 */
	@Test
	public void test5() {
		PatientExtended patient1 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.OCTOBER, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = false;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}

	/**
	 * tests equals method for two different PatientExtended objects. Field 'gender' is unequal.
	 */
	@Test
	public void test6() {
		PatientExtended patient1 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = false;
		boolean result = patient1.equals(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * tests helper method status() to see if the patient has been seen within a year. Expected value - false.
	 */
	@Test
	public void test7() {
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2014, Calendar.JANUARY, 25));
		boolean expected = false;
		boolean result = PatientExtended.status(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * tests helper method status() to see if the patient has been seen within a year. Expected value - true.
	 */
	@Test
	public void test8() {
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		boolean expected = true;
		boolean result = PatientExtended.status(patient2);
		assertEquals(expected, result);
	}
	
	/**
	 * Tests for exactly one year before the current date. Expected value - false.
	 */
	@Test
	public void test9() {
		Calendar compareDate=	Calendar.getInstance();
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', compareDate);
		boolean expected = false;
		boolean result = PatientExtended.status(patient2);
		assertEquals(expected, result);
	}
}
