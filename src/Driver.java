import java.util.Random;

/*
 * Ian Leeds
 * ileeds@brandeis.edu
 * Helper method for BinarySearchTree and HashTable
 */
public class Driver {
	public static void main (String[]args) throws Exception{
		/*BinarySearchTree c=new BinarySearchTree("c", 1);
		//c=c.addRoot("b", 1);
		BinarySearchTree e=new BinarySearchTree();
		//System.out.println(e.isRoot());
		System.out.println(e.isLeftChild());
		System.out.println(e.isRightChild());
		System.out.println(e.hasParent());
		System.out.println(e.isEmpty());
		System.out.println(e.findMin());
		System.out.println(e.findSuccessor("a"));
		System.out.println(e.size());
		System.out.println(e.balanceFactor());
		System.out.println(e.height());
		//System.out.println(e.depth());
		e=e.addRoot("a", 3);
		System.out.println(e.isEmpty());
		System.out.println(e.isLeaf());
		//e=e.addRoot("b", 1);
		System.out.println(e.hasLeft()+""+e.hasRight());
		e=e.insert("b", new BinarySearchTree());
		System.out.println(e.hasLeft()+""+e.hasRight());
		e=e.insert("c", 21938);
		System.out.println(e.hasLeft()+""+e.hasRight());
		System.out.println(e.isLeaf());
		System.out.println(e.isRoot());
		System.out.println(e.isLeftChild());
		System.out.println(e.isRightChild());
		System.out.println(e.hasParent());
		System.out.println(e.findNode("a"));
		System.out.println(e.findNode("b"));
		System.out.println(e.findNode("c"));
		System.out.println(e.findNode("d")) ;
		System.out.println(e.findMin());
		System.out.println(e.findSuccessor("a"));
		System.out.println(e.findSuccessor("b"));
		System.out.println(e.findSuccessor("c"));
		e=e.insert("d", 1);
		e=e.insert("e", 1);
		e=e.insert("f", 1);
		e=e.insert("g", 1);
		e=e.insert("h", 4);
		System.out.println(e.findSuccessor("d"));
		System.out.println(e.findSuccessor("e"));
		System.out.println(e.findSuccessor("f"));
		System.out.println(e.findSuccessor("g"));
		System.out.println(e.findSuccessor("h"));
		System.out.println(e.search("a"));
		System.out.println(e.search("h"));
		System.out.println(e.search("b"));
		System.out.println(e.size());
		System.out.println(e.balanceFactor());
		System.out.println(e.height());
		System.out.println(e.depth());
		e.printInOrder();
		System.out.println();
		System.out.println(e.mirrorTree());
		e.printInOrder();*/
		
		
		int hashValues=1000;
		HashTable a=new HashTable(100);
		Random rand=new Random();
		//System.out.println(a.size);
		for (int i=0; i<hashValues; i++){
			String key="";
			for (int j=0; j<rand.nextInt(9)+5; j++){
				key+=(char)(rand.nextInt(26)+97);
 			}
			a.put(key, 1);
		}
		/*System.out.println(a.size);
		a.put("b", "b");
		System.out.println(a.size);
		System.out.println(a.get("a"));
		System.out.println(a.get("b"));
		System.out.println(a.hasKey("a"));
		System.out.println(a.hasKey("b"));
		a.removeKey("a");
		a.removeKey("b");
		System.out.println(a.size);
		System.out.println(a.get("b"));*/
		int [] b = a.distribution();
		for (int i:b){
			//System.out.println(i);
		}
		String [] d = a.keys();
		for (String i:d){
			System.out.println(i);
		}
	}
}