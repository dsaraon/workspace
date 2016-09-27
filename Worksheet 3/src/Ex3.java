
public class Ex3 {
	
	//iterative
	public static int digitTotalIter(int num){
		int sum = 0;
		while(num !=0){
			//get last digit
			sum = sum + num%10;
			//change num
			num = num/10;
		}
		return sum;
	}
	
	//recursive
	public static int digitTotalRec(int num){
		//base case 
		int sum = 0;
		if(num == 0){
			return sum;
		}
		else{
			return (sum + num%10) + digitTotalRec(num/10);
		}
		
		//recursive case
	}
	
	public static void main(String[] args){
		int num = 12345;
		System.out.println(digitTotalIter(num));
		System.out.println(digitTotalRec(num));
	}
}
