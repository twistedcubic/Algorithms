import java.util.Set;
import java.util.TreeMap;

/* Dijkstra's algorithm for finding shortest 
 * path from origin vertex
 */

public class Dijkstra {

	private WeightedDigraph graph;
	//queue to contain Vertices with ongoing
	//relaxations
	private TreeMap<Integer, Double> map;
	//list to contain a Vertex with min dist
	//once all its neighbors are relaxes
	private final int INFTY = 10000;
	//current shortest distances
	private double[] dist;
	
	/* @param: edges is Set of DiEdge objects
	 * @param: initial_vertex is starting vertex
	 */
	public Dijkstra(Set<DiEdge> edges, int initial_vertex){
		this.graph = new WeightedDigraph(edges);
		this.map = new TreeMap<Integer, Double>();		
		
		//fill in queue with vertices
		int num_vx = graph.num_vx();
		this.dist = new double[num_vx];
		
		for(int i = 0; i < num_vx; i++){
			dist[i] = INFTY;
		}		
		dist[initial_vertex] = 0;
		
		map.put(initial_vertex, (double) 0);
		
		while(!map.isEmpty()){
			int current_vx = map.firstKey();
			//relax the edges
			get_min_dist(current_vx);
		}
	}
	
	/*
	 * Get current min distance to all neighboring
	 * vertices from vertex v
	 */
	public void get_min_dist(int v){
		for(DiEdge e : graph.get_edges(v)){
			int to_vx = e.to();
			if(dist[to_vx] > dist[v] + e.weight()){
				dist[to_vx] = dist[v] + e.weight();		
			}
			
			//replace if already in map
			if(map.containsKey(to_vx)){
				map.remove(to_vx);
			}
			
			map.put(to_vx, dist[to_vx]);
		}
	}
}
