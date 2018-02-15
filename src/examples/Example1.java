package examples;

import java.util.List;
import java.util.ArrayList;

public class Example1
{

 		public static void main(String args[])
	{


 	 		// the Java 1.4 way
// 			List words = new ArrayList();
// 			words.add("Hello");
// 			words.add(1);
//
// 			String str = (String)words.get(0);
// 			Integer num = (Integer)words.get(1);
// 		    System.out.println(str+" "+num);

 			// the Java Generics way
// 			List<String> words = new ArrayList<String>();
// 			words.add("Hello");
// 			words.add(1);    // will this work?
//
// 			String str = words.get(0);  // notice the auto-unboxing

		List<Number> nums = new ArrayList<Number>();

		nums.add(1);
		nums.add(3.14);

		for ( Number n : nums )
		{
			System.out.println(n);
		}


		List<Integer> ints = new ArrayList<Integer>();

		//nums = ints;  // will this work?
		//Type mismatch: cannot convert from List<Integer> to List<Number>


	}

}
