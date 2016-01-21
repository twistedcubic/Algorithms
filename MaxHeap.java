import java.util.ArrayList;

/* Max heap
 * Aray implementation. Indexing starts at 1,
 * so children have indices 2i, 2i+1; parent
 * has index i/2 (automatically rounded down)
 */
public class MaxHeap {

	// use list for now so not worrying about
	// manually growing capacity
	ArrayList<Integer> list;
	
	public MaxHeap(ArrayList<Integer> list){
		this.list = heapify(list);
		
	}
	
	/* Instead of building by successive insertions, 
	 * which takes O(n log n) time, we take the list
	 * and move elements down if needed.
	 * This takes O(n) time by convergence of geometric series
	 * sum_i i/(2^i)
	 */
	public ArrayList<Integer> heapify(ArrayList<Integer> list){		
		// padding out 0th index
		list.add(0, -1);
		int sz = list.size();
		// no work done on last row
		for(int i = 1; 2*i < sz - 1; i++){
			int larger_child_indx = list.get(i*2) > list.get(i*2+1) ?
					2*i : 2*i + 1;
			if(list.get(i) < list.get(larger_child_indx)){
				int temp = list.get(i);
				list.set(i, list.get(larger_child_indx));
				list.set(larger_child_indx, temp);
			}			
		}
		
		return list;		
	}
	
	/* Insert a new value into the heap
	 * 
	 */
	public void insert(int new_val){
		//insert at base, heapify up
		list.add(new_val);
		int parent_index = list.size()/2; 
		int child_index = list.size(); 
		
		while(list.get(parent_index) < new_val){
			int temp = list.get(parent_index);
			list.set(parent_index, list.get(child_index));
			list.set(child_index, temp);
			child_index = parent_index;
			parent_index = parent_index/2;
			// reached root
			if(parent_index == 0)
				break;			
		}		
	}
	
	// returns root, maintain heap property
	public int get_max(){
		int root = list.get(1);
		// promote the larger of the two 
		// child nodes
		int parent_indx = 1;
		// -1 due to extra padding at front
		while(parent_indx*2 < list.size() - 1){
			// elevate the larger child
			int child1 = list.get(parent_indx*2);
			int child2 = list.get(parent_indx*2+1);
			if(child1 > child2){
				list.set(parent_indx, child1);
				parent_indx = parent_indx*2;
			}else{
				list.set(parent_indx, child2);
				parent_indx = parent_indx*2+1;
			}
						
		}
		
		return root;
	}
	
}
