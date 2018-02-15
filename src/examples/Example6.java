package examples;

public class Example6 {

		public static void main(String args[])
		{
			Cell<String> cell1 = new Cell<String>("Hello");

			System.out.println(cell1.getcount());


			Cell<Integer> cell2 = new Cell<Integer>(1);

			System.out.println(cell2.getcount());  // does this print 1 or 2?

		}

	}

	class Cell<T>
	{
		private T value;
		private static int count = 0;

		public static int getcount()
		{
			return count;
		}

		public Cell(T value)
		{
			this.value = value;
			count++;
		}

		public T getValue()
		{
			return value;
		}


}
