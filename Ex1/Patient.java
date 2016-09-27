package Ex1;
import java.util.Calendar;

public class Patient {
	
	private String name;
	private Calendar dateOfBirth;  
	private char gender;
	
	//constructor 
	public Patient(String name, Calendar dateOfBirth, char gender) throws IllegalArgumentException { 
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		
		if(gender == 'M' || gender == 'F'){
			this.gender = gender;
		}else {
			throw new IllegalArgumentException(); 
		}
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender)throws IllegalArgumentException{ 
		if(gender == 'M' || gender == 'F'){
			this.gender = gender;
		}else {
			throw new IllegalArgumentException(); 
		} 
	}
	
	@Override
	public String toString(){
		return "The name of the patient is : " + getName() + ".\n" + 
			   "The date of birth is : " + printDateOfBirth() + ".\n" +
			   "The gender is : " + getGender();
	}
	
	/**
	 * This is a helper method for toString()  
	 * 
	 * @return Calendar object DateOfBirth in a user-friendly string format
	 */
	public String printDateOfBirth(){
		Calendar dob = getDateOfBirth();
		return "" + dob.get(Calendar.DAY_OF_MONTH) +"-"+ dob.get(Calendar.MONTH)+1 +"-" + dob.get(Calendar.YEAR); 
	}
	
}
