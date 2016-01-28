import java.util.LinkedList;
import java.util.Set;

/* Bellman Ford algorithm for 
 * finding single-source shortest 
 * paths. Use a LinkedList to keep
 * track of recently updated vertices,
 * instead of looping through all vertices 
 * each time.
 */
public class BellmanFord {

	private WeightedDigraph graph;
	private LinkedList<Integer> list;
	private final int INFTY = 1000;
	//array to track the vertex multiplicity in list
	private int[] vx_mult;
	//current distances
	private double[] dist;
	
	/* @param graph is weighted acyclic graph
	 * @param initial_vx is starting vertex
	 */
	public BellmanFord(WeightedDigraph graph, int initial_vx){
		
		this.graph = graph;
		this.list = new LinkedList<Integer>();
		int num_vx = graph.num_vx();
		//automatically initialized to all 0
		vx_mult = new int[num_vx];
		list.add(initial_vx);
		vx_mult[initial_vx] = 1;
		
		this.dist = new double[num_vx];
		for(int i = 0; i < num_vx; i++){
			dist[i] = INFTY;
		}
		dist[initial_vx] = 0;
		
		while(!list.isEmpty()){
			//put in the earliest
			int cur_vx = list.removeFirst();
			//only optimal if not added again later
			if(vx_mult[cur_vx] == 1){
				relax_neighbors(cur_vx);
			}
			
		}
		
	}
	
	/* Update dist for all neighboring vertices
	 * and add to list
	 */
	public void relax_neighbors(int vx){		
		Set<DiEdge> vx_edges = graph.get_edges(vx);
		for(DiEdge e : vx_edges){
			int e_to = e.to();
			if(dist[e_to] < dist[vx] + e.weight()){
				dist[e_to] = dist[vx] + e.weight();
				list.add(e_to);
				vx_mult[e_to]++;
			}
		}
		
	}
	
	//return array of min distances
	public double[] get_min_dist(){
		return dist;
	}
	
}
