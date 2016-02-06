/* Hashtable 
 * 
 */
public class HashTable<Key, Val> {

	//linked list of nodes
	private Node<Key,Val>[] lists;
	//sizes of esach list in lists
	private int[] sizes;
	//size of hashtable
	private int tableSz;
	
	//private class for nodes in linked list
	private static class Node<Key2, Val2>{
		private Key2 key;
		private Val2 val;
		private Node<Key2, Val2> next;
		
		public Node(Key2 key, Val2 val, Node<Key2, Val2> next){
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	//@param cap is initial size of hash table
	public HashTable(int cap){
		this.lists = (Node<Key,Val>[]) new Node[cap];
		//initialized to 0 by default
		this.sizes = new int[cap];
		this.tableSz = cap;
	}
	
	public void insert(Key key, Val val){
		int hs = hash(key);
		
		if(sizes[hs] > 0){
			Node<Key, Val> tempNode = lists[hs];
			Node<Key, Val> newNode = new Node<Key, Val>(key, val, tempNode);
			lists[hs] = newNode;
		}else{
			Node<Key,Val> newNode = new Node<Key,Val>(key, val, null);
			lists[hs] = newNode;		
		}
		sizes[hs]++;
		
		//resize if load too large
		//to minimize linear search time
		if(size() > 5*sizes.length){
			resize(2*sizes.length);
		}
	}
	
	//delete key-value pair
	public void delete(Key key){
		int hs = hash(key);
		if(sizes[hs] == 0){
			System.out.print("Key not found");
			return;
		}
		Node<Key,Val> curNode = lists[hs];
		Node<Key,Val> prevNode = lists[hs];
		int counter = 0;
		Boolean found = false;
		
		while(curNode != null){
			
			if(curNode.key.equals(key)){
				found = true;
				
				if(counter == 0){
					lists[hs] = lists[hs].next;
				}else{
					prevNode.next = curNode.next;
				}
				sizes[hash(key)]--;
				break;
			}			
			counter++;
			prevNode = curNode; 
			curNode = curNode.next;
		}
		if(!found)
			System.out.println("Key not found");
		
		//half the table size if load
		//becomes small
		if(size() < sizes.length){
			resize(sizes.length/2);
		}
	}
	
	//resize list according to @param newSz
	private void resize(int newSz){
		
		HashTable<Key, Val> newTable = new HashTable<Key, Val>(newSz);
		
		for(int i = 0; i < lists.length; i++){
			for(Node<Key, Val> curNode = lists[i]; curNode != null;
			curNode = curNode.next){
				newTable.insert(curNode.key, curNode.val);
			}
		}
		this.lists = newTable.lists;
		this.sizes = newTable.sizes;
		this.tableSz = newTable.tableSz;
	}
		
	//retrieves size
	public int size(){
		int sz = 0;
		for(int i = 0; i < sizes.length; i++){
			sz += sizes[i];
		}
		return sz;
	}
	
	//determines if table empty
	public boolean isEmpty(){
		
		return size() == 0;
	}
	
	//hash function
	private int hash(Key key){
		//bitwise & with 0x7fffffff clears
		//out sign bit
		return key.hashCode() & 0x7fffffff %
				tableSz;
	}
	
}
