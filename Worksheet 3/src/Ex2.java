
public class Ex2{
	//calculate power
	public static double powerIter(double x, int i){
		if(i ==0){
			return 1;
		}

		double power = 1;
		for (int j = 0; j < i; j++) {
			power = power * x;
		}
		return power;
	}
	
	//calculate factorial
	public static int factorialIter(int n){
		int fact = 1;
		for(int i = 1; i <= n; i++){
			fact = fact * i; 
		}
		return fact;
	}
	
	//recursive methods
	public static double powerRec(double x, int i){
		if(i==0){
			return 1;
		}
		else{
			return x * powerRec(x,i-1);
		}
	}
	
	public static int factorialRec(int n){
		if(n==0){
			return 1;
		}
		else{
			return n* factorialRec(n-1);
		}
	}
	
	
	public static double expIter(double x, double threshold){
		double exp = 0;
		int i = 0;
		while( ( powerIter(x,i)/factorialIter(i) )> threshold){
			exp = exp + ( powerIter(x,i)/factorialIter(i) );
			i++;
		}
		return exp;
		}
	
	static int i = -1;
	static double exp = 0;
	public static double expRec(double x, double threshold){
		i++;
		double expDuplicate; 
		if( powerRec(x,i)/factorialRec(i) < threshold){
			return powerRec(x,i)/factorialRec(i);
		}
		else{
			exp =powerRec(x,i)/factorialRec(i) + (expRec(x,threshold));
			expDuplicate = exp; // using duplicates to re initialize static variables for multiple tests 
			exp = 0;
			i = -1;
			return expDuplicate;
		}
	}
}
	
	
	