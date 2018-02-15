package assign1;

public class Recursion2 {

   /**
    *
    * @param array an input array of integers
    * @param pos a position as parameters
    * @return  the count of odd numbers in the array
    */
	public int getOddNumsInArray(int[] array, int pos){
		if (pos<array.length){
			if (array[pos]%2 == 0){
				return 0 + getOddNumsInArray(array,pos+1);
			}
			else{
				return 1 + getOddNumsInArray(array,pos+1);
			}
		}
		else{
			return 0;
		}

	}

	public static void main(String[] args) {

		Recursion2 recur2 = new Recursion2();
		int[] array = new int[8];
		for (int k = 0; k<8;k++){
			array[k] = 5*k-17;
			System.out.print(array[k]+" ");
		}
		int oddNum = recur2.getOddNumsInArray(array, 0);
		System.out.println();
		System.out.println("The count of odd number in the array is: "+oddNum);
		//System.out.println((-7)%2);
	}

}
