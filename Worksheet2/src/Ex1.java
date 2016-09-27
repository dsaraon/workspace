
public class Ex1 {
    /**
     *This method evaluates whether a student has passed a module depending on his status as a MSc or ICY student
     *
     * @param continuousAssessment1 marks obtained in the first continuous assessment
     * @param continuousAssessment2 marks obtained the second continuous assessment
     * @param exam marks obtained in the final exam
     * @param teamWork marks obtained through group assignment
     * @param studentCategory student status - MSc or ICY
     * @return the final pass or fail grade of the module 
     **/
	public static boolean hasPassed(int continuousAssessment1, int continuousAssessment2, int exam, int teamWork, String studentCategory){
		
		//calculates total marks based on the weight an assessment carries for the whole module 
		double totalMark = (continuousAssessment1 * 12.5 / 100) + (continuousAssessment2 * 7.5 / 100) + (exam * 70 / 100) + (teamWork * 10 / 100); 
		
		//tests for student category and corresponding pass value and returns the result 
		if( ( studentCategory.equals("MSc") && totalMark >= 50 ) || 
			( studentCategory.equals("ICY") && totalMark >= 40 ) ) {
			return true; 
	    }
		else {
			return false; 
		}
	}

	public static void main(String args[]){
		System.out.println(hasPassed(50,40,30,67,"MSc"));
	}
}
