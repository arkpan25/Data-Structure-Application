package project1;

/**
 * LinkedList class implements a doubly-linked list.
 * Grade 98/100
 * TA comments -2 should shift everything at once instead of one by one
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }

    private void clear( )
    {
        doClear( );
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }

    public boolean isEmpty( )
    {
        return size( ) == 0;
    }

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;

        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );

        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }

    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }
    /**
     * swaps two nodes at specific positions by changing the links,
     * provided both positions are within the current size
     * @param idx1 the index of first node
     * @param idx2 the index of second node
     */

    public void swap(int idx1, int idx2){
//    	if( idx1 < 0 || idx2 > theSize-1)
//            throw new IndexOutOfBoundsException();
    	Node<AnyType> p1 = getNode(idx1);
     	Node<AnyType> p2 = getNode(idx2);

    	if (Math.abs(idx2-idx1)>1){
         	Node<AnyType> np1 = p1.next;
         	Node<AnyType> bp1 = p1.prev;
         	p1.next = p2.next;
        	p1.prev = p2.prev;
        	p2.prev.next = p1;
        	p2.next.prev = p1;
        	p2.next = np1;
        	p2.prev = bp1;
        	np1.prev = p2;
        	bp1.next = p2;
//        	Node<AnyType> np2 = p2.next;
//        	remove(p1);
//        	remove(p2);
//        	addBefore(np1,p2.data);
//        	addBefore(np2,p1.data);
    	}
    	else if (idx2-idx1 == 1){
    		Node<AnyType> bp1 = p1.prev;
    		p1.prev = p2;
    		p1.next = p2.next;
    		p2.next.prev = p1;
    		p2.next = p1;
    		p2.prev = bp1;
    		bp1.next = p2;
//    		remove(p1);
//    		addBefore(p2.next,p1.data);
    	}
    	else if (idx1-idx2 == 1){
    		Node<AnyType> bp2 = p2.prev;
    		p2.prev = p1;
    		p2.next = p1.next;
    		p1.next.prev = p2;
    		p1.next = p2;
    		p1.prev = bp2;
    		bp2.next = p1;
//    		remove(p2);
//    		addBefore(p1.next,p2.data);
    	}

    }
    /**
     * receives an integer (positive or negative) and shifts the list this
     * many positions forward (if positive) or backward (if negative)
     * @param num positions needed to be shifted.
     */
    public void shift(int num){
		if (num>0){
			while(num>0){
				Node<AnyType> last = getNode(theSize-1);
				last.prev.next = endMarker;
				endMarker.prev = last.prev;
				getNode(0).prev = last;
				last.next = getNode(0);
				beginMarker.next = last;
				last.prev = beginMarker;
				num--;
			}
		}
		else{
			while(num<0){
			     Node<AnyType> first = getNode(0);
			     Node<AnyType> last = getNode(theSize-1);
			     beginMarker.next = first.next;
			     first.next.prev = beginMarker;
			     last.next = first;
			     first.next = endMarker;
			     first.prev = last;
			     endMarker.prev = first;
			     num++;
			}
		}
	}
    /**
     * receives an index position and number of elements as parameters, and
     * removes elements beginning at the index position for the number of
     * elements specified, provided the index position is within the size
     * and together with the number of elements does not exceed the size
     * @param idx position of first element to remove
     * @param num the number of total elements to be removed
     */
    public void erase(int idx, int num){
    	int lastIdx = idx+num-1;
    	if (lastIdx>theSize-1){
    		throw new IndexOutOfBoundsException("\n"+"the number of elements exceed the size of List"
    				                            +"  getNode index: " + lastIdx + "; size: " + size( ));
    	}
    	Node<AnyType> first = getNode(idx);
    	Node<AnyType> last = getNode(lastIdx);
    	first.prev.next = last.next;
    	last.next.prev = first.prev;
    	theSize -= num;
    }
    /**
     * receives another MyLinkedList and an index position as parameters, and
     * copies the list from the passed list into the list at the specified
     * position, provided the index position does not exceed the size.
     * @param lst the LinkedList needed to be insert
     * @param idx the position the LinkedList insert in
     */
    public void insertList(MyLinkedList<AnyType> lst,int idx){
    	MyLinkedList<AnyType> insertLst = new MyLinkedList<AnyType>();
    	for (AnyType x: lst){
    		insertLst.add(x);
    	}
    	if (idx<0||idx>theSize){
    		throw new IndexOutOfBoundsException("provided index position exceed the size");
    	}
    	Node<AnyType> firstNode = insertLst.getNode(0);
	    Node<AnyType> lastNode = insertLst.getNode(insertLst.size()-1);
    	if (idx == theSize){
    	    getNode(theSize-1).next = firstNode;
    	    firstNode.prev = getNode(theSize-1);
    	    lastNode.next = endMarker;
    	    endMarker.prev = lastNode;
    	}
    	else if (idx == 0){
    		lastNode.next = getNode(0);
    		getNode(0).prev = lastNode;
    		beginMarker.next = firstNode;
    		firstNode.prev = beginMarker;
    	}
    	else{
    		Node<AnyType> begin = getNode(idx-1);
    		Node<AnyType> end = getNode(idx);
    		begin.next = firstNode;
    		firstNode.prev = begin;
    		lastNode.next = end;
    		end.prev = lastNode;
    	}
    	theSize += lst.size();

    }


    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current != endMarker;
        }

        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException();

            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }

        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );
        System.out.println("Test for the swap method to make sure it work");
//        lst.swap(-1,0);
//        System.out.println(lst);
//        lst.swap(0,lst.size());
//        System.out.println(lst);
        System.out.println("Before swap:"+ lst );
        lst.swap(lst.size()-1, 0);
        System.out.println( lst );
        lst.swap(1,lst.size()-1);
        System.out.println( lst );
        lst.swap(0,1);
        System.out.println(lst);
        lst.swap(1,0);
        System.out.println(lst);
        lst.swap(1,1);
        System.out.println(lst);
        System.out.println("Test for the shift method to make sure it work");
        System.out.println("Before shift:"+ lst );
        lst.shift(5);
        System.out.println(lst);
        lst.shift(-5);
        System.out.println(lst);
        lst.shift(lst.size()); // remains the same
        System.out.println(lst);
        System.out.println("Test for the erase method to make sure it work");
        System.out.println("Before erase:"+ lst );
        lst.erase(0, 5);
        System.out.println(lst+"The length of the LinkedList: "+lst.size());
        lst.erase(2,5);
        System.out.println(lst+"The length of the LinkedList: "+lst.size());
        lst.erase(7,1);
        System.out.println(lst+"The length of the LinkedList: "+lst.size());
//        lst.erase(1,12); //IndexOutOfBoundsException
//        System.out.println(lst+"The length of the LinkedList: "+lst.size());
        System.out.println("Test for the insertList method to make sure it work");
        MyLinkedList<Integer> insertLst = new MyLinkedList<Integer>();
        for( int i = 0; i < 5; i++ )
            insertLst.add( i );
        System.out.println("Before insertion:"+lst+"The length of the LinkedList: "+lst.size());
        System.out.println(insertLst+"The length of the insert list: "+lst.size());
        lst.insertList(insertLst, 0);
        System.out.println("After insertion:"+lst+"The length of the LinkedList: "+lst.size());
//        lst.insertList(insertLst, lst.size());
//        System.out.println("After insertion:"+lst+"The length of the LinkedList: "+lst.size());
//        lst.insertList(insertLst, lst.size()+1 ); //provided index position exceed the size
//        System.out.println("After insertion:"+lst+"The length of the LinkedList: "+lst.size());
        lst.insertList(insertLst, 4);
        System.out.println("After insertion:"+lst+"The length of the LinkedList: "+lst.size());
        System.out.println(insertLst+"The length of the insert list: "+insertLst.size());


//        java.util.Iterator<Integer> itr = lst.iterator( );
//        while( itr.hasNext( ) )
//        {
//                itr.next( );
//                itr.remove( );
//                System.out.println( lst );
//        }
        }


}

//class TestLinkedList
//{
//    public static void main( String [ ] args )
//    {
//        MyLinkedList<Integer> lst = new MyLinkedList<>( );
//
//        for( int i = 0; i < 10; i++ )
//                lst.add( i );
//        for( int i = 20; i < 30; i++ )
//                lst.add( 0, i );
//
//        lst.remove( 0 );
//        lst.remove( lst.size( ) - 1 );
//
//        System.out.println( lst );
//
//        java.util.Iterator<Integer> itr = lst.iterator( );
//        while( itr.hasNext( ) )
//        {
//                itr.next( );
//                itr.remove( );
//                System.out.println( lst );
//        }
//    }
//}