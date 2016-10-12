/*
 * Ian Leeds
 * ileeds@brandeis.edu
 * Store key-value pairs with Strings as keys and Objects as values
 * Use chaining to handle collisions
 * Instead of using a linked list as buckets, uses BinarySearchTree
 */
public class HashTable {

	BinarySearchTree[]table;
	int size=0;
	
	//creates a hash table whose array length is equal to size
	public HashTable(int size){
		table=new BinarySearchTree[size];
	}
	
	//Perform some hash function on string and return an int
	public int hash(String string){
		int hashVal=0;
		for (int i=0; i<string.length(); i++){
			hashVal+=string.charAt(i)*(i+1);
		}
		return hashVal%table.length;
	}
	
	//Perform the hash function on key, and add a record containing key
	//and value at the position in the array indicated by the result of
	//the hash. Return true if the pair is successfully added and false
	//if it is not
	public boolean put(String key, Object value) throws Exception{
		int hashVal=hash(key);
		if (table[hashVal]==null){
			table[hashVal]=new BinarySearchTree(key,value);
		}else{
			table[hashVal]=table[hashVal].insert(key, value);
		}
		size++;
		return true;
	}
	
	//Returns the Object that is associated with key, and null if none
	//exists
	public Object get(String key){
		int hashVal=hash(key);
		if (table[hashVal]==null || table[hashVal].findNode(key)==null){
			return null;
		}
		return table[hashVal].findNode(key).value;
	}
	
	//Returns true if the table is currently storing a record with key
	//as its key, and false if not
	public boolean hasKey(String key){
		int hashVal=hash(key);
		if (table[hashVal].findNode(key)!=null && table[hashVal].findNode(key).key.equals(key)){
			return true;
		}
		return false;
	}
	
	//Removes the key-value pair associated with key from the table
	public void removeKey(String key){
		if (hasKey(key)){
			int hashVal=hash(key);
			table[hashVal].delete(key);
			size--;
		}
	}
	
	//Returns an int, the total number of key – value entries currently
	//in the hash table
	public int size(){
		return size;
	}
	
	//Returns an int[ ] whose length is the same as the length of the
	//hash table. The value of the nth cell in this array should be
	//equal to the number of entries stored in the bucket corresponding
	//to the nth position in the hash table
	public int[] distribution(){
		int[]dist=new int[table.length];
		for (int i=0; i<dist.length; i++){
			if (table[i]!=null){
				dist[i]=table[i].size();
			}else{
				dist[i]=0;
			}
		}
		return dist;
	}
	
	//Return a String[ ] containing all Strings that are keys currently
	//associated with Objects in the hash table
	public String[] keys(){
		String[]keys=new String[size];
		int k=0;
		//traverses table, adding all values in each index/BST to the
		//keys array (when value is added, increment k to go to next
		//index of keys array)
		for (int i=0; i<table.length; i++){
			for (int j=0; j<=table[i].size(); j++){
				keys[k]=table[i].returnKey(j);
				k++;
			}
		}
		return keys;
	}
	
}
