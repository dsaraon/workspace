package Ex1;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.GregorianCalendar;

//class to create patientExtended objects and test method notSeen
public class Clinic {
	public static void main(String args[]){
		
		ArrayList<PatientExtended> listOfPatients = new ArrayList<PatientExtended>();
		 
		PatientExtended patient1 = new PatientExtended("Elena", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'F', new GregorianCalendar(2013, Calendar.JANUARY, 25));
		PatientExtended patient2 = new PatientExtended("Constantine", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.OCTOBER, 30));
		PatientExtended patient3 = new PatientExtended("Divyjyot", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.NOVEMBER, 25));
		PatientExtended patient4 = new PatientExtended("Angelo", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2014, Calendar.JUNE, 25));
		PatientExtended patient5 = new PatientExtended("Pani", new GregorianCalendar(1993, Calendar.JANUARY, 25), 'M', new GregorianCalendar(2013, Calendar.DECEMBER, 27));
		
		
		listOfPatients.add(patient1);
		listOfPatients.add(patient2);
		listOfPatients.add(patient3);
		listOfPatients.add(patient4);
		listOfPatients.add(patient5);
				
		ArrayList<PatientExtended> notSeenPatients = new ArrayList<PatientExtended>();
		PatientExtended.notSeen(listOfPatients, notSeenPatients);
		
	}
}
