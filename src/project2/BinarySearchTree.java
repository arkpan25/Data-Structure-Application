package project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Fangzhou Pan
 * Grade 98/100
 * TA comments equals & isMirror; -2 should use .equals or .compareTo instead of == (already fixed)
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }
    /**
     * A full tree has every node as either a leaf or a parent with two children.
     * @return true if the tree is full
     */
    public boolean isFull(){
    	if( isEmpty( ) )
            throw new UnderflowException( );
    	return isFull(root);
    }
    /**
     * Compares the structure of current tree to another tree and returns true if they match.
     * @param other another tree to compare with.
     * @return true if two tree share the same structure.
     */

    public boolean compareStructure(BinarySearchTree<AnyType> other)
    {
        if(this.root == null && other.root == null)
            return true;
        else
            return compareStructure(this.root, other.root);
    }
    /**
     * Compares the current tree to another tree and returns true if they are identical.
     * @param other another tree to compare with
     * @return true if two trees are identical
     */
    public boolean equals(BinarySearchTree<AnyType> other){
    	//if(!compareStructure(other)) return false;
    	if(this.root == null && other.root == null) return true;
    	else return equals(this.root,other.root);
    }
    /**
     * Creates and returns a new tree that is a copy of the original tree.
     * @return the copy of original tree.
     */
    public BinarySearchTree<AnyType> copy(){
    	BinarySearchTree<AnyType> clone = new BinarySearchTree<AnyType>();
    	clone.root = copy(root);
		return clone;
    }
    /**
     * Creates and returns a new tree that is a mirror image of the original tree.
     * @return the mirror of the orignal tree.
     */
    public BinarySearchTree<AnyType> mirror(){
    	BinarySearchTree<AnyType> mt = new BinarySearchTree<AnyType>();
    	mt.root = mirror(root);
    	return mt;
    }
    /**
     * check whether another tree is the mirror of current tree.`
     * @param other another BinarySearchTree to check with
     * @return true if the tree is a mirror of the passed tree.
     */
    public boolean isMirror(BinarySearchTree<AnyType> other){
    	if(root == null && other.root == null)
            return true;
        else
            return isMirror(root, other.root);
    }


    /**
     * Performs a single right rotation on the node having the passed value.
     * @param element the passed value in the node
     */
    public void rotateRight(AnyType element){
    	if( isEmpty( ) )
            throw new UnderflowException( );
    	if (!contains(element,root))
    		throw new NullPointerException("Nodes with the passed value does not exist in the tree"
    				                       + " The rotate can not be performed.");
    	ArrayList<BinaryNode<AnyType>> trace = findTrace(element);
    	int traceLen = trace.size();
    	BinaryNode<AnyType> rotateNode = trace.get(traceLen-1);
    	BinaryNode<AnyType> k2 = rotateNode.left;
    	if (k2 == null)
    		throw new NullPointerException("Nodes with the passed value does not have the left child"
    				                      +" The rotate can not be performed.");
    	rotateNode.left = k2.right;
    	k2.right = rotateNode;
		if (traceLen>1){
			BinaryNode<AnyType> parent = trace.get(traceLen-2);
			int compareResult = element.compareTo( parent.element );
			if( compareResult < 0 ) parent.left = k2;
    		else  parent.right = k2;
		}
		else root = k2;
    }
    /**
     * Performs a single left rotation on the node having the passed value.
     * @param element
     */
    public void rotateLeft(AnyType element){
    	if( isEmpty( ) )
            throw new UnderflowException( );
    	if (!contains(element,root))
    		throw new NullPointerException("Nodes with the passed value does not exist in the tree"
                    + " The rotate can not be performed.");
    	ArrayList<BinaryNode<AnyType>> trace = findTrace(element);
    	int traceLen = trace.size();
    	BinaryNode<AnyType> rotateNode = trace.get(traceLen-1);
    	BinaryNode<AnyType> k2 = rotateNode.right;
    	if (k2 == null)
    		throw new NullPointerException("Nodes with the passed value does not have the right child"
                    +" The rotate can not be performed.");
    	rotateNode.right = k2.left;
    	k2.left = rotateNode;
    	if (traceLen>1){
			BinaryNode<AnyType> parent = trace.get(traceLen-2);
			int compareResult = element.compareTo( parent.element );
			if( compareResult < 0 ) parent.left = k2;
    		else  parent.right = k2;
		}
		else root = k2;
    }

    private ArrayList<BinaryNode<AnyType>> findTrace(AnyType element){
    	BinaryNode<AnyType> curr = root;
    	ArrayList<BinaryNode<AnyType>> trace = new ArrayList<BinaryNode<AnyType>>();
    	while(curr != null){
    		trace.add(curr);
    		int compareResult = element.compareTo( curr.element );
    		if( compareResult < 0 ) curr = curr.left;
    		else if( compareResult > 0 ) curr = curr.right;
    		else break;
    	}
    	return trace;
    }

    /**
     * performs a level-by-level printing of the tree.
     */
    public void printLevels( )
    {
    	Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
    	queue.add(root);
    	while(queue.size()>0)
    	{
    		int numPerLevel =queue.size();
    		for (int i=0; i<numPerLevel; i++){
    			BinaryNode<AnyType> currNode = queue.poll();
        		System.out.print(currNode.element+ " ");
        		if(currNode.left != null)
        			queue.add(currNode.left);
        		if(currNode.right != null)
        			queue.add(currNode.right);
    		}
    		System.out.print("\n");
    	}

    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    /**
     * Recursively traverses the tree and returns the count of nodes.
     * @return the number of total nodes
     */
    public int nodeCount(){
    	 if( isEmpty( ) )
             return 0;
         else
             return nodeCount(root);
    }
    /**
     * Internal method to count total tree nodes
     * @param t the node that roots the subtree.
     * @return the total number of tree nodes
     */
    private int nodeCount(BinaryNode<AnyType> t){
    	if ( t == null)
    		return 0;
    	else
    		return 1+nodeCount(t.left)+nodeCount(t.right);

    }
    /**
     * Internal method to check whether the tree is full
     * @param t the node that roots the subtree
     * @return true if the tree is full false if not
     */
    private boolean isFull(BinaryNode<AnyType> t){
    	if ( t.left == null && t.right == null){
    		return true;
    	}
    	else if (t.left == null || t.right == null){
    		return false;
    	}
    	else return isFull(t.left) && isFull(t.right);
    }
    /**
     * Internal method to compare the structure of current tree with another
     * @param t1 the node that roots the subtree
     * @param t2 the node that roots the subtree
     * @return return true if two trees share the same structure otherwise false.
     */
    private boolean compareStructure(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
    	if (t1 == null && t2 == null) return true;
    	else if (t1 != null && t2 != null)
    		return compareStructure(t1.left,t2.left) && compareStructure(t1.right,t2.right);
    	else return false;
    }
    /**
     * Internal method to check whether two trees are identical
     * @param t1 the node that roots the subtree
     * @param t2 the node that roots the subtree
     * @return true if trees are identical otherwise false.
     */
    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
    	if (t1 == null && t2 == null) return true;
    	else if(t1 != null && t2 != null){
    		if (t1.element.equals(t2.element))
    			return equals(t1.left,t2.left) && equals(t1.right,t2.right);
    		else return false;
    	}
    	else return false;
    }

    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t){
    	if (t == null) return null;
    	BinaryNode<AnyType> lt = copy(t.left);
    	BinaryNode<AnyType> rt = copy(t.right);
    	return new BinaryNode<AnyType>(t.element,lt,rt);

    }

    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t){
    	if (t == null) return null;
    	BinaryNode<AnyType> lt = mirror(t.left);
    	BinaryNode<AnyType> rt = mirror(t.right);
    	return new BinaryNode<AnyType>(t.element,rt,lt);
    }

    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2){
    	if (t1 == null && t2 == null) return true;
    	else if (t1 != null && t2 != null)
    		return t1.element.equals(t2.element) && isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    	else return false;
    }


    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else;
            // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );
        System.out.println("The number of nodes in the tree: "+t.nodeCount()); // the result should be 0
        //System.out.println(t.isFull()); // throw out an error
        final int NUMS = 20;
        for (int i=0; i<NUMS; i++){
        	t.insert(i);
        	//System.out.println(t.nodeCount());
        	//System.out.println(t.isFull());
        }
        System.out.println("The number of nodes in the tree: "+t.nodeCount()); //The result should be 20
        System.out.println("The tree is full: "+ t.isFull());    //The result should be false
        List<Integer> nums = Arrays.asList(15,12,20,18,25,13,9);
        BinarySearchTree<Integer> fullTree = new BinarySearchTree<>( );
        for (int x: nums){
        	fullTree.insert(x);
        	System.out.println("The tree is full: "+fullTree.isFull());
        }
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
        System.out.println("Tree t1 and t2 share the same structure: "+t1.compareStructure(t2)); // should output true
        for (int x: nums){
        	t1.insert(x);
        	t2.insert(x);
        }
        System.out.println("Tree t1 and t2 share the same structure: "+t1.compareStructure(t2)); // should output true because t1 and t2 match
        System.out.println("t1 and t2 are identical: "+ t1.equals(t2));// should output true
        t1.insert(33);
        System.out.println(t1.compareStructure(t2)); // should output false because t1 and t2 do not match
        System.out.println("t1 and t2 are identical: "+ t1.equals(t2));// should output false
        t2.insert(35);
        System.out.println("t1 and t2 are identical: "+ t1.equals(t2));// should output false
        BinarySearchTree<Integer> t3 = t2.copy();
        System.out.println("t2 and t3 are identical: "+ t2.equals(t3));// should output true
        System.out.println("t2 and t3 are one tree: "+ (t2 == t3));// should output false
        BinarySearchTree<Integer> mt2 = t2.mirror();   //Tree mt2 is the mirror image of t2.
        System.out.println("mt2 is the mirror of t1: "+t2.isMirror(mt2)); // should output true
        System.out.println("t3 is a mirror of t2: "+t2.isMirror(t3));     // should output false
        //t1.printLevels();
        // test for printLevel method
        t2.printLevels();
        System.out.print('\n');
        mt2.printLevels();
        t2.insert(37);
        t2.insert(40);
        System.out.print('\n');
        t2.printLevels();
        System.out.print('\n');
        // test for rotateRight method
        t2.rotateRight(12);
        t2.printLevels();
        System.out.print('\n');
        //t2.rotateRight(18); // throw out exception node rotate can not be performed.
        t2.rotateRight(15);
        t2.printLevels();
        System.out.print('\n');
        // test for rotateLeft method
        t.makeEmpty();
        t1.makeEmpty();
        int []array = {28,16,13,20,11,19,26,30};
        for( int i = 0; i < array.length; i++ )
        {
            t.insert( array[i] );
            t1.insert( array[i] );

        }
        t.rotateLeft(28);
        t.printLevels();
        System.out.print('\n');
        t1.rotateLeft(20);
        t1.printLevels();


        /*
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            t.insert( i );

        for( int i = 1; i < NUMS; i+= 2 )
            t.remove( i );

        if( NUMS < 40 )
            t.printTree( );
        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
            System.out.println( "FindMin or FindMax error!" );

        for( int i = 2; i < NUMS; i+=2 )
             if( !t.contains( i ) )
                 System.out.println( "Find error1!" );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( t.contains( i ) )
                System.out.println( "Find error2!" );
        }*/
    }
}
