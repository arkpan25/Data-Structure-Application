/**
 *
 */
package project1;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * @author Fangzhou Pan
 *
 */
public class MyStack<AnyType> {

	private ArrayList<AnyType> arrayList;
	private int top;

	public MyStack(){
		arrayList = new ArrayList<AnyType>();
        top = -1;
	}

	public boolean isEmpty(){
		return arrayList.isEmpty();
	}

	public void push(AnyType x){
		arrayList.add(x);
		top++;
	}

	public AnyType peek(){
		if(isEmpty()) new EmptyStackException();
		return arrayList.get(top);
	}

	public AnyType pop(){
		if (isEmpty()){
			new EmptyStackException();
		}
		AnyType value = arrayList.get(top--);
		arrayList.remove(arrayList.size()-1);
		return value;

	}

	public int size(){
		return arrayList.size();
	}

	@Override
	public String toString() {
		return "MyStack [" + arrayList.toString() + "]";
 	}


	public static void main(String[] args) {
		// Testing myStack class
		MyStack<String> s = new MyStack<String>();
		System.out.println(s.isEmpty());
		System.out.println(s);
		s.push("A");
	    s.push("B");
	    s.push("C");
	    System.out.println(s);
	    System.out.println("size->"+s.size());
	     int l=s.size();
	     for (int i=0;i<l;i++){
	          System.out.println("s.pop->"+s.pop());
	        }

	     //Use your MyStack class to demonstrate the nested symbol algorithm
	     MyStack<Character> theStack = new MyStack<Character>();
	     //String symbols = "[({}{})]"; //output balanced
	     //String symbols = "([)]";     //output unbalanced
	     String symbols = "([1+2])";    //output balanced
	     for (int k=0; k<symbols.length(); k++){
			char symbol = symbols.charAt(k);
			if (symbol == '['||symbol == '('||symbol=='{'){
				theStack.push(symbol);
			}
			if (symbol == '}'){
				if (theStack.peek()=='{') theStack.pop();
				else break;
			}
			else if (symbol == ')'){
				if (theStack.peek()=='(') theStack.pop();
				else break;
			}
			else if (symbol == ']'){
				if (theStack.peek()=='[') theStack.pop();
				else break;
			}
		 }
	     if (theStack.isEmpty()) System.out.println("The symbols are balanced");
	     else System.out.println("The symbols are unbalanced");

	}

}
