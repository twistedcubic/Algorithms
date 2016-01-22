// quicksort
// O(n log n) time complexity
// Sorts in-place, but needs O(n) space 
// for input, and O(n/2) stack space for
// the non-tail-recursion call

// sort in ascending order
public class QuickSort {

	public int[] list;
	public QuickSort(int[] list){
		this.list = list;
	}
	
	public void quick_sort( int left, int right ){
		int pivot = list[(left+right)/2];
		int left_index = left;
		int right_index = right;
		
		while(left_index < right_index){
			
			if(list[right_index] > pivot){
				right_index--;
			}
			if(list[left_index] <= pivot){
				left_index++;
				
			}else if(list[right_index] < pivot){
				int temp = list[left_index];
				list[left_index] = list[right_index];
				list[right_index] = temp;
				left_index++;
				right_index--;
			}

		}
		quick_sort(left,left_index-1);
		quick_sort(left_index+1,right);
	}
	
}

/* Of course this would be less code in Python:
 *
 * def quicksort(list):
 *   if len(list) <= 1:
 *     return list
 *   pivot = list[len(list)/2]
 *   left = [x for x in list if x < pivot]
 *   right = [x for x in list if x > pivot]
 *   mid = [x for x in list if x == pivot]
 *   return quicksort(left) + mid + quicksort(right)
 *
 */
