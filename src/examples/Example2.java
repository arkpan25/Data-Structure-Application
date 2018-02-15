package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example2 {

	public static void printlist(List<Integer> nums)
	{
		for (Integer n : nums )
		{
			System.out.println(n);
		}
	}


	public static void main(String args[])
	{

		List<Number> nums = new ArrayList<Number>();
		nums.add(1);
		nums.add(2);

		List<Integer> ints = Arrays.asList(1, 2);


		//printlist(nums);

		printlist(ints);  // will this work?
		//The method printlist(List<Number>) in the type Example2 is not applicable for the arguments (List<Integer>)


	}

}
