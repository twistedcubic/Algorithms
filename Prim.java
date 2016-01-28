import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;

/* Prim's algorithm for finding min
 * spanning tree using priority queue
 */

public class Prim {
	
	//keep all the edges between current tree and  
	//rest of graph in a queue
	private PriorityQueue<Edge> queue;
	
	private WeightedGraph graph;
	
	//keep MST in a list
	private ArrayList<Edge> mst;
		
	/* Build graph from graph built
	 * from set of weighted edges 
	 */
	public Prim(WeightedGraph graph){

		this.queue = new PriorityQueue<Edge>();
		this.mst = new ArrayList<Edge>();
		this.graph = graph;
	}
	
	/* Get MST
	 */	
	public ArrayList<Edge> get_mst(){	
		
		// visited keeps track of visited vertices 
		boolean[] visited = new boolean[graph.num_vx()];
		
		//pick random vertex
		int init_vx = 1;
		visited[init_vx] = true;
		
		//enqueue all connecting edges		
		Set<Edge> v_edges = graph.get_edges(init_vx);
		for( Edge e : v_edges){
			queue.add(e);
		}
		
		while(!queue.isEmpty()){
			Edge min_edge = queue.remove();
			//check that not both vertices
			//are visited
			int[] vx = min_edge.get_vertices();
			if(visited[vx[0]] && visited[vx[1]]){
				continue;				
			}
			
			//pick the not-visited vertex
			int other_vx_index;
			
			if(visited[vx[0]]){
				other_vx_index = 1;
			}else {
				other_vx_index = 0;
			}
			
			visited[vx[other_vx_index]] = true;
			mst.add(min_edge);
			
			//add new connecting edges/cuts
			Set<Edge> edges = graph.get_edges(vx[other_vx_index]);
			for(Edge e : edges){
				int[] temp_vx = e.get_vertices();
				//if the other vertex of e is not visited
				if(!visited[temp_vx[0]] || !visited[temp_vx[0]] ){
					queue.add(e);
				}
			}
			
		}
		return mst;
	}

	
}