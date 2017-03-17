/***************************************************************************************\
 *                                                                                     *
 *  FILENAME:                       RedBlackTree.java                                  *
 *                                                                                     *
 *  NAME:     Chris Rogers                                                             *
 *  CLASS:    SE3345 Data Structures and Introduction to Algorithmic Analysis          *
 *  SECTION:  003                                                                      *
 *  SEMESTER: Spring 2017                                                              *
 *                                                                                     *
 *  PROJECT4                                                                           *
 *                                                                                     *
 *  DESCRIPTION:                                                                       *
 *    Red-black tree with no deletion.                                                 *
 *    Methods: insert, contains, printTree                                             *
 *                                                                                     *
\***************************************************************************************/

package project4;

public class RedBlackTree<AnyType extends Comparable<? super AnyType>>{

	private RedBlackNode<AnyType> header;
	private RedBlackNode<AnyType> nullNode;

	// Used in insert routine and its helpers
	private RedBlackNode<AnyType> current;
	private RedBlackNode<AnyType> parent;
	private RedBlackNode<AnyType> grand;
	private RedBlackNode<AnyType> great;

	private static final int BLACK = 1;    // BLACK must be 1
	private static final int RED = 0;

	/**
	 *  Construct the tree
	 */
	public RedBlackTree(){
		nullNode = new RedBlackNode<>(null);          // create null node with null children
		nullNode.left = nullNode.right = nullNode;
		header = new RedBlackNode<>(null);            // create null header node
		header.left = header.right = nullNode;        // create null children nodes
	}

	/**
	*  Nodes for tree
	*/
	private static class RedBlackNode<AnyType>{
		// Constructors
		RedBlackNode(AnyType theElement){
			this(theElement, null, null);
		}
		RedBlackNode(AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt){
			element = theElement; left = lt; right = rt; color = RedBlackTree.BLACK;
		}
		
		AnyType element;              // The data in the node
		RedBlackNode<AnyType> left;   // left child
		RedBlackNode<AnyType> right;  // right child
		int color;                    // color
	}

	/**
	*  Insert into the tree.
	*  @param item the item to insert
	*/
	public void insert(AnyType item){
		current = parent = grand = header;                                  // current, parent, grand set equal to header
		nullNode.element = item;                                            // set element of a null node to the item being inserted

		while(compare(item, current) != 0){                                 // while the item and current nodes are not equal
			great = grand;                                                  // move down one level
			grand = parent;
			parent = current;
			current = compare(item, current) < 0 ? current.left : current.right;

			if(current.left.color == RED && current.right.color == RED){    // Check if two red children; fix if so
				handleReorient(item);
			}
		}
		if(current != nullNode){                                            // Insertion fails if duplicate      
			return;
		}
		current = new RedBlackNode<>(item, nullNode, nullNode);             // create node with element and null children
		if(compare(item, parent) < 0){                                      // Attach to parent
			parent.left = current;
		}
		else{
			parent.right = current;
		}
		handleReorient(item);                                               // Check if two red children; fix if so
		System.out.println("Element inserted");                             // Message displayed to confirm element inserted
	}

	/**
	*  Internal routine that is called during an insertion
	*  if a node has two red children. Performs flip and rotations.
	*  @param item the item being inserted.
	*/
	private void handleReorient(AnyType item){
		current.color = RED;                   //Do the color flip
		current.left.color = BLACK;
		current.right.color = BLACK;

		if(parent.color == RED){               // Have to rotate
			grand.color = RED;
			if((compare(item, grand) < 0) !=
				(compare(item, parent) < 0))
				parent = rotate(item, grand);  // Start dbl rotate
			current = rotate(item, great);
			current.color = BLACK;
		}
		header.right.color = BLACK;            // Make root black
	}

	/**
	*  Internal routine that performs a single or double rotation.
	*  Because the result is attached to the parent, there are four cases.
	*  Called by handleReorient.
	*  @param item the item in handleReorient.
	*  @param parent the parent of the root of the rotated subtree.
	*  @return the root of the rotated subtree.
	*/
	private RedBlackNode<AnyType> rotate(AnyType item, RedBlackNode<AnyType> parent){
		if(compare(item, parent) < 0){                              // if the item is less than parent
			return parent.left = compare(item, parent.left) < 0 ?   // do LL or LR rotation
				rotateWithLeftChild(parent.left) :  // LL
				rotateWithRightChild(parent.left) ; // LR
		}
		else{                                                       // else the item is greater
			return parent.right = compare(item, parent.right) < 0 ? // do a RL or RR rotation
				rotateWithLeftChild(parent.right) :  // RL
				rotateWithRightChild(parent.right) ; // RR
		}
	}

	/**
	*  Compare item and t.element, using compareTo, with
	*  caveat that if t is header, then item is always larger.
	*  This routine is called if it is possible that t is header.
	*  If it is not possible for t to be header, use compareTo directly.
	*/
	private final int compare(AnyType item, RedBlackNode<AnyType> t){
		if(t == header){
			return 1;
		}
		else{
			return item.compareTo(t.element);
		}
	}

	/**
	*  Rotate binary tree node with left child
	*  For RedBlack trees, this is a single rotation for case ___
	*/
	private RedBlackNode<AnyType> rotateWithLeftChild(RedBlackNode<AnyType> k2){
		RedBlackNode<AnyType> k1 = k2.left;  // set k1 as left child of k2
		k2.left = k1.right;                  // k2 left child becomes k1 right child
		k1.right = k2;                       // k1 right child becomes k2
		return k1;                           // return k1
	}

	/**
	*  Rotate binary tree node with right child
	*  For RedBlack trees, this is a single rotation for case ___
	*/
	private RedBlackNode<AnyType> rotateWithRightChild(RedBlackNode<AnyType> k2){
		RedBlackNode<AnyType> k1 = k2.right; // set k1 as right child of k2
		k2.right = k1.left;                  // k2 right child becomes k1 left child
		k1.left = k2;                        // k1 left child becomes k2
		return k1;                           // return k1
	}

	/**
	*  Print the tree contents in sorted order.
	*/
	public void printTree(){
		if(isEmpty()){
			System.out.print("Empty Tree");
		}
		else{
			printTree(header.right);
		}
		System.out.println();
	}

	/**
	*  Internal method to print a subtree in sorted order.
	*  @param t the node that roots the subtree.
	*/
	private void printTree(RedBlackNode<AnyType> t){
		if(t != nullNode){
			printTree(t.left);                  // recursively search left
			if(t.color == RED){                 // if the color is red, print *
				System.out.print("*");
			}
			System.out.print(t.element + " ");  // print the element
			printTree(t.right);                 // recursively search right
		}
	}

	/**
	*  Search for element in tree.
	*  @param e the element to search for
	*  @return true if the element is found, false otherwise
	*/
	public boolean contains(AnyType e){
		boolean found = false;
		if(isEmpty()){
			System.out.println("Empty Tree");
		}
		else{
			found = contains(header.right, e);
		}
		return found;
	}

	/**
	*  Internal method to search for element in tree.
	*  @param t the node that roots the subtree.
	*  @param e the element to search for
	*  @return true if the element is found, false otherwise
	*/
	private boolean contains(RedBlackNode<AnyType> t, AnyType e){
		if(t != nullNode){
			if(contains(t.left, e) == true){    // recursively search left
				return true;
			}
			if(t.element == e){                 // if found, return true
				return true;
			}
			if(contains(t.right, e) == true){   // recursively search right
				return true;
			}
		}
		return false;                           // if item not found, return false
	}

	/*
	*  Indicates if tree is empty
	*  @return true if tree is empty, false otherwise.
	*/
	public boolean isEmpty(){
		return header.right.element == null;
	}

}