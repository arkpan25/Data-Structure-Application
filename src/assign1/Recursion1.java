package assign1;

public class Recursion1 {
	/**
	 *
	 * @param num the n value passed in the function
	 * @return The sequence as demand accordint to the num
	 */
	public String getSequence(int num){
		if (num == 0){
			String sequence = "0";
			return sequence;
		}
		else{
			return num+" "+getSequence(num-1)+" "+num;
		}
	}


	public static void main(String[] args) {
		Recursion1 recur = new Recursion1();
		System.out.print(recur.getSequence(5));

	}

}
