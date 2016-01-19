import java.util.ArrayList;

// merge sort: O(n log n ) time, O(n) space 
// sort in descending order
public class MergeSort {

	public ArrayList<Integer> merge_sort(ArrayList<Integer> list){
		if(list.size() <= 1)
			return list;
		//divide up into two lists (shallow copy)
		int sz = list.size();
		ArrayList<Integer> list1 = new ArrayList<Integer>(list.subList(0, sz/2));
		ArrayList<Integer> list2 = new ArrayList<Integer>(list.subList(sz/2, sz));
		
		//recursively divide up / sort list1 and list2
		ArrayList<Integer> listA = merge_sort(list1);
		ArrayList<Integer> listB = merge_sort(list2);
		
		return merge(listA, listB);
	}
	
	public ArrayList<Integer> merge(ArrayList<Integer> list1, 
			ArrayList<Integer> list2){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(list1.size() != 0 && list2.size() != 0){
			ArrayList<Integer> temp_list = list1.get(0) > list2.get(0) ? 
					list1 : list2;			
			list.add(temp_list.get(0));
			temp_list.remove(0);
		}
		
		//now only one list is nonempty, and is sorted
		if(list1.size() != 0){
			list.addAll(list1);
		}else{
			list.addAll(list2);
		}
		
		return list;
	}
			
}
