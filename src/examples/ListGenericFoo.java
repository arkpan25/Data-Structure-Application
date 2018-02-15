package examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.sun.javafx.image.impl.General;
public class ListGenericFoo<T>
{
	private T[] fooArray;
	public T[] getFooArray()
	{
		return fooArray;
	}
	public void setFooArray(T[] fooArray)
	{
		this.fooArray = fooArray;
	}
	public static void main(String[] args)
	{
		ListGenericFoo<LinkedList> foo1 = new ListGenericFoo<LinkedList>();
		ListGenericFoo<ArrayList> foo2 = new ListGenericFoo<ArrayList>();
		//Error: Bound mismatch
		//ListGenericFoo<HashMap> foo3 = new ListGenericFoo<HashMap>();
		LinkedList[] linkedLists = new LinkedList[10];
		foo1.setFooArray(linkedLists);
		ArrayList[] arrayLists = new ArrayList[10];
		foo2.setFooArray(arrayLists);

		ListGenericFoo<? super List> foo = null;
		foo = new ListGenericFoo<List>();
		//foo = new ListGenericFoo<HashMap>();
	}
}