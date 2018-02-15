package examples;

import java.util.NoSuchElementException;

import javafx.scene.Node;

public class myLL {

	private Node head;
	private Node tail;
	int size = 0;

	public void addFront(int x){
		Node front = new Node(x, head);
		head = front;

		if (tail == null){
			tail = head;
		}

		size++;
	}

	public void addBack(int x){
		if (tail == null){
			tail = new Node(x,null);
			head = tail;
		}
		else{
			tail.next = new Node(x,null);
			tail = tail.next;

		}
		size++;
	}

	public void removeFront(){
		if (head == null){
			throw new NoSuchElementException();
		}
		else if (head == tail){
			head = tail = null;
		}
		else head = head.next;
		
		size--;
	}
	
	public void removeBack(){
		
		if (head == null || head == tail){
			removeFront();
		}
		else{
			Node p = head;
			while (p.next != tail){
				p = p.next;
			}
			p.next = null;
			tail = p;
			size--;			
		}
	}
	
	public void reverse(){
		
		if (head == null || head == tail) return;
		Node prev = null;
		Node curr = head;
		Node next = curr.next;
		while (next != null){
			curr.next = prev;
			prev = curr;
			curr = next;
			next = next.next;						
		}
		curr.next = prev;
		tail = head;
		head = curr;
		
	}
	public int get(int i)
	{
	        if (i < 0 || i > size - 1)
	           throw new ArrayIndexOutOfBoundsException("Index " + i + "; size " + size);

	        Node p = head;
	        for (int k=0; k<i; k++)
	           p = p.next;

	        return p.data;
	 }


	//@Override

	public String toString(){
		StringBuilder sb = new StringBuilder("[");

		Node p = head;
		while (p != null){
			sb.append(" ");
			sb.append(p.data);
			p = p.next;
		}
		sb.append(" ]");
		return new String(sb);


	}




	private static class Node{
		int data;
		Node next;

		Node(int d, Node n){
			data = d;
			next = n;

		}
	}

	public static void main (String args[]){

		myLL list = new myLL();

		for (int i = 0; i<5; i++){
			//list.addFront(i);
			list.addBack(i);
		}
		System.out.println(list);
		list.reverse();
		System.out.println(list);
		/*
		System.out.println(list);
		for (int i = 0; i<3; i++){
			list.removeBack();
			System.out.println(list);
		}
		*/

	}

}
