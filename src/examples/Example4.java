package examples;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;

public class Example4
{
	public static <T extends Comparable<? super T>> T max(Collection<T> coll)
	{
		T maxsofar = coll.iterator().next();
		for (T item : coll)
		{
			if (maxsofar.compareTo(item) < 0)
			   maxsofar = item;
		}
		return maxsofar;
	}

	public static void main (String... args)
	{
		List<Apple> apples = Arrays.asList(new Apple(1), new Apple(10));
		List<Orange> oranges = Arrays.asList(new Orange(1), new Orange(10));
		List<Fruit> fruits = Arrays.<Fruit>asList(new Apple(1), new Orange(10));

		System.out.println(max(apples));  // will this work?
		System.out.println(max(oranges));
		System.out.println(max(fruits));

    }
}


class Fruit implements Comparable<Fruit> {

    String name;
    int size;

    public Fruit(String name, int size)
    {
		this.name = name; this.size = size;
    }

    public int compareTo(Fruit that)
    {
		return
	      this.size < that.size ? - 1 :
	        this.size == that.size ? 0 : 1 ;
    }

    public String toString()
    {
		return this.name + "(" + size + ")";
    }

    public boolean equals(Object o)
    {
		return (o instanceof Fruit &&
			name.equals(((Fruit)o).name) &&
			size == ((Fruit)o).size) ;
    }
}

class Apple extends Fruit
{
    public Apple (int size)
    {
		super("Apple", size);
    }
}

class Orange extends Fruit
{
    public Orange (int size)
    {
		super("Orange", size);
    }
}
