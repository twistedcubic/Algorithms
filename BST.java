/* Binary search tree.
 * 
 */
public class BST<Key extends Comparable<Key>, Val> {
	private Node root;
	
	//Node class
	private class Node{
		private Key key;
		private Val val;
		private Node left, right;
		
		public Node(Key key, Val val){
			this.key = key;
			this.val = val;
		}
	}
	
	//element containment
	public boolean contains(Key key){
		return contains(key, root);
	}
	
	private boolean contains(Key key, Node cur_node){
		if(cur_node == null)
			return false;
		
		if(key.compareTo(cur_node.key) < 0){
			return contains(key, cur_node.left);
		}else if(key.compareTo(cur_node.key) > 0){
			return contains(key, cur_node.right);
		}else
			return true;
	}
	
	//get an element
	public Val get(Key key){		
		return get(key, root);		
	}
	
	public Val get(Key key, Node cur_node){
		if(cur_node == null)
			return null;
		
		if(key.compareTo(cur_node.key) < 0){
			return get(key, cur_node.left);
		}else if(key.compareTo(cur_node.key) > 0){
			return get(key, cur_node.right);
		}else
			return cur_node.val;
	}
	
	//delete min element
	//same logic for delete max
	public void delete_min(){
		if(root == null){
			System.out.println("Tree empty!");
			return;
		}
		root = delete_min(root);
	}

	public Node delete_min(Node cur_node){
		if(cur_node.left == null)
			return cur_node.right;
		cur_node.left = delete_min(cur_node.left);
		return cur_node;
	}


	/* insert key-value pair.
	 * replace if key already in tree 
	 */
	public void insert(Key key, Val val){

		if(key == null){
			System.out.println("Key cannot be null");
			return;
		}
		root = insert(key, val, root);
	}
		
	public Node insert(Key key, Val val, Node cur_node){
		if(cur_node == null){
			Node node = new Node(key, val);
			return node;
		}
		
		if(key.compareTo(cur_node.key) < 0)
			cur_node.left = insert(key, val, cur_node.left);
		if(key.compareTo(cur_node.key) > 0)
			cur_node.right = insert(key, val, cur_node.right);
		else{
			//replace val of cur_node
			cur_node.val = val;
		}
		return cur_node;
	}
	
	//delete key-value pair
	public void delete(Key key){
		if(key == null){
			System.out.print("Key cannot be null!");
			return;
		}
		root = delete(key, root);
	}
		
	public Node delete(Key key, Node cur_node){
		if(cur_node == null){
			System.out.println("Key not found");
			return null;
		}
		
		if(key.compareTo(cur_node.key) < 0)
			cur_node.left = delete(key, cur_node.left);
		if(key.compareTo(cur_node.key) > 0)
			cur_node.right = delete(key, cur_node.right);
		else{
			//reached a leaf
			if(cur_node.right == null)
				return cur_node.left;
			if(cur_node.left == null)
				return cur_node.right;

			//get smallest successor						
			Node temp_node = cur_node;
			cur_node = get_successor(cur_node.right);
			cur_node.left = temp_node.left;
			//delete the immediate successor and set 
			//the right node
			cur_node.right = delete_min(temp_node.right);
			//the old cur_node will get garbage-collected
			
		}
		return cur_node;
	}

	//helper method used in delete 
	private Node get_successor(Node node){
		if(node == null)
			return null;
		if(node.left == null)
			return node;
		return get_successor(node.left);
	}
	
}
