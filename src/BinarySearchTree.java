import java.util.Stack;

/*
 * Ian Leeds
 * ileeds@brandeis.edu
 * AVL Binary Search tree in which each node stores both a key and a value
 * Keys are Strings and Values are Objects
 */
public class BinarySearchTree {

	String key;
	Object value;
	BinarySearchTree parent;
	BinarySearchTree left;
	BinarySearchTree right;
		
	//constructs empty tree
	public BinarySearchTree(){
		
	}
	
	//constructs tree with key and value in root
	public BinarySearchTree(String key, Object value) {
		this.key=key;
		this.value=value;
	}

	//returns true if the tree has a left child
	public boolean hasLeft(){
		if (left==null){
			return false;
		}
		return true;
	}
	
	//returns true if the tree has a right child
	public boolean hasRight(){
		if (right==null){
			return false;
		}
		return true;
	}
	
	//returns true if the tree is a leaf (has no children)
	public boolean isLeaf(){
		if (!hasLeft() && !hasRight()){
			return true;
		}
		return false;
	}
	
	//returns true if the tree is empty (doesn’t have a root)
	public boolean isEmpty(){
		if (key==null){
			return true;
		}
		return false;
	}
	
	//returns true if the tree has no parent
	public boolean isRoot(){
		if (!hasParent()){
			return true;
		}
		return false;
	}
	
	//returns true if the tree is a left child of its parent
	public boolean isLeftChild(){
		if (!hasParent()){
			return false;
		}
		//if parent comes alphabetically after
		if (parent.key.compareTo(key)>0){
			return true;
		}
		return false;
	}
	
	//returns true if the tree is a right child of its parent
	public boolean isRightChild(){
		if (!hasParent()){
			return false;
		}
		//if parent comes alphabetically before
		if (parent.key.compareTo(key)<0){
			return true;
		}
		return false;
	}
	
	//returns true if the tree has a parent
	public boolean hasParent(){
		if (parent==null){
			return false;
		}
		return true;
	}
	
	//returns the node with the given key
	public BinarySearchTree findNode(String key){
		BinarySearchTree current=this;
		if (key==null){
			return null;
		}
		if (current.key.equals(key)){
			return current;
		}
		if (current.key.compareTo(key)>0){
			if (current.left==null){
				return null;
			}
			return current.left.findNode(key);
		}else{
			if (current.right==null){
				return null;
			}
			return current.right.findNode(key);
		}
	}
	
	//returns the node with the minimum value in this tree
	public BinarySearchTree findMin(){
		BinarySearchTree current=this;
		while (current.hasLeft()){
			current=current.left;
		}
		return current;
	}
	
	//returns the node that is the successor to this node in the tree
	public BinarySearchTree findSuccessor(String key){
		BinarySearchTree current=this;
		if (current.hasRight()){
			return current.right.findMin();
		}else{
			if (!current.hasParent()){
				return null;
			}
			BinarySearchTree w=current.parent;
			while (w!=null && current.key.equals(w.right.key)){
				current=w;
				if (!w.hasParent()){
					return w;
				}
				w=w.parent;
			}
			return w;
		}
	}
	
	//add a root to the tree if the tree is empty (throw exception if it
	//isn't)
	public BinarySearchTree addRoot(String key, Object value) throws Exception {
		if (!isEmpty()){
			throw new Exception("Tree is not empty");
		}else{
			this.key=key;
			this.value=value;
		}
		return this;
	}
	
	//Starting from the root, walk down the tree, searching for the
	//correct location for key. If the tree already contains a node with
	//key as its key, throw an exception. Otherwise, insert a node into
	//the tree with key and value as its key and value. Preserves the
	//balanced binary search tree property
	public BinarySearchTree insert(String key, Object value) throws Exception{
		BinarySearchTree z=new BinarySearchTree(key,value);
		BinarySearchTree v=null;
		BinarySearchTree w=this;
		while (w!=null){
			v=w;
			if (z.key.compareTo(w.key)==0){
				throw new Exception("Tree already contains node with this key");
			}else if (z.key.compareTo(w.key)<0){
				if (!w.hasLeft()){
					w=null;
				}else{
					w=w.left;
				}
			}else{
				if (!w.hasRight()){
					w=null;
				}else{
					w=w.right;
				}
			}
		}
		z.parent=v;
		if (v==null){
			addRoot(z.key, z.value);
		}else{
			if (z.key.compareTo(v.key)<0){
				v.left=z;
			}else{
				v.right=z;
			}
		}
		z.balance();
		while (z.hasParent()){
			z=z.parent;
			z.balance();
		}
		return z;
	}
	
	//returns the Object stored in the node with key as its key, and
	//returns null if the tree does not have a node with that key
	public Object search(String key){
		return findNode(key).value;
	}
	
	//removes the node containing key from the tree. Throws an exception
	//if there is no node with key
	public void delete(String key){
		BinarySearchTree root=this;
		BinarySearchTree x;
		BinarySearchTree y;
		BinarySearchTree z=findNode(key);
		if (!z.hasLeft() || !z.hasRight()){
			y=z;
		}else{
			y=findSuccessor(z.key);
		}
		if (y.hasLeft()){
			x=y.left;
		}else if (y.hasRight()){
			x=y.right;
		}else{
			x=null;
		}
		if (x!=null){
			if (!y.hasParent()){
				x.parent=null;
			}else{
				x.parent=y.parent;
			}
		}
		if (!y.hasParent()){
			root=x;
		}else{
			if (y.parent.hasLeft() && y.key.equals(y.parent.left.key)){
				y.parent.left=x;
			}else if(y.parent.hasRight() && y.key.equals(y.parent.right.key)){
				y.parent.right=x;
			}else{
				y.parent.right=null;
			}
		}
		if (!y.key.equals(z.key)){
			z.key=y.key;
			z.value=y.value;
		}
	}
	
	//returns an int, the number of nodes currently in the tree
	public int size(){
		BinarySearchTree current=this;
		if (current.hasRight()&&current.hasLeft()){
			return current.right.size()+current.left.size()+1;
		}
		else if (current.hasRight()){
			return current.right.size()+1;
		}else if (current.hasLeft()){
			return current.left.size()+1;
		}else{
			return 1;
		}
	}
	
	//calculates the balance factor of tree (the height of the its left
	//subtree minus the height of its right subtree)
	public int balanceFactor(){
		BinarySearchTree current=this;
		if (current.right==null && current.left==null){
			return 0;
		}else if (!current.hasRight()){
			return current.left.height();
		}else if (!current.hasLeft()){
			return -current.right.height();
		}else{
			return current.left.height()-current.right.height();
		}
	}
	
	//recursive; return the height of the tree
	public int height(){
		BinarySearchTree current=this;
		if (current==null){
			return -1;
		}
		int leftHeight=0;
		int rightHeight=0;
		if (current.hasLeft()){
			leftHeight=current.left.height();
		}
		if (current.hasRight()){
			rightHeight=current.right.height();
		}
		return Math.max(leftHeight, rightHeight)+1;
	}
	
	//recursive; return the depth of the tree
	public int depth(){
		BinarySearchTree v=this;
		if (v.isRoot()){
			return 0;
		}else{
			return 1+v.parent.depth();
		}
	}
	
	//balances this binary search tree using the algorithm described
	//in class
	public void balance(){
		BinarySearchTree current=this;
		if (current.balanceFactor()<-1){
			if (current.right.balanceFactor()>0){
				current.right.rightRotation();
				current.leftRotation();
			}else{
				current.leftRotation();
			}
		}else if (current.balanceFactor()>1){
			if (current.left.balanceFactor()<0){
				current.left.leftRotation();
				current.rightRotation();
			}else{
				current.rightRotation();
			}
		}
	}
	
	//rotates the tree to the right around the given node
	public void rightRotation(){
		BinarySearchTree v=this;
		BinarySearchTree u=null;
		if (v.hasParent()){
			u=v.parent;
		}
		BinarySearchTree w=null;
		if (v.hasLeft()){
			w=v.left;
		}
		BinarySearchTree x=null;
		if (w.hasRight()){
			x=w.right;
		}
		w.right=v;
		v.parent=w;
		v.left=x;
		//
		if (x!=null){
			x.parent=v;
		}
		if (u!=null){
			if (u.hasLeft()){
				if (u.left==v){
					u.left=w;
				}
			}else{
				u.right=w;
			}
		}
		w.parent=u;
	}
	
	//rotates the tree to the left around the given node
	public void leftRotation(){
		BinarySearchTree w=this;
		BinarySearchTree u=null;
		if (w.hasParent()){
			u=w.parent;
		}
		BinarySearchTree v=null;
		if (w.hasRight()){
			v=w.right;
		}
		BinarySearchTree x=null;
		if (v.hasLeft()){
			x=v.left;
		}
		w.right=x;
		//
		if (x!=null){
			x.parent=w;
		}
		v.left=w;
		w.parent=v;
		if (u!=null){
			if (u.hasRight()){
				if (u.right==w){
					u.right=v;
				}
			}else{
				u.left=v;
			}
		}
		v.parent=u;
	}
	
	//recursive; return a new tree which looks like the mirror image of
	//the given tree
	public BinarySearchTree mirrorTree(){
		BinarySearchTree current=this;
		BinarySearchTree temp=this;
		if (current.isLeaf()){
			return null;
		}
		if (current.hasLeft() && current.hasRight()){
			temp=current.right;
			current.right=current.left;
			current.left=temp;
		}else if (current.hasLeft()){
			current.right=current.left;
			current.left=null;
		}else{
			current.left=current.right;
			current.right=null;
		}
		if (current.hasLeft()){
			current.left.mirrorTree();
		}
		if (current.hasRight()){
			current.right.mirrorTree();
		}
		return current;
	}
	
	//prints the tree using an inorder traversal
	public void printInOrder(){
		BinarySearchTree current=this;
		if (current.hasLeft()){
			current.left.printInOrder();
		}
		System.out.print(current+" ");
		if (current.hasRight()){
			current.right.printInOrder();
		}
	}
	
	//prints just key for testing purposes
	public String toString(){
		return key;
	}
	
	//returns key, indicated by given number, in tree (for HashTable)
	public String returnKey(int key){
		BinarySearchTree current=this;
		Stack <BinarySearchTree> a=new Stack <BinarySearchTree> ();
		if (current.key!=null){
			a.push(current);
		}
		while (!a.isEmpty()){
			BinarySearchTree bst=a.pop();
			if (key==0){
				return bst.key;
			}
			if (bst.left!=null){
				a.push(bst.left);
			}
			if (bst.right!=null){
				a.push(bst.right);
			}
			key--;
		}
		return current.key;
	}
}
