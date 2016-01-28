import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* Graph with weighted edges
 * 
 */
public class WeightedGraph {
	
	//list of vertices, each with set of edges
	//from that vertex
	private ArrayList<Set<Edge>> vertices;
	
	/* construct from set of edges
	 * to list of sets of edges.
	 * Each set in list corresponds to a vertex
	 */
	public WeightedGraph(Set<Edge> edges){
		this.vertices = new ArrayList<Set<Edge>>();
		// must add sufficient number of vertices
		// #vertices <= #edges - 1
		// assume graph connected
		for(int i = 0; i < edges.size(); i++){
			Set<Edge> temp_set = new HashSet<Edge>();
			vertices.add(temp_set);
		}
		
		for(Edge e : edges){
			int[] e_vx = e.get_vertices();
			if(e_vx.length != 2){
				System.out.println("Vertices retrival failed");
				return;
			}
			Set<Edge> new_set1 = vertices.get(e_vx[0]);
			Set<Edge> new_set2 = vertices.get(e_vx[1]);
			new_set1.add(e);
			new_set2.add(e);
			vertices.set(e_vx[1], new_set1);
			vertices.set(e_vx[2], new_set2);
		}
		
	}
	
	/* Return the edge set for a vertex v
	 * 
	 */
	public Set<Edge> get_edges(int v){
		return vertices.get(v);
	}
	
	/* Return number of vertices.
	 * Check size because initial allocation
	 * for list may have overshot
	 */
	public int num_vx(){
		int count = 0;
		for(Set<Edge> s : vertices){
			if(s.size() > 0){
				count++;
			}
		}
		return count;
	}
}
