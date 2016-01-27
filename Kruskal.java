import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;

/* Kruskal's algorithm for finding
 * minimum spanning tree (MST) using 
 * union find.
 * Time complexity E log(E) and 
 * space complexity E
 */

public class Kruskal {
	//head of each element, may not be root
	private int[] head;
	//size of component rooted at i
	//doesn't mean much if not i is 
	//not a root
	private int[] size;
	Set<Edge> edges;
	//min priority queue to store all edges
	PriorityQueue<Edge> queue;
	ArrayList<Edge> mst;
	
	public Kruskal(Set<Edge> edges){
		this.edges = edges;
		WeightedGraph graph = new WeightedGraph(edges);
		int num_vx = graph.num_vx();
		
		head = new int[num_vx];
		size = new int[num_vx];
		for(int i = 0; i < num_vx; i++){
			head[i] = i;
			size[i] = 1;
		}
		
		queue = new PriorityQueue<Edge>();
		mst = new ArrayList<Edge>();
	}
	
	//find root (not just head) of component
	//containing v
	private int find(int v){
		
		while(v != head[v]){
			v = head[v];			
		}
		return v;
	}
	
	/*Combine the components containing v, w
	 * according to their component sizes
	 */
	private void union(int v, int w){
		int v_root = find(v);
		int w_root = find(w);
		//checked earlier that v_root is different from w_root
		
		//tag the smaller component onto the bigger one
		//for faster future find
		if(size[v_root] > size[w_root]){
			head[w_root] = v_root;
			size[v_root] += size[w_root];
		}else{
			head[v_root] = w_root;
			size[w_root] += size[v_root];
		}
	}
	
	/*runs Kruskal's algorithm.
	 * Greedily takes the next min weight edge
	 */
	public ArrayList<Edge> get_mst(){
		//MST should have 1 less element than
		//number of vertices, which is head.length
		while(mst.size() < head.length-1){
			Edge min_edge = queue.poll();
			if(min_edge == null){
				System.out.println("Too few edges, maybe graph "
						+ "not connected");
				return null;
			}
			
			int[] e_vx = min_edge.get_vertices();
			
			//min_edge forms a loop if its two vertices
			//belong to the same component
			if(find(e_vx[0]) == find(e_vx[1])){
				continue;
			}
			
			//add to MST if no loop
			mst.add(min_edge);
			
			//join the two components
			union(e_vx[0], e_vx[1]);
		}
		return mst;
	}
	
}
