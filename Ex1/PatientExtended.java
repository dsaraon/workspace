package Ex1;
//extends Patient
import java.util.ArrayList;
import java.util.Calendar;


public class PatientExtended extends Patient{
	
	private Calendar lastSeen;
	
	public PatientExtended(String name, Calendar dateOfBirth, char gender, Calendar lastSeen){
		super(name, dateOfBirth, gender);
		this.lastSeen = lastSeen; 
	}
	
	public Calendar getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Calendar lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * This method goes through the list of patients in an arrayList and returns an arraylist  
	 * 
	 * @param listOfPatients ArrayList containing all patients 
	 * @param notSeenPatients ArrayList that is filled with patients not seen in one year
	 * @return returns the arrayList notSeenPatients which contains all patients not seen in one year
	 */
	public static ArrayList<PatientExtended> notSeen(ArrayList<PatientExtended> listOfPatients, ArrayList<PatientExtended> notSeenPatients){	 	
		for(int i = 0; i< listOfPatients.size(); i++){
		 	boolean notSeen =  status(listOfPatients.get(i)); 
		 	if(notSeen){
		 		notSeenPatients.add( listOfPatients.get(i) );  
		 	}
		}	
		System.out.println("The following patients have not been seen in a year :\n" + notSeenPatients);
		return notSeenPatients;
	}
	
	/**
	 * Helper method for notSeen(). Tests whether patient has been seen within the past one year.
	 * Specifically created to make JUnit testing easier  
	 * 
	 * @param patientDate Value of lastSeen field of the PatientExtended object
	 * @return true if the patient hasn't been seen within a year, false otherwise. 
	 */
	public static boolean status(PatientExtended p){
		Calendar compareDate=	Calendar.getInstance();
		compareDate.add(Calendar.YEAR, -1);
		Calendar patientDate = p.getLastSeen(); 
		return patientDate.before(compareDate);
	}
	
	@Override
	public String toString(){
		return super.toString() + "\n"  
					 + "Last seen on :" + printLastSeen() +".\n\n";
	}
	
	/**
	 * This is a helper method for toString()  
	 * 
	 * @return Calendar object lastSeen in a user-friendly string format
	 */
	public String printLastSeen(){
		Calendar ls = getLastSeen();
		return  ls.get(Calendar.DAY_OF_MONTH) +"-"+ ls.get(Calendar.MONTH)+1 +"-" + ls.get(Calendar.YEAR); 
	}

	public boolean equals(PatientExtended p){
		return (  this.getName().equals( p.getName() ) &&
				  this.getDateOfBirth().equals(p.getDateOfBirth()) &&
				  this.getGender() == p.getGender()  &&
				  this.getLastSeen().equals(p.getLastSeen())  );
				
	}
	
}
