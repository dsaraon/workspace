
public class Ex1 {
	public static double average(int[] a){		
		double sum = 0;
		for(int i =0; i<a.length; i++){
			sum = sum + a[i];
		}
		return sum/a.length;
	}

	public static void main(String[] args){
		int[] a = {1,2,3,4,5,6,7};
		System.out.println(average(a));
	}
}