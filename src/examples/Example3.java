package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example3 {

	public static <T> void copy(List<? super T> dst, List<? extends T> src)
	{
		int i=0;
		for ( T n : src )
		{
			dst.set(i, src.get(i));
			i++;
		}
	}


	public static void main(String args[])
	{

		List<Number> nums = new ArrayList<Number>();
		nums.add(2.78);
		nums.add(3.14);

		List<Integer> ints = Arrays.asList(1, 2);

		copy(nums, ints);  // will this work?

		//copy(ints, nums);  // will this work?
		//The method copy(List<? super T>, List<? extends T>) in the type Example3 is not applicable for the arguments (List<Integer>, List<Number>)

    }
}
